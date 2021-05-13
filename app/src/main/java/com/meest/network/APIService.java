package com.meest.network;

import com.meest.model.login.Login;
import com.meest.model.login.LoginResponseModel;
import com.meest.model.signup.RegisterResponse;
import com.meest.model.signup.VerifyUserNameRequest;
import com.meest.model.signup.VerifyUserNameResponse;
import com.meest.model.signup.RegisterRequest;
import com.meest.model.signup.VerifyOtpAllRequest;
import com.meest.model.signup.VerifyOtpAllResponse;
import com.meest.model.signup.components.VerifyAllRequest;

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
