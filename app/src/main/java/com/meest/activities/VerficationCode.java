package com.meest.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.meest.R;
import com.meest.model.signup.RegisterRequest;
import com.meest.model.signup.RegisterResponse;
import com.meest.model.signup.VerifyOtpAllRequest;
import com.meest.model.signup.VerifyOtpAllResponse;
import com.meest.network.APIService;
import com.meest.network.RetrofitInstance;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerficationCode extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 200;
    String device_name = "NA", device_model = "NA", android_version = "NA", android_id = "NA";
    String dInfo = "NA";
    RelativeLayout otp_submit;
    Button otp_btn, txt_send_again;
    PinView pinView;
    TextView tct_expire;
    LinearLayout txtCountLayout;
    private static final int REQ_USER_CONSENT = 200;

    private String firstName,lastName,dob,gender,mobileno,password,username,lat,lng;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_verfication_code);

        firstName = getIntent().getExtras().getString("firstName");
        lastName = getIntent().getExtras().getString("lastName");
        dob = getIntent().getExtras().getString("dob");
        gender = getIntent().getExtras().getString("gender");
        mobileno = getIntent().getExtras().getString("mobileno");
        password = getIntent().getExtras().getString("password");
        username = getIntent().getExtras().getString("username");


        txtCountLayout = findViewById(R.id.txtCountLayout);
        tct_expire = findViewById(R.id.tct_expire);
        otp_submit = findViewById(R.id.otp_submit);
        otp_btn = findViewById(R.id.otp_btn);
        txt_send_again = findViewById(R.id.txt_send_again);
        pinView = findViewById(R.id.pinView);
        otp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onClick: " + pinView.getText());
                if (!pinView.getText().toString().equals("")) {
                    verifyOTPAll();
                } else {
                    Toast.makeText(VerficationCode.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
                }
//                Intent intent=new Intent(Verification_Code.this, InterestActivity.class);
//                startActivity(intent);
            }
        });
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                txtCountLayout.setVisibility(View.VISIBLE);
                tct_expire.setText(millisUntilFinished / 1000 + " sec");
                txt_send_again.setVisibility(View.GONE);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                txt_send_again.setVisibility(View.VISIBLE);
                txtCountLayout.setVisibility(View.GONE);
            }

        }.start();
        txt_send_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //resending otp
            //    resendNumberOtp();

                new CountDownTimer(60000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        tct_expire.setText(millisUntilFinished / 1000 + " sec");
                        txt_send_again.setVisibility(View.GONE);
                        txtCountLayout.setVisibility(View.VISIBLE);
                        //here you can have your logic to set text to edittext

                    }

                    public void onFinish() {
                        txt_send_again.setVisibility(View.VISIBLE);
                        txtCountLayout.setVisibility(View.GONE);
                    }
                }.start();
            }
        });
        startSmsUserConsent();
        getAllBackgroundData();
    }

    private void verifyOTPAll(){

        VerifyOtpAllRequest verifyOtpAllRequest = new VerifyOtpAllRequest();
        verifyOtpAllRequest.setOtp(pinView.getText().toString());
        verifyOtpAllRequest.setUserName(username);
        verifyOtpAllRequest.setFcmToken("");

        APIService apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);
        Call<VerifyOtpAllResponse> call = apiService.verifyOtpAll(verifyOtpAllRequest);
        call.enqueue(new Callback<VerifyOtpAllResponse>() {
            @Override
            public void onResponse(Call<VerifyOtpAllResponse> call, Response<VerifyOtpAllResponse> response) {

                Log.d("VerifyOTPAll", response.body().getData().getMessage());

                if (response.code() == 200 && response.body() != null) {
                    if (response.body().getCode() == 1) {

                           register();


                    }}

            }

            @Override
            public void onFailure(Call<VerifyOtpAllResponse> call, Throwable t) {

                Log.d("Error",t.toString());

            }
        });



    }

    private void register(){

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName(firstName);
        registerRequest.setLastName(lastName);
        registerRequest.setPassword(password);
        registerRequest.setGender("gender");
        registerRequest.setMobile(mobileno);
        registerRequest.setEmail("");
        registerRequest.setgToken("");
        registerRequest.setFbToken("");
        registerRequest.setLocation("");
        registerRequest.setAccountType("PUBLIC");
        registerRequest.setDeviceVoipToken("");
        registerRequest.setFriendReferral("");
        registerRequest.setNotification("1");
        registerRequest.setMediaAutoDownload("false");
        registerRequest.setDnd("false");
        registerRequest.setLat(lat);
        registerRequest.setLag(lng);
        registerRequest.setDeviceName(device_name);
        registerRequest.setDeviceModel(device_model);
        registerRequest.setDeviceVersion(android_version);
        registerRequest.setOsType("Android");
        registerRequest.setImeiNumber(getIMEIDeviceId());
        registerRequest.setTimeZone(getCurrentTizeZone().getDisplayName(false, TimeZone.SHORT));
        registerRequest.setDateZone(getCurrentTizeZone().getDisplayName(false, TimeZone.SHORT));
        registerRequest.setStatus("");
        registerRequest.setDob("dob");


        APIService apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);
        Call<RegisterResponse> call = apiService.register(registerRequest);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {


                if (response.code() == 200 && response.body() != null) {
                    if (response.body().getCode() == 1) {


                        Log.d("Register","Success");


                        Log.d("Register",response.body().getData().getNewUser().getUsername());




                    }}

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                Log.d("Error",t.toString());

            }
        });



    }


    private void startSmsUserConsent() {
        SmsRetrieverClient client = SmsRetriever.getClient(this);
        //We can add sender phone number or leave it blank
        // I'm adding null here
        client.startSmsUserConsent(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Toast.makeText(RegisterOtpActivity.this, "On Success", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //  Toast.makeText(RegisterOtpActivity.this, "On OnFailure", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getAllBackgroundData() {
        getLocation();


        getIMEIDeviceId();

        getCurrentTizeZone();
        getPhoneInfo();
        // getImeiNumber();
    }

    public void getPhoneInfo() {
        device_name = Build.MANUFACTURER;
        device_model = Build.MODEL;
        android_version = Build.VERSION.RELEASE;
        android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        Log.e("data", "data: " + device_name + "::" + device_model + "::" + android_id);


    }


    public TimeZone getCurrentTizeZone() {
        TimeZone tz = TimeZone.getDefault();
        System.out.println("TimeZone   " + tz.getDisplayName(false, TimeZone.SHORT) + " Timezone id :: " + tz.getID());
        //  TimeZone GMT+09:30 Timezone id :: Australia/Darwin  //timeZone format

        return tz;

    }

    public String getIMEIDeviceId() {


//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        String stringIMEI = telephonyManager.getImei();
//

        String deviceId;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            deviceId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        } else {
            final TelephonyManager mTelephony = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return "";
                }
            }
            assert mTelephony != null;
            if (mTelephony.getDeviceId() != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    deviceId = mTelephony.getImei();
                } else {
                    deviceId = mTelephony.getDeviceId();
                }
            } else {
                deviceId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
            }
        }
        Log.d("deviceId", deviceId);
        return deviceId;
    }

    public void getLocation() {
//        SharedPrefreances.setSharedPreferenceString(getActivity(), SharedPrefreances.Reg_lat, lat);
//        SharedPrefreances.setSharedPreferenceString(getActivity(), SharedPrefreances.Reg_lat, lng);
        if (!canGetLocation()) {
            Toast.makeText(this, getString(R.string.Please_Enable_Location), Toast.LENGTH_SHORT).show();
        }

        displayLocationSettingsRequest(this);
        requestLocationUpdates(this);
    }

    public boolean canGetLocation() {
        return isLocationEnabled(this); // application context
    }

    public boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    private void displayLocationSettingsRequest(Context context) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);
        locationRequest.setNumUpdates(5);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i("TAG", "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i("TAG", "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(VerficationCode.this, 12);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i("TAG", "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i("TAG", "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
    }

    private void requestLocationUpdates(Context context) {

//Specify how often your app should request the device’s location//

        LocationRequest request = new LocationRequest();
        request.setInterval(2000);
        request.setNumUpdates(6);

        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);

        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (permission == PackageManager.PERMISSION_GRANTED) {

            client.requestLocationUpdates(request, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {

                    Location location = locationResult.getLastLocation();
                    if (location != null) {
                         lat = location.getLatitude() + "";
                         lng = location.getLongitude() + "";

//                        //String locationname = getAddress(location.getLatitude(), location.getLongitude());
                        Log.e("TAG", lat + "," + lng);

                    } else {
                        Log.d("TAG", "location is not enabled");
                    }

                }
            }, null);
        } else {
            //SaveAttInDB("NA", "LOCATION PERMISSION IS NOT ENABLE FOR APPLICATION");
            Log.d("TAG", "location permission is not enabled");
            // showno();

        }
    }

}