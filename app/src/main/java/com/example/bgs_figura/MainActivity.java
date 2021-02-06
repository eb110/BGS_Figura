package com.example.bgs_figura;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.bgs_figura.connector_RSS.Downloader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);
        final ListView lv = (ListView) findViewById(R.id.lv);
        FragmentManager manager = getSupportFragmentManager();

        new Downloader(manager,MainActivity.this, lv, btn).execute();


    }
}