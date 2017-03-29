package com.example.rahil.asynctaskinternet;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * Created by rahil on 20-03-2017.
 */

public class ConnectInternetTask extends AsyncTask<String,Void,String> {
    Context ctx;

    public ConnectInternetTask(Context ct){
        ctx = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String s1 = strings[0];
        InputStream in;
        try{
            URL myUrl = new URL(s1);


            HttpURLConnection myConn = (HttpURLConnection) myUrl.openConnection();
            myConn.setReadTimeout(10000);
            myConn.setConnectTimeout(20000);
            myConn.setRequestMethod("GET");
            myConn.connect();
            int response = myConn.getResponseCode();
            Log.i("Hi",  "The response is: " + response);


            in = myConn.getInputStream();
            BufferedReader myBuf = new BufferedReader(new InputStreamReader(in));
            StringBuilder st = new StringBuilder();
            String line ="";

            while ((line = myBuf.readLine()) != null){
                st.append(line+"  \n");
            }
            myBuf.close();
            in.close();

            return st.toString();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        MainActivity.myText.setText(s);
    }
}
