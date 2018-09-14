package com.rookie.tesminiproject.api;

import com.rookie.tesminiproject.model.Login;
import com.rookie.tesminiproject.model.Player;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("/androidrec/public/api/login")
    Call<Login> login(@Field("username") String username,
                      @Field("password") String password);

    @GET("mylist")
    Call<Player> getPlayer(@Header("Authorization") String authorization);
}
