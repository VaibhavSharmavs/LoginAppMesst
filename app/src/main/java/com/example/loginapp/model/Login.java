package com.example.loginapp.model;

import android.util.Patterns;


public class Login {


    private String username,password;
    private String fcmToken;

    public Login() {

    }




    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login(String mobile, String password) {
        this.username = mobile;
        this.password = password;
    }

    public boolean isValid(){
        if(this.username!=null&& !this.username.isEmpty()&& Patterns.PHONE.matcher(this.username).matches()){
            return true;
        }
        return false;
    }
}
