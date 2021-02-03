package com.example.bgs_figura.list_manipulation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bgs_figura.BGS_Event_Figura;
import com.example.bgs_figura.data.Earthquake;

import java.util.ArrayList;

public class ListCreator extends AsyncTask<Void, Void, Boolean> {

    Context c;
    ListView lv;

    ArrayList<Earthquake> earthquakes = new ArrayList<>();
    Earthquake quake = new Earthquake();

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

    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.generateList();
    }

    private Boolean generateList(){
        int l = earthquakes.size();
        for(int i = 0; i < l; i++){
            Earthquake ele = earthquakes.get(i);
            //   headlines.add(ele.getDate());
            headlines.add(ele.getLocation());
            //  headlines.add(ele.getUrl());
            // headlines.add("next kolo");
        }


        return true;
    }

    @Override
    protected void onPostExecute(Boolean check) {
        super.onPostExecute(check);


        if(check){
            //bind
            pd.dismiss();
            lv.setAdapter(new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1, headlines));
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
