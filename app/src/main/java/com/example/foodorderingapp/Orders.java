package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Orders extends AppCompatActivity {

    TextView itemCount;

    private NavigationView nv;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;

    ListView mylistview;
    Handler handler;
    List<RowItem> rowItems;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {

            }
        };

        mylistview = (ListView) findViewById(R.id.list);
        BackgroundWorker bw = new BackgroundWorker(Orders.this,handler,mylistview);
        UserData ud = UserData.getInstance();
        bw.execute("get_orders_list",ud.getUserID(),"get_orders");

        dl = (DrawerLayout) findViewById(R.id.activity_Orders);
        t = new ActionBarDrawerToggle(this, dl, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Orders");
        nv = (NavigationView) findViewById(R.id.nv);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.settings:
                        Toast.makeText(Orders.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent ac1 = new Intent(Orders.this,Home.class);
                        startActivity(ac1);
                        break;
                    case R.id.mycart:
                        Toast.makeText(Orders.this, "My Cart", Toast.LENGTH_SHORT).show();
                        Intent ac2 = new Intent(Orders.this,Cart.class);
                        startActivity(ac2);
                        break;
                    case R.id.order:
                        Toast.makeText(Orders.this, "Orders", Toast.LENGTH_SHORT).show();
                        Intent ac3 = new Intent(Orders.this,Orders.class);
                        startActivity(ac3);
                        break;
                    case R.id.history:
                        Toast.makeText(Orders.this, "History", Toast.LENGTH_SHORT).show();
                        Intent ac4 = new Intent(Orders.this,OrderHistory.class);
                        startActivity(ac4);
                        break;
                    case R.id.logout: {
                        Toast.makeText(Orders.this, "Logout", Toast.LENGTH_SHORT).show();
                        finishAffinity();
                    }
                    default:
                        return true;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
