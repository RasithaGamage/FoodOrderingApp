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
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SnackList extends AppCompatActivity {


    private NavigationView nv;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;

    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;

    Handler handler;
    private Bundle bb = new Bundle();
    ListView mylistview;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_list);

        EditText editText= (EditText) findViewById(R.id.searchBox);
        listView = (ListView) findViewById(R.id.list);

        dl = (DrawerLayout) findViewById(R.id.activity_SnackList);
        t = new ActionBarDrawerToggle(this, dl, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Snack List");
        nv = (NavigationView) findViewById(R.id.nv);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.settings:
                        Toast.makeText(SnackList.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent ac1 = new Intent(SnackList.this,Home.class);
                        startActivity(ac1);
                        break;
                    case R.id.mycart:
                        Toast.makeText(SnackList.this, "My Cart", Toast.LENGTH_SHORT).show();
                        Intent ac2 = new Intent(SnackList.this,Cart.class);
                        startActivity(ac2);
                        break;
                    case R.id.order:
                        Toast.makeText(SnackList.this, "Orders", Toast.LENGTH_SHORT).show();
                        Intent ac3 = new Intent(SnackList.this,Orders.class);
                        startActivity(ac3);
                        break;
                    case R.id.history:
                        Toast.makeText(SnackList.this, "History", Toast.LENGTH_SHORT).show();
                        Intent ac4 = new Intent(SnackList.this,OrderHistory.class);
                        startActivity(ac4);
                        break;
                    case R.id.logout: {
                        Toast.makeText(SnackList.this, "Logout", Toast.LENGTH_SHORT).show();
                        finishAffinity();
                    }
                    default:
                        return true;
                }
                return true;
            }
        });

        initList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initList(){

//        listItems = new ArrayList<>(Arrays.asList(items));
//        adapter = new ArrayAdapter<String>(this, R.layout.list_item,R.id.text,listItems);

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
            }
        };
        mylistview = (ListView) findViewById(R.id.list);
        BackgroundWorker bw = new BackgroundWorker(SnackList.this,handler,mylistview);
        bw.execute("get_snack_list");

    }


}
