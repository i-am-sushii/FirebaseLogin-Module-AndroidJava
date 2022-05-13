package com.example.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText name, email, password;
    Button register;
    TextView signin;
    String Name, Email, Password;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        signin = findViewById(R.id.signin);
        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });


    }

    private void register() {
        Email = email.getText().toString().trim();
        Password = password.getText().toString().trim();
        if (Email.isEmpty()) {
            email.setError("Please Enter Your Email");
        }
        else if (!Email.contains("@")){
            email.setError("Invalid Email Address");
        }
        if (Password.isEmpty()) {
            password.setError("Please Enter Your Password");
        }
        else if (Password.length()<=7) {
            password.setError("Password Should Be 8 Digits");
        }
        else{
            auth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Register.this, "Registeration Succcessfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this,Login.class));
                    } else {
                        Toast.makeText(Register.this, "Registeration failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }
}