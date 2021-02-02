package com.example.bgs_figura;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bgs_figura.connector_RSS.Downloader;

public class MainActivity extends AppCompatActivity {

    final static String urlAddress = "http://quakes.bgs.ac.uk/feeds/MhSeismology.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        final ListView lv = (ListView) findViewById(R.id.lv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Downloader(MainActivity.this, urlAddress, lv).execute();

            }
        });



    }




}