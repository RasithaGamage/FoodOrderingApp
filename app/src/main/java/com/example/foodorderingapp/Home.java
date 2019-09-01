package com.example.foodorderingapp;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private NavigationView nv;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dl = (DrawerLayout) findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ansell Cafeteria");
        nv = (NavigationView) findViewById(R.id.nv);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.settings:
                        Toast.makeText(Home.this, "My Account", Toast.LENGTH_SHORT).show();
                        Intent ac1 = new Intent(Home.this,Settings.class);
                        startActivity(ac1);
                        break;
                    case R.id.mycart:
                        Toast.makeText(Home.this, "My Cart", Toast.LENGTH_SHORT).show();
                        Intent ac2 = new Intent(Home.this,Cart.class);
                        startActivity(ac2);
                        break;
                    case R.id.order:
                        Toast.makeText(Home.this, "Orders", Toast.LENGTH_SHORT).show();
                        Intent ac3 = new Intent(Home.this,Orders.class);
                        startActivity(ac3);
                        break;
                    case R.id.history:
                        Toast.makeText(Home.this, "History", Toast.LENGTH_SHORT).show();
                        Intent ac4 = new Intent(Home.this,OrderHistory.class);
                        startActivity(ac4);
                        break;
                    case R.id.logout: {
                        Toast.makeText(Home.this, "Logout", Toast.LENGTH_SHORT).show();
                    }
                    default:
                        return true;
                }
                return true;
            }
        });

        ImageButton mealButton =  (ImageButton) findViewById(R.id.mealButton);

        mealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivityLoad = new Intent(Home.this,FoodList.class);
                startActivity(newActivityLoad);

            }
        });


        ImageButton snackButton =  (ImageButton) findViewById(R.id.snackButton);

        snackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivityLoad = new Intent(Home.this,SnackList.class);
                startActivity(newActivityLoad);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    private void checkTime(){
        ListView mylistview = (ListView) findViewById(R.id.list);
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {

            }
        };

        BackgroundWorker bw = new BackgroundWorker(this,handler,mylistview);
        bw.execute("check_time");
    }
}
