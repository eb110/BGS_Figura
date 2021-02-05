package com.example.bgs_figura.fragmets_manipulation;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bgs_figura.MapsFragment;
import com.example.bgs_figura.R;
import com.example.bgs_figura.data.Earthquake;

import java.util.ArrayList;
import java.util.Arrays;

public class MapCreator extends AppCompatActivity {
    private Context c;
    private MapsFragment mf;
    private ArrayList<Earthquake> earthquakes;
    public MapCreator(Context c, MapsFragment mf, ArrayList<Earthquake> earthquakes){
        this.mf = mf;
        this.earthquakes = earthquakes;
        this.c = c;
    }

    public void execute(){
        Earthquake quake = new Earthquake();
        quake.setLatitude(1);
        quake.setLongitude(1);
        quake.setLocation("ciemna dupa");
        mf = new MapsFragment(new ArrayList<>(Arrays.asList(quake)), 7);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_maps2, mf);
      //  transaction.commit();


    }
}
