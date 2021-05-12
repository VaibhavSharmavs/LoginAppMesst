package com.example.loginapp.viewmodel.signup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginapp.R;
import com.example.loginapp.activities.VerficationCode;
import com.example.loginapp.model.login.LoginResponseModel;
import com.example.loginapp.model.signup.SignUp;
import com.example.loginapp.model.signup.VerifyUserNameRequest;
import com.example.loginapp.model.signup.VerifyUserNameResponse;
import com.example.loginapp.model.signup.components.DatePicker.date.DatePicker;
import com.example.loginapp.model.signup.components.GenderLayout;
import com.example.loginapp.network.APIService;
import com.example.loginapp.network.RetrofitInstance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {
    public MutableLiveData<String> userName =  new MutableLiveData<>();
    public MutableLiveData<String> firstName =  new MutableLiveData<>();
    public MutableLiveData<String> lastName =  new MutableLiveData<>();
    public MutableLiveData<String> mobileno =  new MutableLiveData<>();
    public MutableLiveData<String> password =  new MutableLiveData<>();
    public MutableLiveData<String> dob =  new MutableLiveData<>();
    public MutableLiveData<String> gender =  new MutableLiveData<>();



    public SignUp signUp;
    public Context context;
    public View view;
    DatePicker datePicker;

    public SignUpViewModel(SignUp signUp, Context context, View view) {
        this.signUp = signUp;
        this.context = context;
        this.view = view;
    }

    public SignUpViewModel() {

    }


    public void onDOBClick(){

        showDataPicker();


    }

    public void onGenderClick(){

    }

    public void onSignUpClick(){

        signUp.setMobileno(mobileno.getValue());


    }






    private void showDataPicker() {
        final Dialog setDate = new Dialog(context);
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
}
