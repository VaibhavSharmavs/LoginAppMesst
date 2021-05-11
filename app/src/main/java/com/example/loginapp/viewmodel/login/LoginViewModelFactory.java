package com.example.loginapp.viewmodel.login;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginapp.model.login.Login;
import com.example.loginapp.viewmodel.login.LoginViewModel;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private  Login login;
    private Context context;
    private View view;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        return (T) new LoginViewModel(login,context,view);
    }

    public LoginViewModelFactory(Login login, Context applicationContext,View view) {

        this.context = applicationContext;
        this.login = login;
        this.view = view;
    }
}
