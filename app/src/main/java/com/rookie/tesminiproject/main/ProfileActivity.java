package com.rookie.tesminiproject.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rookie.tesminiproject.R;
import com.rookie.tesminiproject.adapter.PlayerListAdapter;
import com.rookie.tesminiproject.api.ApiInterface;
import com.rookie.tesminiproject.api.ApiService;
import com.rookie.tesminiproject.model.Player;
import com.rookie.tesminiproject.model.PlayerList;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private CircleImageView mProfileImage;
    private TextView mNameTv,mUsernameTv,mEmailTv,mAddressTv;
    private RecyclerView recyclerView;
    private PlayerListAdapter playerAdapter;

    private String mToken,mUsername,mFullname,mEmail,mAddress,mPhoto;

    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mProfileImage = findViewById(R.id.imgProfile);
        mNameTv = findViewById(R.id.nameTv);
        mUsernameTv = findViewById(R.id.usernameTv);
        mEmailTv = findViewById(R.id.emailTv);
        mAddressTv = findViewById(R.id.alamatTv);
        recyclerView = findViewById(R.id.recycleId);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        mApiInterface = ApiService.getService().create(ApiInterface.class);

        Intent intent = getIntent();
        mToken = intent.getStringExtra("token");
        mFullname = intent.getStringExtra("fullname");
        mUsername = intent.getStringExtra("username");
        mEmail = intent.getStringExtra("email");
        mAddress = intent.getStringExtra("address");
        mPhoto = intent.getStringExtra("photo");

        initData(mToken);

        Glide.with(this).load(mPhoto).into(mProfileImage);
        mNameTv.setText(mFullname);
        mUsernameTv.setText(mUsername);
        mEmailTv.setText(mEmail);
        mAddressTv.setText(mAddress);

    }

    public void initData(String token){
        Call<PlayerList> playerCall = mApiInterface.getPlayer(token);
        playerCall.enqueue(new Callback<PlayerList>() {
            @Override
            public void onResponse(Call<PlayerList> call, Response<PlayerList> response) {
                List<Player> players = response.body().getPlayers();
                playerAdapter = new PlayerListAdapter(getApplicationContext(),players);
                recyclerView.setAdapter(playerAdapter);
                playerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PlayerList> call, Throwable t) {
                Log.e("Get",t.toString());
            }
        });
    }
}
