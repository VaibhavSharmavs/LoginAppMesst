package com.example.loginapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.loginapp.databinding.ActivityMainBinding;
import com.example.loginapp.model.login.Login;
import com.example.loginapp.model.login.components.RoundedView;
import com.example.loginapp.model.signup.SignUp;
import com.example.loginapp.model.signup.VerifyUserNameRequest;
import com.example.loginapp.model.signup.VerifyUserNameResponse;
import com.example.loginapp.network.APIService;
import com.example.loginapp.network.RetrofitInstance;
import com.example.loginapp.viewmodel.login.LoginViewModel;
import com.example.loginapp.viewmodel.login.LoginViewModelFactory;
import com.example.loginapp.viewmodel.signup.SignUpViewModel;
import com.example.loginapp.viewmodel.signup.SignUpViewModelFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_signUp,tv_login;
    RelativeLayout login_rl,signup_rl,login_submit,signUp_submit;
    LinearLayout ll_login,ll_signup,forgor_ll;
    RoundedView main_layout;
    EditText edit_username;
    ProgressBar username_progress;
    boolean login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        tv_signUp = findViewById(R.id.tv_signUp);
        tv_login = findViewById(R.id.tv_login);
        login_rl = findViewById(R.id.login_rl);
        signup_rl = findViewById(R.id.signup_rl);
        ll_login = findViewById(R.id.ll_login);
        ll_signup = findViewById(R.id.ll_signup);
        forgor_ll = findViewById(R.id.forgor_ll);
        login_submit = findViewById(R.id.login_submit);
        signUp_submit = findViewById(R.id.signUp_submit);
        main_layout = findViewById(R.id.main_layout);
        edit_username = findViewById(R.id.edit_username);
        username_progress = findViewById(R.id.username_progress);


        tv_signUp.setOnClickListener(this);
        tv_login.setOnClickListener(this);


        LoginViewModel loginViewModel = new ViewModelProvider(MainActivity.this,
                new LoginViewModelFactory(
                        new Login(),getApplicationContext(),getWindow().getDecorView().getRootView())).get(LoginViewModel.class);
        binding.setLoginModel(loginViewModel);





//        SignUpViewModel signUpViewModel = new ViewModelProvider(MainActivity.this,
//                new SignUpViewModelFactory(
//                        new SignUp(),getApplicationContext(),getWindow().getDecorView().getRootView())).get(SignUpViewModel.class);
//        binding.setSignUpModel(signUpViewModel);


        edit_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                username_progress.setVisibility(View.VISIBLE);
                if (s.toString().trim().length() > 4) {
                  //  CheckValues(s.toString());
                    callVerifyUserName(loginViewModel.userNameSignUp);
                } else {
//                    Toast.makeText(LoginSignUpActivity.this, "Your Username length is less than 4", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public void  callVerifyUserName(MutableLiveData<String> userName){

        username_progress.setVisibility(View.GONE);

        VerifyUserNameRequest verifyUserNameRequest = new VerifyUserNameRequest();
        verifyUserNameRequest.setUsername(userName.getValue());

        APIService apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);
        Call<VerifyUserNameResponse> call = apiService.verifyUsername(verifyUserNameRequest);
        call.enqueue(new Callback<VerifyUserNameResponse>() {
            @Override
            public void onResponse(Call<VerifyUserNameResponse> call, Response<VerifyUserNameResponse> response) {

                if(response.code()==200&&response.body()!=null) {

                    Log.d("VerfiyUserName", String.valueOf(response.body().getSuccess()));
                }
            }

            @Override
            public void onFailure(Call<VerifyUserNameResponse> call, Throwable t) {

                Log.d("Error",t.toString());

            }
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.tv_signUp){




            login = false;
            login_rl.setTranslationX(0);
            signup_rl.setTranslationX(0);
            ll_login.setVisibility(View.GONE);
            forgor_ll.setVisibility(View.GONE);
            signUp_submit.setVisibility(View.VISIBLE);
            ll_signup.setVisibility(View.VISIBLE);
            login_rl.setBackgroundResource(0);
            signup_rl.setBackgroundResource(R.drawable.drop_shadow_1);
            tv_login.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
            tv_signUp.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.second_color));
            if (login_rl.getTranslationX() == 0) {
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(signup_rl, "translationX", login_rl.getTranslationX() - 200, signup_rl.getTranslationX());
                anim1.setDuration(300);
                anim1.start();
            }
            main_layout.setBottomLeftCornerRadius(20);
            main_layout.setBottomRightCornerRadius(20);
            main_layout.setTopLeftCornerRadius(20);
            main_layout.setTopRightCornerRadius(0);
            main_layout.setBackgroundResource(R.drawable.bg_stroke_main_1);
            final ChangeBounds transition = new ChangeBounds();
            transition.setDuration(600L);
            TransitionManager.beginDelayedTransition(main_layout, new ChangeBounds().setDuration(300L));

        }

        if(v.getId()==R.id.tv_login){

            login = true;
            signup_rl.setTranslationX(0);
            login_rl.setTranslationX(0);
            ll_login.setVisibility(View.VISIBLE);
            ll_signup.setVisibility(View.GONE);
            login_submit.setVisibility(View.VISIBLE);
            forgor_ll.setVisibility(View.VISIBLE);
            login_rl.setBackgroundResource(R.drawable.drop_shadow_1);
            signup_rl.setBackgroundResource(0);
            tv_login.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.first_color));
            tv_signUp.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
            if (signup_rl.getTranslationX() == 0) {
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(login_rl, "translationX", signup_rl.getTranslationX() + 200, login_rl.getTranslationX());
                anim1.setDuration(300);
                anim1.start();
            }
            main_layout.setBottomLeftCornerRadius(20);
            main_layout.setBottomRightCornerRadius(20);
            main_layout.setTopLeftCornerRadius(0);
            main_layout.setTopRightCornerRadius(20);
            main_layout.setBackgroundResource(R.drawable.bg_stroke_main);
            TransitionManager.beginDelayedTransition(main_layout, new ChangeBounds().setDuration(300L));

        }


    }


}