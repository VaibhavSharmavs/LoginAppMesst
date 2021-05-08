package com.example.loginapp.network;

import com.example.loginapp.model.Login;
import com.example.loginapp.model.LoginResponseModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {

    @POST("loginUser")
    Call<LoginResponseModel> login(@Body Login login);




}
