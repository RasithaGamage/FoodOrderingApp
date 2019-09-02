package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class OrderHistory extends AppCompatActivity {
    private NavigationView nv;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;

    ListView mylistview;
    Handler handler;
    List<RowItem> rowItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);


        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {

            }
        };
        mylistview = (ListView) findViewById(R.id.list);
        BackgroundWorker bw = new BackgroundWorker(OrderHistory.this,handler,mylistview);
        UserData ud = UserData.getInstance();
        bw.execute("get_orders_list",ud.getUserID(),"get_history");

        dl = (DrawerLayout) findViewById(R.id.activity_OrderHistory);
        t = new ActionBarDrawerToggle(this, dl, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Order History");
        nv = (NavigationView) findViewById(R.id.nv);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.settings:
                        Toast.makeText(OrderHistory.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent ac1 = new Intent(OrderHistory.this,Home.class);
                        startActivity(ac1);
                        break;
                    case R.id.mycart:
                        Toast.makeText(OrderHistory.this, "My Cart", Toast.LENGTH_SHORT).show();
                        Intent ac2 = new Intent(OrderHistory.this,Cart.class);
                        startActivity(ac2);
                        break;
                    case R.id.order:
                        Toast.makeText(OrderHistory.this, "Orders", Toast.LENGTH_SHORT).show();
                        Intent ac3 = new Intent(OrderHistory.this,Orders.class);
                        startActivity(ac3);
                        break;
                    case R.id.history:
                        Toast.makeText(OrderHistory.this, "History", Toast.LENGTH_SHORT).show();
                        Intent ac4 = new Intent(OrderHistory.this,OrderHistory.class);
                        startActivity(ac4);
                        break;
                    case R.id.logout: {
                        Toast.makeText(OrderHistory.this, "Logout", Toast.LENGTH_SHORT).show();
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
