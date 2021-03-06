package com.meest.viewmodel.login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.meest.R;
import com.meest.model.login.Login;
import com.meest.model.login.LoginResponseModel;
import com.meest.model.signup.VerifyUserNameResponse;
import com.meest.model.signup.components.DatePicker.date.DatePicker;
import com.meest.model.signup.components.GenderLayout;
import com.meest.model.signup.components.VerifyAllRequest;
import com.meest.network.APIService;
import com.meest.network.RetrofitInstance;
import com.meest.view.activities.VerficationCode;
import com.meest.view.activities.chat.ChatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> username =  new MutableLiveData<>();
    public MutableLiveData<String> password =  new MutableLiveData<>();

    public Login login;
    public Context context;
    public View view;
    public DatePicker datePicker;


    public MutableLiveData<String> userNameSignUp =  new MutableLiveData<>();
    public MutableLiveData<String> firstName =  new MutableLiveData<>();
    public MutableLiveData<String> lastName =  new MutableLiveData<>();
    public MutableLiveData<String> mobileno =  new MutableLiveData<>();
    public MutableLiveData<String> passwordSignUp =  new MutableLiveData<>();
    public MutableLiveData<String> dob =  new MutableLiveData<>();
    public MutableLiveData<String> gender =  new MutableLiveData<>();







    public void onDOBClick(){

        showDataPicker();


    }

    public void onGenderClick(){
        new GenderLayout().showGender(context,login,view,gender);



    }

    public void onSignUpClick(){

        login.setFistName(firstName.getValue());
        login.setLastName(lastName.getValue());
        login.setGender(gender.getValue());
        login.setDob(dob.getValue());
        login.setMobileno(mobileno.getValue());
        login.setUsernameSignUp(userNameSignUp.getValue());
        login.setPasswordSignUp(passwordSignUp.getValue());

        Intent intent = new Intent(context, VerficationCode.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("firstName",login.getFistName());
        intent.putExtra("lastName",login.getLastName());
        intent.putExtra("password",login.getPasswordSignUp());
        intent.putExtra("username",login.getUsernameSignUp());
        intent.putExtra("gender",login.getGender());
        intent.putExtra("mobileno",login.getMobileno());
        intent.putExtra("dob",login.getDob());
        context.startActivity(intent);


 //       callVerifyAll();

    }


    public void  callVerifyAll(){



        VerifyAllRequest verifyAllRequest = new VerifyAllRequest();
        verifyAllRequest.setUserName("+91"+login.getMobileno());

        APIService apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);
        Call<VerifyUserNameResponse> call = apiService.verifyAll(verifyAllRequest);
        call.enqueue(new Callback<VerifyUserNameResponse>() {
            @Override
            public void onResponse(Call<VerifyUserNameResponse> call, Response<VerifyUserNameResponse> response) {

                if(response.code()==200&& response.body()!=null){

                    Log.d("VerifyAll", response.body().getData().getMessage());


                    Intent intent = new Intent(context, VerficationCode.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    intent.putExtra("firstName",login.getFistName());
                    intent.putExtra("lastName",login.getLastName());
                    intent.putExtra("password",login.getPasswordSignUp());
                    intent.putExtra("username",login.getUsernameSignUp());
                    intent.putExtra("gender",login.getGender());
                    intent.putExtra("mobileno",login.getMobileno());
                    intent.putExtra("dob",login.getDob());
                    context.startActivity(intent);

                }



            }

            @Override
            public void onFailure(Call<VerifyUserNameResponse> call, Throwable t) {

                Log.d("Error",t.toString());

            }
        });



    }



    private void showDataPicker() {
        final Dialog setDate = new Dialog(view.getContext());
        setDate.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setDate.setContentView(R.layout.dialog_setdate);
        setDate.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setDate.show();
        datePicker = setDate.findViewById(R.id.datePicker);
        datePicker.setOnDateSelectedListener(new DatePicker.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day) {
                String  selected_date = datePicker.getDate();
                if (checkAgeValidatin(selected_date)) {
                    Log.e("selected date", "date: " + datePicker.getDate());
                    dob.setValue(getCurrentDate());
                    Log.e("after date", "date: " + selected_date);
                } else {
                    //      Utilss.showToast(LoginSignUpActivity.this, "Age should be greater than 13 ", R.color.msg_fail);
                }
            }
        });
    }

    public String getCurrentDate() {
        StringBuilder builder = new StringBuilder();
        if (datePicker.getDay() < 10) {
            builder.append("0" + datePicker.getDay() + "/");
        } else {
            builder.append(datePicker.getDay() + "/");
        }
        if (datePicker.getMonth() < 10) {
            builder.append("0" + datePicker.getMonth() + "/");
        } else {
            builder.append(datePicker.getMonth() + "/");
        }
        builder.append(datePicker.getYear());
        return builder.toString();
    }

    public static boolean checkAgeValidatin(String date) {
        int age = 0;
        boolean chkAge = false;
        DateFormat format = SimpleDateFormat.getDateInstance();
        try {
            Date date1 = format.parse(date);
            Calendar now = Calendar.getInstance();
            Calendar dob = Calendar.getInstance();
            dob.setTime(date1);
            if (dob.after(now)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            int year1 = now.get(Calendar.YEAR);
            int year2 = dob.get(Calendar.YEAR);
            age = year1 - year2;
            if (age > 13) {
                chkAge = true;
            }
            if (age == 13) {
                int month1 = now.get(Calendar.MONTH);
                int month2 = dob.get(Calendar.MONTH);
                if (month1 > month2) {
                    chkAge = true;
                } else if (month1 == month2 && age == 13) {
                    int day1 = now.get(Calendar.DAY_OF_MONTH);
                    int day2 = dob.get(Calendar.DAY_OF_MONTH);
                    if (day1 >= day2) {
                        chkAge = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chkAge;
    }




    public LoginViewModel(Login login, Context context,View view) {
        this.login = login;
        this.context = context;
        this.view = view;
    }


    public void onLoginClick(){

        login.setUsername("+91"+username.getValue());
        login.setPassword(password.getValue());
        login.setFcmToken("fmirXaAxROC4Oi4wIzuKEs:APA91bHrGbu7JXij4HzChl0yJtainoWYbwO8ohNYz9FL5D_pB1ncijSlX-noxeEcwNIXkHyzHgwdDOogOFJynJ73ULTCcJ5mtg2hVw4LlcwFX_fYPtI7y6krYhJhRSRZDqW3fW4LM3xe");

        if(login.isValid()){
            Log.d("LoginViewModel",username.getValue());
            callLogin();

        }else{
            Toast.makeText(context.getApplicationContext(),"Please Enter valid mobile no",Toast.LENGTH_SHORT).show();
        }

    }



    public void  callLogin(){

        APIService apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);
        Call<LoginResponseModel> call = apiService.login(login);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {

                if(response.code()==200&& response.body() != null && response.body().isSuccess()){
                Log.d("LoginResponse", response.body().getData().getToken());

                Intent intent = new Intent(context, ChatActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startActivity(intent);
                }
                else{

                    Toast.makeText(context.getApplicationContext(), "User not Found",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {

                Log.d("Error",t.toString());

            }
        });



    }
}
