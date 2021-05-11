package com.example.loginapp.viewmodel.signup;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginapp.model.signup.SignUp;

public class SignUpViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private SignUp signUp;
    private Context context;
    private View view;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull  Class<T> modelClass) {
        return (T) new SignUpViewModel(signUp,context,view);
    }

    public SignUpViewModelFactory(SignUp signUp, Context applicationContext, View rootView) {
        this.signUp = signUp;
        this.context = applicationContext;
        this.view = rootView;
    }
}
