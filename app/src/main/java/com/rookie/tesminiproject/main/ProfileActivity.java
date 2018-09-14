package com.rookie.tesminiproject.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rookie.tesminiproject.R;
import com.rookie.tesminiproject.model.Login;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private CircleImageView mProfileImage;
    private TextView mNameTv,mUsernameTv,mEmailTv,mAddressTv;

    private String mToken,mUsername,mFullname,mEmail,mAddress,mPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mProfileImage = findViewById(R.id.imgProfile);
        mNameTv = findViewById(R.id.nameTv);
        mUsernameTv = findViewById(R.id.usernameTv);
        mEmailTv = findViewById(R.id.emailTv);
        mAddressTv = findViewById(R.id.alamatTv);

        Intent intent = getIntent();
        mToken = intent.getStringExtra("token");
        mFullname = intent.getStringExtra("fullname");
        mUsername = intent.getStringExtra("username");
        mEmail = intent.getStringExtra("email");
        mAddress = intent.getStringExtra("address");
        mPhoto = intent.getStringExtra("photo");

        Glide.with(this).load(mPhoto).into(mProfileImage);
        mNameTv.setText(mFullname);
        mUsernameTv.setText(mUsername);
        mEmailTv.setText(mEmail);
        mAddressTv.setText(mAddress);

    }
}
