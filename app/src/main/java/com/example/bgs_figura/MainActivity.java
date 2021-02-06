package com.example.bgs_figura;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

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
                int a = R.id.fragment_maps2;

                FragmentManager manager = getSupportFragmentManager();

                 new Downloader(manager,MainActivity.this, urlAddress, lv).execute();

            }
        });
    }
}