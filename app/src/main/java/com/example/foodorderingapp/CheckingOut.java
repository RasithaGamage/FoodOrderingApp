package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RequiresApi;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_out);

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

