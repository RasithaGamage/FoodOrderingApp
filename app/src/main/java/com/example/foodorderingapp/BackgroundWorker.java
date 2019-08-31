package com.example.foodorderingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.finishAffinity;

public class BackgroundWorker extends AsyncTask<String,Void,Void> {

    Context context;
    Handler handler;
    ArrayList <Product> productList;
    List<RowItem> rowItems;
    ListView  mylistview;

    BackgroundWorker (Context ctx,Handler handler,ListView mylistview){
        context = ctx;
        this.handler = handler;
        this.mylistview = mylistview;

    }


    @Override
    protected Void doInBackground(String... params) {

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

        if (params[0].equals("login")){

            HttpPost httppost ;
            //httppost = new HttpPost("http://10.0.2.2:8080/ansell_cafeteria/auth.php");
            httppost = new HttpPost("http://imssa.lk/ansell_cafeteria/auth.php");
            // httppost = new HttpPost("http://localhost:8080/ansell_cafeteria/auth.php");
            nameValuePairs.add(new BasicNameValuePair("emp_id", params[1]));
            nameValuePairs.add(new BasicNameValuePair("pw", params[2]));

            try {

                String SetServerString ;
                HttpClient httpclient = new DefaultHttpClient();
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                SetServerString = httpclient.execute(httppost, responseHandler);

                Log.d("+++++++++++++++++ :",SetServerString);
                Log.d("+++++++++++++++++ :","::::Response end::::");

                if(SetServerString.equals("Login Successful") ){
                    Intent newActivityLoad = new Intent(context,Home.class);
                    context.startActivity(newActivityLoad);
                }

                if(SetServerString.equals("No value found") )
                {
                    Message m = Message.obtain(); //get null message
                    Bundle b = new Bundle();
                    b.putString("invalid_credentials", "Invalid Credentials !");
                    m.setData(b);
                    //use the handler to send message
                    handler.sendMessage(m);
                }

            }  catch(Exception ex) {
                Log.d("+++++++++++++++++ :",ex.toString());
            }
        }

        if (params[0].equals("get_meal_list")){
            HttpPost httppost ;
            httppost = new HttpPost("http://imssa.lk/ansell_cafeteria/get_meals.php");
            try {

                final String SetServerString ;
                HttpClient httpclient = new DefaultHttpClient();
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                SetServerString = httpclient.execute(httppost, responseHandler);

               // Log.d("+++++++++++++++++ :",SetServerString);
                Log.d("+++++++++++++++++ :","::::Response end::::");

//                Message m = Message.obtain(); //get null message
//                Bundle b = new Bundle();
//                b.putString("meal_list", SetServerString);
//                m.setData(b);
//                //use the handler to send message
//                handler.sendMessage(m);

                handler.post(new Runnable() {

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {

                        if(SetServerString.length()>0 && !SetServerString.equals("No value found")){

                            //Toast.makeText(context,"what ever",Toast.LENGTH_SHORT).show();
                            Gson g = new Gson();
                            productList= g.fromJson(SetServerString, new TypeToken<List<Product>>(){}.getType());
                            //ArrayList <Product> productList = new ArrayList<>();
                            //Log.d("++++getPro_name+++++ :",productList.get(0).getPro_name());
                            //Toast.makeText(context,SetServerString,Toast.LENGTH_SHORT).show();

                            rowItems = new ArrayList<RowItem>();

                            for (int i = 0; i < productList.size(); i++) {
                                RowItem item = new RowItem(productList.get(i).getPro_id(),productList.get(i).getPro_name(),productList.get(i).getImg(), productList.get(i).getDetails(),"" ,0);
                                item.setPrice(productList.get(i).getPrice());
                                rowItems.add(item);
                            }

                            CustomAdapter adapter = new CustomAdapter(context, rowItems);
                            mylistview.setAdapter(adapter);

                            mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position,long id)
                                {
                                    Log.d("++++onItemClick+++++ :","works");
                                    String pro_name = rowItems.get(position).getPro_name();
                                    Toast.makeText(context, "" + pro_name,Toast.LENGTH_SHORT).show();
                                }
                            });

//                        Message m = Message.obtain(); //get null message
//                        Bundle b = new Bundle();
//                        b.putString("do", "do");
//                        m.setData(b);
//                        handler.sendMessage(m);
                        }
                    }
                });
            }  catch(Exception ex) {
                Log.d("+++++++++++++++++ :",ex.toString());
            }
        }

        if (params[0].equals("get_snack_list")){
            HttpPost httppost ;
            httppost = new HttpPost("http://imssa.lk/ansell_cafeteria/get_snacks.php");
            try {

                final String SetServerString ;
                HttpClient httpclient = new DefaultHttpClient();
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                SetServerString = httpclient.execute(httppost, responseHandler);

               // Log.d("+++++++++++++++++ :",SetServerString+"::::Response end::::");

                handler.post(new Runnable() {

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {

                        if(SetServerString.length()>0 && !SetServerString.equals("No value found")){
                            //Toast.makeText(context,"what ever",Toast.LENGTH_SHORT).show();
                            Gson g = new Gson();
                            productList= g.fromJson(SetServerString, new TypeToken<List<Product>>(){}.getType());

                            rowItems = new ArrayList<RowItem>();

                            for (int i = 0; i < productList.size(); i++) {
                                RowItem item = new RowItem(productList.get(i).getPro_id(),productList.get(i).getPro_name(),productList.get(i).getImg(), productList.get(i).getDetails(), "",0);
                                item.setPrice(productList.get(i).getPrice());
                                rowItems.add(item);
                            }

                            CustomAdapter adapter = new CustomAdapter(context, rowItems);
                            mylistview.setAdapter(adapter);

                            mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position,long id)
                                {
                                    Log.d("++++onItemClick+++++ :","works");
                                    String pro_name = rowItems.get(position).getPro_name();
                                    Toast.makeText(context, "" + pro_name,Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }  catch(Exception ex) {
                Log.d("+++++++++++++++++ :",ex.toString());
            }
        }

        if (params[0].equals("place_order")){

            //params[0] place_order
            //params[1] emp_id
            //params[2} date_time
            //params[3} salary or cash

            String paying_method =""; //deduct from salary ---- or --- pay by cash

            HttpPost httppost ;
            httppost = new HttpPost("http://imssa.lk/ansell_cafeteria/updatedb.php");

            final ShoppingCart s = ShoppingCart.getInstance();
            Gson gson = new Gson();
            String shoppingCart_json = gson.toJson(s.getShoppingCartArray());
            Log.d("++++++++json++++++++:",shoppingCart_json);

           nameValuePairs.add(new BasicNameValuePair("emp_id", params[1]));
           nameValuePairs.add(new BasicNameValuePair("date_time", params[2]));
           nameValuePairs.add(new BasicNameValuePair("product_list", shoppingCart_json));
           nameValuePairs.add(new BasicNameValuePair("payment_type", params[3]));

            try {
                final String SetServerString ;
                HttpClient httpclient = new DefaultHttpClient();
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                SetServerString = httpclient.execute(httppost, responseHandler);

                handler.post(new Runnable() {

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {

                        if(SetServerString.length()>0){
                            Log.d("+++++++ret+++++++++:",SetServerString);
                        }

                        s.getShoppingCartArray().clear();
                    }
                });

            }catch (Exception ex) {
                Log.d("+++++++Exc++++++++++ :",ex.toString());
            }
        }

        if (params[0].equals("get_orders_list")){
            HttpPost httppost ;
            httppost = new HttpPost("http://imssa.lk/ansell_cafeteria/get_orders.php");
            nameValuePairs.add(new BasicNameValuePair("emp_id", params[1]));

            try {
                final String SetServerString ;
                HttpClient httpclient = new DefaultHttpClient();
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                SetServerString = httpclient.execute(httppost, responseHandler);

                Log.d("+++++++++++++++++ :","::::Response end::::");

                handler.post(new Runnable() {

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {

                        if(SetServerString.length()>0 && !SetServerString.equals("No value found")){

                            Gson g = new Gson();
                            productList= g.fromJson(SetServerString, new TypeToken<List<Product>>(){}.getType());

                            rowItems = new ArrayList<RowItem>();

                            for (int i = 0; i < productList.size(); i++) {
                                RowItem item = new RowItem(productList.get(i).getPro_id(),productList.get(i).getPro_name(),productList.get(i).getImg(), productList.get(i).getDetails(),productList.get(i).getBrand() ,0);
                                item.setPrice(productList.get(i).getPrice());
                                item.setAmount(productList.get(i).getQty());
                                rowItems.add(item);
                            }

                            CustomAdapter adapter = new CustomAdapter(context, rowItems);
                            mylistview.setAdapter(adapter);

                            mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position,long id)
                                {
                                    Log.d("++++onItemClick+++++ :","works");
                                    String pro_name = rowItems.get(position).getPro_name();
                                    Toast.makeText(context, "" + pro_name,Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                });
            }  catch(Exception ex) {
                Log.d("+++++++++++++++++ :",ex.toString());
            }
        }

        if (params[0].equals("remove_order")){
            HttpPost httppost ;
            httppost = new HttpPost("http://imssa.lk/ansell_cafeteria/remove_order.php");
            nameValuePairs.add(new BasicNameValuePair("order_id", params[1]));
            nameValuePairs.add(new BasicNameValuePair("prod_id", params[2]));

            try {
                final String SetServerString ;
                HttpClient httpclient = new DefaultHttpClient();
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                SetServerString = httpclient.execute(httppost, responseHandler);

                Log.d("+++++++++++++++++ :",SetServerString+"::::Response end::::");

                handler.post(new Runnable() {  //You should use Handler.post() whenever you want to do operations in the UI thread.

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {

                        Intent in = new Intent(context,context.getClass());
                        finishAffinity((Activity) context);
                        context.startActivity(in);
                    }
                });

            }catch (Exception ex) {
                Log.d("+++++++++++++++++ :",ex.toString());
            }


        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
