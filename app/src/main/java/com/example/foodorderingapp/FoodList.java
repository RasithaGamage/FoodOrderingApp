package com.example.foodorderingapp;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class FoodList extends AppCompatActivity {



    Handler handler;
    private Bundle bb = new Bundle();

    private NavigationView nv;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {

            }
        };

        mylistview = (ListView) findViewById(R.id.list);
        BackgroundWorker bw = new BackgroundWorker(FoodList.this,handler,mylistview);
        bw.execute("get_meal_list");

        dl = (DrawerLayout) findViewById(R.id.activity_FoodList);
        t = new ActionBarDrawerToggle(this, dl, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Meal List");
        nv = (NavigationView) findViewById(R.id.nv);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.settings:
                        Toast.makeText(FoodList.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent ac1 = new Intent(FoodList.this,Home.class);
                        startActivity(ac1);
                        break;
                    case R.id.mycart:
                        Toast.makeText(FoodList.this, "My Cart", Toast.LENGTH_SHORT).show();
                        Intent ac2 = new Intent(FoodList.this,Cart.class);
                        startActivity(ac2);
                        break;
                    case R.id.order:
                        Toast.makeText(FoodList.this, "Orders", Toast.LENGTH_SHORT).show();
                        Intent ac3 = new Intent(FoodList.this,Orders.class);
                        startActivity(ac3);
                        break;
                    case R.id.history:
                        Toast.makeText(FoodList.this, "History", Toast.LENGTH_SHORT).show();
                        Intent ac4 = new Intent(FoodList.this,OrderHistory.class);
                        startActivity(ac4);
                        break;
                    case R.id.logout: {
                        Toast.makeText(FoodList.this, "Logout", Toast.LENGTH_SHORT).show();
                        finishAffinity();
                    }
                    default:
                        return true;
                }
                return true;
            }
        });

//        handler = new Handler(Looper.getMainLooper()) {
//            @Override
//            public void handleMessage(Message inputMessage) {
//                bb = inputMessage.getData();
//                String str = bb.getString("do");
//
//
//            }
//        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
