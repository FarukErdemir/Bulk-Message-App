package com.example.denememesaj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.denememesaj.ui.sendmessage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button loginbtn;
    TextView emailll,passworddd;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginbtn=findViewById(R.id.Loginbutton);
        emailll=findViewById(R.id.Emaillogin);
        passworddd=findViewById(R.id.Passwordlogin);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();
            }
        });
    }
    private void loginuser(){
        String Emailloginn=emailll.getText().toString();
        String passs=passworddd.getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(Emailloginn,passs).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

            }
        });
    }
    public void SignupclickL(View view){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
    public void Loginclick(View view){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}