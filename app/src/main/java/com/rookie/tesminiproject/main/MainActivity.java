package com.rookie.tesminiproject.main;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.rookie.tesminiproject.R;
import com.rookie.tesminiproject.api.ApiInterface;
import com.rookie.tesminiproject.api.ApiService;
import com.rookie.tesminiproject.model.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText editUsername,editPassword;
    private Button loginBtn;
    private ProgressBar loginProgress;
    private RelativeLayout parentView;

    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUsername = findViewById(R.id.etUsername);
        editPassword = findViewById(R.id.etPassword);
        loginBtn= findViewById(R.id.btnLogin);
        loginProgress = findViewById(R.id.progressBar);
        parentView = findViewById(R.id.loginView);

        mApiInterface = ApiService.getService().create(ApiInterface.class);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmpty();
            }
        });
    }

    public void checkEmpty(){
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        if (username.isEmpty() || password.isEmpty()){
            Snackbar.make(parentView,"Isi username dan password",Snackbar.LENGTH_SHORT).show();
        }else if (password.length() < 6){
            Snackbar.make(parentView,"Password minimal 6 karakter",Snackbar.LENGTH_SHORT).show();
        }
        else {
            loginHandler(username,password);
        }
    }

    public void loginHandler(String username,String password){
        loginProgress.setVisibility(View.VISIBLE);
        retrofit2.Call<Login> loginCall = mApiInterface.login(username,password);
            loginCall.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    loginProgress.setVisibility(View.GONE);
                    Log.e("Retrofit Post","Sukses: "+String.valueOf(response.body()));
                    Snackbar.make(parentView,response.body().getToken(),Snackbar.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                    intent.putExtra("token",response.body().getToken());
                    intent.putExtra("username",response.body().getUsername());
                    intent.putExtra("email",response.body().getEmail());
                    intent.putExtra("fullname",response.body().getFullname());
                    intent.putExtra("address",response.body().getAddress());
                    intent.putExtra("photo",response.body().getPhoto());
                    startActivity(intent);

                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                        Log.e("Retrofit Post", t.toString());
                        Snackbar.make(parentView,t.toString(),Snackbar.LENGTH_SHORT).show();
                }
            });
    }
}
