package com.example.loginapp.model.login;

import android.util.Patterns;


public class Login {


    private String username,password,usernameSignUp,passwordSignUp,fistName,lastName,dob,gender,mobileno;
    private String fcmToken;

    public Login() {

    }

    public String getUsernameSignUp() {
        return usernameSignUp;
    }

    public void setUsernameSignUp(String usernameSignUp) {
        this.usernameSignUp = usernameSignUp;
    }

    public String getPasswordSignUp() {
        return passwordSignUp;
    }

    public void setPasswordSignUp(String passwordSignUp) {
        this.passwordSignUp = passwordSignUp;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
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
            
            if(this.password!=null&& !this.password.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
