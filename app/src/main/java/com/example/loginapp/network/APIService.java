package com.example.loginapp.network;

import com.example.loginapp.model.login.Login;
import com.example.loginapp.model.login.LoginResponseModel;
import com.example.loginapp.model.signup.RegisterResponse;
import com.example.loginapp.model.signup.VerifyUserNameRequest;
import com.example.loginapp.model.signup.VerifyUserNameResponse;
import com.example.loginapp.model.signup.RegisterRequest;
import com.example.loginapp.model.signup.VerifyOtpAllRequest;
import com.example.loginapp.model.signup.VerifyOtpAllResponse;
import com.example.loginapp.model.signup.components.VerifyAllRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {

    @POST("pub/loginUser")
    Call<LoginResponseModel> login(@Body Login login);

    @POST("/pub/verifyUsername")
    Call<VerifyUserNameResponse> verifyUsername(@Body VerifyUserNameRequest verifyParam);

    @POST("/pub/verifyAll")
    Call<VerifyUserNameResponse> verifyAll(@Body VerifyAllRequest verifyParam);

    @POST("/pub/verifyOtpAll")
    Call<VerifyOtpAllResponse> verifyOtpAll(@Body VerifyOtpAllRequest verifyParam);

    @POST("/pub/register")
    Call<RegisterResponse> register(@Body RegisterRequest resetPasswordParam);




}
