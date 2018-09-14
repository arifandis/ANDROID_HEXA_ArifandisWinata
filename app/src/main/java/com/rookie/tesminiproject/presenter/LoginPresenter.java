package com.rookie.tesminiproject.presenter;


import android.util.Log;

import com.rookie.tesminiproject.api.ApiInterface;
import com.rookie.tesminiproject.api.ApiService;
import com.rookie.tesminiproject.model.Login;
import com.rookie.tesminiproject.view.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginView.Presenter {
    private LoginView.View mView;
    private ApiInterface mApiInterface;
    private String mUsername,mPassword;

    public LoginPresenter(LoginView.View mView, ApiInterface mApiInterface, String mUsername, String mPassword) {
        this.mView = mView;
        this.mApiInterface = mApiInterface;
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    @Override
    public void getDataProfile() {
        mView.showLoading();
        retrofit2.Call<Login> loginCall = mApiInterface.login(mUsername,mPassword);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Log.e("Retrofit Post","Sukses: "+response.body().getToken());
                mView.showDataProfile(response.body());
                mView.hideLoading();
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.e("Retrofit Post", t.toString());
            }
        });
    }
}
