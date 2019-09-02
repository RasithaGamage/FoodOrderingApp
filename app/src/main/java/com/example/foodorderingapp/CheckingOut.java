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
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CheckingOut extends AppCompatActivity {
    private NavigationView nv;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_out);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payment");
        nv = (NavigationView) findViewById(R.id.nv);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.settings:
                        Toast.makeText(CheckingOut.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent ac1 = new Intent(CheckingOut.this,Home.class);
                        startActivity(ac1);
                        break;
                    case R.id.mycart:
                        Toast.makeText(CheckingOut.this, "My Cart", Toast.LENGTH_SHORT).show();
                        Intent ac2 = new Intent(CheckingOut.this,Cart.class);
                        startActivity(ac2);
                        break;
                    case R.id.order:
                        Toast.makeText(CheckingOut.this, "Orders", Toast.LENGTH_SHORT).show();
                        Intent ac3 = new Intent(CheckingOut.this,Orders.class);
                        startActivity(ac3);
                        break;
                    case R.id.history:
                        Toast.makeText(CheckingOut.this, "History", Toast.LENGTH_SHORT).show();
                        Intent ac4 = new Intent(CheckingOut.this,OrderHistory.class);
                        startActivity(ac4);
                        break;
                    case R.id.logout: {
                        Toast.makeText(CheckingOut.this, "Logout", Toast.LENGTH_SHORT).show();
                    }
                    default:
                        return true;
                }
                return true;
            }
        });




        Button payByCashBtn = (Button) findViewById(R.id.payByCashBtn);
        Button DeductSalBtn = (Button) findViewById(R.id.DeductSalBtn);
        TextView tv =  (TextView) findViewById(R.id.txt4) ;
        Intent i = getIntent();
        tv.setText(i.getStringExtra("total_amount") );

        final ShoppingCart s = ShoppingCart.getInstance();

        payByCashBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {


                Handler handler = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message inputMessage) {

                    }
                };


                //YYYY-MM-DD hh:mm:ss
                String pattern = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date_time = simpleDateFormat.format(new Date());


                UserData ud = UserData.getInstance();


                BackgroundWorker bw = new BackgroundWorker(CheckingOut.this,handler,(ListView) findViewById(R.id.list) );
                bw.execute("place_order",ud.getUserID(),date_time,"cash");
                //params[0] place_order
                //params[1] emp_id
                //params[2} date_time
                //params[3} salary or cash

                Intent newActivityLoad = new Intent(CheckingOut.this,Home.class);
                CheckingOut.this.startActivity(newActivityLoad);
                Toast.makeText(CheckingOut.this,"Your will pay by cash at the counter",Toast.LENGTH_LONG).show();
            }
        });

        DeductSalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Handler handler = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message inputMessage) {

                    }
                };

                //YYYY-MM-DD hh:mm:ss
                String pattern = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date_time = simpleDateFormat.format(new Date());

                UserData ud = UserData.getInstance();

                BackgroundWorker bw = new BackgroundWorker(CheckingOut.this,handler,(ListView) findViewById(R.id.list) );
                bw.execute("place_order",ud.getUserID(),date_time,"salary");
                //params[0] place_order
                //params[1] emp_id
                //params[2} date_time
                //params[3} salary or cash

                Intent newActivityLoad = new Intent(CheckingOut.this,Home.class);
                CheckingOut.this.startActivity(newActivityLoad);
                Toast.makeText(CheckingOut.this,"Amount will be deducted from your salary",Toast.LENGTH_LONG).show();
            }
        });
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if(t.onOptionsItemSelected(item))
//            return true;
//
//        return super.onOptionsItemSelected(item);
//    }
}

