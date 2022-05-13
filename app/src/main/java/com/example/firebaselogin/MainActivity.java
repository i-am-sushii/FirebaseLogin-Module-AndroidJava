package com.example.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        FirebaseUser user=mAuth.getCurrentUser();
        if (user==null)
        {
            startActivity(new Intent(MainActivity.this,Login.class));
        }

        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.home){
            Toast.makeText(this, "You clicked Home", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.logout){
            Toast.makeText(this, "Logout Successfull", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this,Login.class));
        }
        if(item.getItemId()==R.id.settings){
            Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.about){
            Toast.makeText(this, "You clicked About", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}