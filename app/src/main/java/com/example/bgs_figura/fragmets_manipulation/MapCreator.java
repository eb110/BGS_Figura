package com.example.bgs_figura.fragmets_manipulation;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bgs_figura.MapsFragment;
import com.example.bgs_figura.R;
import com.example.bgs_figura.data.Earthquake;

import java.util.ArrayList;

public class MapCreator {

    FragmentManager manager;

    private ArrayList<Earthquake> earthquakes;
    public MapCreator(FragmentManager manager, ArrayList<Earthquake> earthquakes){
        this.earthquakes = earthquakes;
        this.manager = manager;
    }

    public void execute(){

        MapsFragment mf = new MapsFragment(earthquakes, 6);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_maps2, mf);
        transaction.commit();


    }
}
