package com.example.denememesaj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent((SplashActivity.this),MainActivity.class));
        }
    }
    public void LoginclickS(View view){
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
    }
    public void SignupclickS(View view){
        startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
    }



}