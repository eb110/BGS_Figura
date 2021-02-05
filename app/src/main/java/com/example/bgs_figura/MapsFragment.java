package com.example.bgs_figura;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bgs_figura.data.Earthquake;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsFragment extends Fragment {

    private int scale;
    private int l;
    private ArrayList<Earthquake>earthquakes = new ArrayList<>();
    public MapsFragment(ArrayList<Earthquake> quakes, int scale){
        this.earthquakes = quakes;
        this.scale = scale;
        this.l = earthquakes.size();
    }
    public MapsFragment(){}
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            GoogleMap mMap = googleMap;
            LatLng landMark = new LatLng(0,0);
            for(int i = 0; i < l; i++) {
                Earthquake quake = earthquakes.get(i);
                landMark = new LatLng(quake.getLatitude(), quake.getLongitude());
                mMap.addMarker(new MarkerOptions().position(landMark).title(quake.getLocation()));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(landMark,scale));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}