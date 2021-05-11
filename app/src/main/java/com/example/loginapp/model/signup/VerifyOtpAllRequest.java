package com.example.loginapp.model.signup;

public class VerifyOtpAllRequest {
    String userName;
    String otp;
    String fcmToken;

    public String getUserName() {
        return userName;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
