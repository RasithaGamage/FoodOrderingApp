package com.example.foodorderingapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

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

public class BackgroundWorker extends AsyncTask<String,Void,Void> {

    Context context;

    BackgroundWorker (Context ctx){
        context = ctx;
    }

    @Override
    protected Void doInBackground(String... params) {

        HttpClient Client = new DefaultHttpClient();
        HttpPost httppost ;

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

        if (params[0].equals("login")){
            nameValuePairs.add(new BasicNameValuePair("emp_id", params[1]));
            nameValuePairs.add(new BasicNameValuePair("pw", params[2]));
            httppost = new HttpPost("http://10.0.2.2:8080/ansell_cafeteria/auth.php");

            try {

                String SetServerString ;
                HttpClient httpclient = new DefaultHttpClient();
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                SetServerString = httpclient.execute(httppost, responseHandler);

                Log.d("+++++++++++++++++ :",SetServerString);
                Log.d("+++++++++++++++++ :","::::Response end::::");

                if(SetServerString.length()>0){
                    Intent newActivityLoad = new Intent(context,Home.class);
                    context.startActivity(newActivityLoad);
                }

            }  catch(Exception ex) {
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
