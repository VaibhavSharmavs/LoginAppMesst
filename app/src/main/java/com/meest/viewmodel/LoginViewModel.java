package com.meest.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.meest.model.login.Login;
import com.meest.model.login.LoginResponseModel;
import com.meest.network.APIService;
import com.meest.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> username =  new MutableLiveData<>();
    public MutableLiveData<String> password =  new MutableLiveData<>();

    public Login login;
    public Context context;

    public LoginViewModel(Login login, Context context) {
        this.login = login;
        this.context = context;
    }

    public void onLoginClick(){
        login.setUsername(username.getValue());
        login.setPassword(password.getValue());
        login.setFcmToken("fmirXaAxROC4Oi4wIzuKEs:APA91bHrGbu7JXij4HzChl0yJtainoWYbwO8ohNYz9FL5D_pB1ncijSlX-noxeEcwNIXkHyzHgwdDOogOFJynJ73ULTCcJ5mtg2hVw4LlcwFX_fYPtI7y6krYhJhRSRZDqW3fW4LM3xe");

        if(login.isValid()){
            Log.d("LoginViewModel",username.getValue());
            callLogin();

        }else{
            Toast.makeText(context,"Please Enter valid mobile no",Toast.LENGTH_SHORT).show();
        }

    }



    public void  callLogin(){
        APIService apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);
        Call<LoginResponseModel> call = apiService.login(login);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {

                Log.d("LoginResponse", response.body().getData().getToken());

            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {

                Log.d("Error",t.toString());

            }
        });
    }
}
