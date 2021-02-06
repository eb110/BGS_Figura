package com.example.bgs_figura;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bgs_figura.data.Earthquake;

import java.util.ArrayList;

public class SearchingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        Intent intent = getIntent();
        ArrayList<Earthquake> earthquakes = (ArrayList<Earthquake>) intent.getSerializableExtra("quake");
    }
}