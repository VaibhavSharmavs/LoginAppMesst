package com.example.loginapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginapp.model.Login;
import com.example.loginapp.viewmodel.LoginViewModel;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private  Login login;
    private Context context;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        return (T) new LoginViewModel(login,context);
    }

    public LoginViewModelFactory(Login login, Context applicationContext) {

        this.context = applicationContext;
        this.login = login;
    }
}
