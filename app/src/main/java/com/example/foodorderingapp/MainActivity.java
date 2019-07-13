package com.example.foodorderingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn;
    Button btnLogIn;
    EditText uidEditText;
    EditText pwdEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnLogIn = (Button) findViewById(R.id.btnLogin);
        uidEditText =(EditText) findViewById(R.id.uidEditText);
        pwdEditText = (EditText) findViewById(R.id.pwdEditText);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newActivityLoad = new Intent(MainActivity.this,CreatingAccount.class);
                startActivity(newActivityLoad);

            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BackgroundWorker bw = new BackgroundWorker(MainActivity.this);
                bw.execute("login",uidEditText.getText().toString(),pwdEditText.getText().toString());

            }
        });


    }

}
