/*
    Wladyslaw Figura
    Glasgow Caledonian University
    Mobile Technology 2021
    S1920048
 */
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
                double check = quake.getMagnitude();

                if(check >= 3.0) {
                    mMap.addMarker(new MarkerOptions()
                            .position(landMark)
                            .title(quake.getLocation())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                }
                else if(check >= 2.0) {
                    mMap.addMarker(new MarkerOptions()
                            .position(landMark)
                            .title(quake.getLocation())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
                }
                else if(check >= 1.0) {
                    mMap.addMarker(new MarkerOptions()
                            .position(landMark)
                            .title(quake.getLocation())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                }
                else {
                    mMap.addMarker(new MarkerOptions()
                            .position(landMark)
                            .title(quake.getLocation())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                }

               // mMap.addMarker(new MarkerOptions().position(landMark).icon().title(quake.getLocation()));
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