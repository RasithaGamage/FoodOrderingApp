package com.example.foodorderingapp;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FoodList extends AppCompatActivity {

    String[] place;
    String[] member_names;
    TypedArray profile_pics;
    String[] statues;
    String[] contactType;

    List<RowItem> rowItems;
    ListView mylistview;

    private NavigationView nv;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        dl = (DrawerLayout) findViewById(R.id.activity_FoodList);
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
                    case R.id.account:
                        Toast.makeText(FoodList.this, "My Account", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:
                        Toast.makeText(FoodList.this, "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mycart:
                        Toast.makeText(FoodList.this, "My Cart", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout: {
                        Toast.makeText(FoodList.this, "Logged out", Toast.LENGTH_SHORT).show();
                    }
                    default:
                        return true;
                }
                return true;
            }
        });


        rowItems = new ArrayList<RowItem>();
        place = getResources().getStringArray(R.array.place);
        member_names = getResources().getStringArray(R.array.Member_names);
        profile_pics = getResources().obtainTypedArray(R.array.profile_pics);
        statues = getResources().getStringArray(R.array.statues);
        contactType = getResources().getStringArray(R.array.contactType);

        for (int i = 0; i < member_names.length; i++) {
            RowItem item = new RowItem(place[i],member_names[i],
                    profile_pics.getResourceId(i, -1), statues[i],
                    contactType[i]);
            rowItems.add(item);
        }
        mylistview = (ListView) findViewById(R.id.list);
        CustomAdapter adapter = new CustomAdapter(this, rowItems);
        mylistview.setAdapter(adapter);

        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String member_name = rowItems.get(position).getMember_name();
                Toast.makeText(getApplicationContext(), "" + member_name,
                        Toast.LENGTH_SHORT).show();
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
