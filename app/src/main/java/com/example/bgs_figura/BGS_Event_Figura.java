package com.example.bgs_figura;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bgs_figura.data.Earthquake;
import com.google.android.gms.maps.GoogleMap;

public class BGS_Event_Figura extends AppCompatActivity {

    TextView tv_date;
    TextView tv_magnitude;
    TextView tv_depth;
    TextView tv_latlong;
    Earthquake quake = new Earthquake();

    private GoogleMap mMap;

    public void setQuake (Earthquake quake){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_g_s__event__figura);

        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_magnitude = (TextView) findViewById(R.id.tv_magnitude);
        tv_depth = (TextView) findViewById(R.id.tv_depth);
        tv_latlong = (TextView) findViewById(R.id.tv_latlong);

        Intent intent = getIntent();
        quake = (Earthquake) intent.getSerializableExtra("quake");

        tv_date.setText(quake.getDate());
        tv_magnitude.setText("Magnitude: " + quake.getMagnitude() + " Richter Scale");
        tv_depth.setText(("Depth: " + quake.getDepth() + " km"));
        tv_latlong.setText(("Latitude: " + quake.getLatitude() + " Longitude: " +quake.getLongitude()));

    }


}