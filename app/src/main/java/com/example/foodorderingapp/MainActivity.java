package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Button btnSignIn;
    Button btnLogIn;
    EditText uidEditText;
    EditText pwdEditText;
    Handler handler;
    private Bundle bb = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        //btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnLogIn = (Button) findViewById(R.id.btnLogin);
        uidEditText =(EditText) findViewById(R.id.uidEditText);
        pwdEditText = (EditText) findViewById(R.id.pwdEditText);

//        btnSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent newActivityLoad = new Intent(MainActivity.this,CreatingAccount.class);
//                startActivity(newActivityLoad);
//
//            }
//        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message inputMessage) {
                        bb = inputMessage.getData();
                        String str = bb.getString("invalid_credentials");
                        Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
                    }
                };
//                BackgroundWorker bw = new BackgroundWorker(MainActivity.this,handler,(ListView) findViewById(R.id.list) );
//                bw.execute("login",uidEditText.getText().toString(),pwdEditText.getText().toString());

                Intent newActivityLoad = new Intent(MainActivity.this,Home.class);
                MainActivity.this.startActivity(newActivityLoad);

                UserData ud = UserData.getInstance();
                ud.setUserID("123");

            }
        });
    }
}

class UserData {

    private static String userID;
    private static UserData single_instance = null;

    private UserData() {
    }

    public static UserData getInstance()
    {
        if (single_instance == null)
        {
            single_instance = new UserData();
        }
        return single_instance;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        UserData.userID = userID;
    }

}
