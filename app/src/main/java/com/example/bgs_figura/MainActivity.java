package com.example.bgs_figura;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bgs_figura.connector_RSS.Downloader;
import com.example.bgs_figura.data.Earthquake;

public class MainActivity extends AppCompatActivity {

    final static String urlAddress = "http://quakes.bgs.ac.uk/feeds/MhSeismology.xml";
    private MapsFragment mf;
    Earthquake quake = new Earthquake();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quake.setLatitude(1);
        quake.setLongitude(1);
        quake.setLocation("ciemna dupa");

        Button button = (Button) findViewById(R.id.button);

        final ListView lv = (ListView) findViewById(R.id.lv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Downloader(MainActivity.this, urlAddress, lv, mf).execute();

                // czekam sobie na downloader az skonczy

           //     mf = new MapsFragment(new ArrayList<>(Arrays.asList(quake)), 7);
           //     FragmentManager manager = getSupportFragmentManager();
          //      FragmentTransaction transaction = manager.beginTransaction();
           //     transaction.replace(R.id.fragment_maps2, mf);
           //     transaction.commit();
            }
        });



    }




}