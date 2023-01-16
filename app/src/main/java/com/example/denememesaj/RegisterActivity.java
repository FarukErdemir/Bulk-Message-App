package com.example.denememesaj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    Button signup;
    EditText email,password;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signup=findViewById(R.id.Signupbutton);
        email=findViewById(R.id.Emailregister);
        password=findViewById(R.id.Passwordregister);

        firebaseAuth=FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupClick();
            }
        });
    }
    private void signupClick(){
        String emaill=email.getText().toString();
        String passwordd=password.getText().toString();
        if(emaill.isEmpty()){
            Toast.makeText(this,"hbele",Toast.LENGTH_SHORT).show();
        }
        if(passwordd.isEmpty()){
            Toast.makeText(this,"hbele",Toast.LENGTH_SHORT).show();
        }

        firebaseAuth.createUserWithEmailAndPassword(emaill,passwordd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

            }
        });

    }
    public void LoginclickR(View view){
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

}