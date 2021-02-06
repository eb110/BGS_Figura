package com.example.bgs_figura.fragmets_manipulation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.bgs_figura.BGS_Event_Figura;
import com.example.bgs_figura.data.Earthquake;

import java.util.ArrayList;
import java.util.Comparator;

public class ListCreator extends AsyncTask<Void, Void, Boolean> {

    Context c;
    ListView lv;

    ArrayList<Earthquake> earthquakes = new ArrayList<>();
    Earthquake quake = new Earthquake();
    ArrayAdapter arrayAdapter;

    ArrayList<String> headlines = new ArrayList<>();

    ProgressDialog pd;

    public ListCreator(Context c, ArrayList<Earthquake> earthquakes, ListView lv) {
        this.c = c;
        this.earthquakes = earthquakes;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("List generation");
        pd.setMessage("Generating list...Please wait");
        pd.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.generateList();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Boolean generateList(){

        earthquakes.sort(new Comparator<Earthquake>() {
            @Override
            public int compare(Earthquake o1, Earthquake o2) {
                if(o1.getMagnitude() < o2.getMagnitude()) return 1;
                if(o1.getMagnitude() > o2.getMagnitude()) return -1;
                return 0;
            }
        });

        int l = earthquakes.size();
        for(int i = 0; i < l; i++){
            Earthquake ele = earthquakes.get(i);
            headlines.add("" + ele.getMagnitude() + " Richter Scale " + ele.getLocation());
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean check) {
        super.onPostExecute(check);
        pd.dismiss();

        if(check){


            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(c,
                    android.R.layout.simple_list_item_1, headlines) {
                @Override
                public View getView(int i, View convertView, ViewGroup parent) {
                    View view = super.getView(i, convertView, parent);
                        quake = earthquakes.get(i);
                        if(quake.getMagnitude() >= 3.0)view.setBackgroundColor(Color.RED);
                        else if(quake.getMagnitude() >= 2.0)view.setBackgroundColor(Color.MAGENTA);
                        else if(quake.getMagnitude() >= 1.0)view.setBackgroundColor(Color.YELLOW);
                        else view.setBackgroundColor(Color.GREEN);
                    return view;
                }
            };

            lv.setAdapter(arrayAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                    quake = earthquakes.get(i);
                    Toast.makeText(c, quake.getLocation(), Toast.LENGTH_SHORT).show();
                    openActivity2(quake);
                }
            });
        }else{

            Toast.makeText(c, "Unable To Parse", Toast.LENGTH_SHORT).show();

        }
    }

    private void openActivity2(Earthquake quake) {
        Intent intent = new Intent(c, BGS_Event_Figura.class);
        intent.putExtra("quake", quake);
        c.startActivity(intent);
    }

    public Earthquake getQuake(){
        return quake;
    }

}
