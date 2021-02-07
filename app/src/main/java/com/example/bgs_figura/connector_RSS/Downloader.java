package com.example.bgs_figura.connector_RSS;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class Downloader extends AsyncTask<Void, Void, Object> {

    Context c;
    String urlAddress = "http://quakes.bgs.ac.uk/feeds/MhSeismology.xml";
    ListView lv;
    FragmentManager manager;
    Button btn;
    Handler handler;
    Runnable runnable;

    ProgressDialog pd;

    public Downloader(FragmentManager manager, Context c, ListView lv, Button btn, Handler handler,
                      Runnable runnable) {
        this.c = c;
        this.lv = lv;
        this.manager = manager;
        this.btn = btn;
        this.handler = handler;
        this.runnable = runnable;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Fetch Headlines");
        pd.setMessage("Fetching....Please wait");
        pd.show();
    }

    @Override
    protected Object doInBackground(Void... voids) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(Object data) {
        super.onPostExecute(data);
        pd.dismiss();

        if(data.toString().startsWith("Error")){
            Toast.makeText(c, data.toString(), Toast.LENGTH_SHORT).show();
        }else{
            //
            new RSSParser(manager, c, (InputStream) data, lv, btn, handler, runnable).execute();
        }
    }

    private Object downloadData(){

        Object connection = Connector.connect(urlAddress);
        if(connection.toString().startsWith("Error")){
            return connection.toString();
        }

        try{
            HttpURLConnection con = (HttpURLConnection) connection;
            InputStream is = new BufferedInputStream(con.getInputStream());
            return is;
        } catch (IOException e){
            e.printStackTrace();
            return ErrorTracker.IO_ERROR;
        }

    }
}
