package com.example.foodorderingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        payByCashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivityLoad = new Intent(CheckingOut.this,Home.class);
                CheckingOut.this.startActivity(newActivityLoad);
                Toast.makeText(CheckingOut.this,"Your will pay by cash at the counter",Toast.LENGTH_LONG).show();
            }
        });

        DeductSalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivityLoad = new Intent(CheckingOut.this,Home.class);
                CheckingOut.this.startActivity(newActivityLoad);
                Toast.makeText(CheckingOut.this,"Amount will be deducted from your salary",Toast.LENGTH_LONG).show();
            }
        });
    }

}

