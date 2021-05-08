package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.loginapp.databinding.ActivityMainBinding;
import com.example.loginapp.model.Login;
import com.example.loginapp.viewmodel.LoginViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);




        LoginViewModel loginViewModel = new ViewModelProvider(MainActivity.this, new LoginViewModelFactory(new Login(),getApplicationContext())).get(LoginViewModel.class);
        binding.setLoginModel(loginViewModel);

    }
}