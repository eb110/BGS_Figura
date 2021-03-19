/*
    Wladyslaw Figura
    Glasgow Caledonian University
    Mobile Technology 2021
    S1920048
 */
package com.example.bgs_figura;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.bgs_figura.connector_RSS.Downloader;

public class MainActivity extends AppCompatActivity {

    private final Handler handler = new Handler();
    private ListView lv;
    FragmentManager manager;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        lv = (ListView) findViewById(R.id.lv);
        manager = getSupportFragmentManager();
        startRepeating();
        }

    @Override
    protected void onResume() {
        super.onResume();
        startRepeating();
    }

    @Override
    protected void onPause(){
        super.onPause();
        handler.removeCallbacks(appRunnable);
    }

        public void startRepeating() {
            handler.postDelayed(appRunnable, 1000);
        }

        private Runnable appRunnable = new Runnable() {
            @Override
            public void run() {
                new Downloader(manager,MainActivity.this, lv, btn).execute();
                handler.postDelayed(this, 30000);
            }
        };

}