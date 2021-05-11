package com.example.loginapp.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponseModel {

    @SerializedName("code")
    public   int code;

    @SerializedName("data")
    public Data data ;

    @SerializedName("success")
    public boolean success;

    public int getCode() {
        return code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public class Data{

        @SerializedName("user")
        public User user ;


        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @SerializedName("token")
        public String token;

    }

    public class User{

        public String dob;

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }
    }


}


