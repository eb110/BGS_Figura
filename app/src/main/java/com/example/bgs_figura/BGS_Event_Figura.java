package com.example.bgs_figura;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bgs_figura.data.Earthquake;

public class BGS_Event_Figura extends AppCompatActivity {

    TextView textView;
    Earthquake quake = new Earthquake();

    public void setQuake (Earthquake quake){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_g_s__event__figura);

        textView = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
        quake = (Earthquake) intent.getSerializableExtra("quake");

        textView.setText(quake.getLocation());
    }
}