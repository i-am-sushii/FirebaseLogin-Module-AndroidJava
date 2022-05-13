package com.example.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText Email,Password;
    Button loginbtn;
    TextView signup;
    String user,pass;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        loginbtn=findViewById(R.id.loginbtn);
        signup=findViewById(R.id.signup);
        auth=FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });




    }

    private void login() {
        user= Email.getText().toString().trim();
        pass=Password.getText().toString().trim();

        if (user.isEmpty()) {
            Email.setError("Please Enter Your Email");
        }
        else if (!user.contains("@")){
            Email.setError("Invalid Email Address");
        }
        if (pass.isEmpty()) {
            Password.setError("Please Enter Your Password");
        }
        else if (pass.length()<=7) {
            Password.setError("Password Should Be 8 Digits");
        }
        else{
            auth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this,MainActivity.class));
                    }
                    else {
                        Toast.makeText(Login.this, "Login Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }


    }
}