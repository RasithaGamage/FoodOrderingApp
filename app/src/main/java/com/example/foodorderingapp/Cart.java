package com.example.foodorderingapp;
import android.app.Application;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

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
        setContentView(R.layout.activity_cart);

//        itemCount = (TextView) findViewById(R.id.itemCount);
          ShoppingCart s = ShoppingCart.getInstance();
//        itemCount.setText(Integer.toString(s.getShoppingCartArray().size()));

        mylistview = (ListView) findViewById(R.id.list);
        BackgroundWorker bw = new BackgroundWorker(Cart.this,handler,mylistview);

        rowItems = new ArrayList<RowItem>();

        for (int i = 0; i < s.getShoppingCartArray().size(); i++) {
            RowItem item = new RowItem(s.getShoppingCartArray().get(i).getPro_id(),s.getShoppingCartArray().get(i).getPro_name(),s.getShoppingCartArray().get(i).getImg(),s.getShoppingCartArray().get(i).getDetails(), "",s.getShoppingCartArray().get(i).getBuying_amount());
            item.setPrice(s.getShoppingCartArray().get(i).getPrice());
            rowItems.add(item);
        }

        CustomAdapter adapter = new CustomAdapter(this, rowItems);
        mylistview.setAdapter(adapter);

        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.d("++++onItemClick+++++ :","works");
                String pro_name = rowItems.get(position).getPro_name();
                Toast.makeText(Cart.this, "" + pro_name,Toast.LENGTH_SHORT).show();
            }
        });

        dl = (DrawerLayout) findViewById(R.id.activity_Cart);
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
                        Toast.makeText(Cart.this, "My Account", Toast.LENGTH_SHORT).show();
                        Intent ac1 = new Intent(Cart.this,Settings.class);
                        startActivity(ac1);
                        break;
                    case R.id.mycart:
                        Toast.makeText(Cart.this, "My Cart", Toast.LENGTH_SHORT).show();
                        Intent ac2 = new Intent(Cart.this,Cart.class);
                        startActivity(ac2);
                        break;
                    case R.id.order:
                        Toast.makeText(Cart.this, "Orders", Toast.LENGTH_SHORT).show();
                        Intent ac3 = new Intent(Cart.this,Orders.class);
                        startActivity(ac3);
                        break;
                    case R.id.history:
                        Toast.makeText(Cart.this, "History", Toast.LENGTH_SHORT).show();
                        Intent ac4 = new Intent(Cart.this,OrderHistory.class);
                        startActivity(ac4);
                        break;
                    case R.id.logout: {
                        Toast.makeText(Cart.this, "Logout", Toast.LENGTH_SHORT).show();
                    }
                    default:
                        return true;
                }
                return true;
            }
        });

        final TextView total_amount = (TextView)  findViewById(R.id.total_amount);

        Button checkoutbtn =(Button) findViewById(R.id.checkoutBtn);
        checkoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivityLoad = new Intent(Cart.this,CheckingOut.class);
                newActivityLoad.putExtra("total_amount",total_amount.getText().toString());
                Cart.this.startActivity(newActivityLoad);
            }
        });

    }
}

