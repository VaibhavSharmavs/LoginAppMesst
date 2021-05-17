package com.meest.viewmodel.login;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.meest.model.login.Login;

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
