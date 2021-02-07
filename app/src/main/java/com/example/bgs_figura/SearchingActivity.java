package com.example.bgs_figura;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bgs_figura.data.Earthquake;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SearchingActivity extends AppCompatActivity implements View.OnClickListener {

    EditText from_date;
    EditText to_date;
    final Calendar myCalendar = Calendar.getInstance();
    int flag = 0;
    Date df;
    Date dt;
    Button searchBtn;
    TextView north, south, east, west, magnitude, deep, shallow;

    ArrayList<Earthquake> earthquakes = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        Intent intent = getIntent();
        earthquakes = (ArrayList<Earthquake>) intent.getSerializableExtra("quake");

        from_date = (EditText) findViewById(R.id.date_from);
        to_date = (EditText) findViewById(R.id.date_to);

        north = (TextView) findViewById(R.id.north);
        south = (TextView) findViewById(R.id.south);
        east = (TextView) findViewById(R.id.east);
        west = (TextView) findViewById(R.id.west);
        magnitude = (TextView) findViewById(R.id.magnitude);
        deep = (TextView) findViewById(R.id.deep);
        shallow = (TextView) findViewById(R.id.shallow);

        searchBtn = (Button) findViewById(R.id.search_btn);

        from_date.setOnClickListener(this);
        to_date.setOnClickListener(this);

        searchBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.equals(searchBtn)) updateResult();
        else {
            if (v == to_date) flag = 1;
            else flag = 0;

            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    myCalendar.set(Calendar.HOUR_OF_DAY, 0);
                    myCalendar.set(Calendar.SECOND, 0);
                    myCalendar.set(Calendar.MINUTE, 0);
                    myCalendar.set(Calendar.MILLISECOND, 0);
                    updateLabel();
                }
            };

            new DatePickerDialog(SearchingActivity.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

    private ArrayList<Earthquake> updateQuakes(){
        ArrayList<Earthquake> result = new ArrayList<>();
        int l = earthquakes.size();

        if(df == null) return null;
        if(dt == null) dt = df;
        if(dt.before(df)) return null;

        for(int i = 0; i < l; i++){
            String date_S = earthquakes.get(i).getDate();
            date_S = date_S.substring(date_S.indexOf(' ') + 1, date_S.lastIndexOf(' '));
            Date temp = new Date(date_S);
            if(temp.before(df) || temp.after(dt))continue;
            result.add(earthquakes.get(i));
        }

        return result;
    }

    private void updateResult(){
        ArrayList<Earthquake> earthquakes1 = updateQuakes();
        if(earthquakes1 == null || earthquakes1.size() == 0)return;
        double wst = 1000;
        String west_S = "";
        double est = -1000;
        String east_S = "";
        double nth = -1000;
        String north_S = "";
        double sth = 1000;
        String south_S = "";
        double mgnt = 0;
        int dpth = 0;
        int shlw = Integer.MAX_VALUE;
        int l = earthquakes1.size();
        for(int i = 0; i < l; i++){
            Earthquake quake = earthquakes1.get(i);
            if(quake.getMagnitude() > mgnt)mgnt = quake.getMagnitude();
            if(quake.getDepth() > dpth)dpth = quake.getDepth();
            if(quake.getDepth() < shlw)shlw = quake.getDepth();
            if(quake.getLongitude() < wst){
                wst = quake.getLongitude();
                west_S = quake.getLocation();
            }
            if(quake.getLongitude() > est){
                est = quake.getLongitude();
                east_S = quake.getLocation();
            }
            if(quake.getLatitude() > nth){
                nth = quake.getLatitude();
                north_S = quake.getLocation();
            }
            if(quake.getLatitude() < sth){
                sth = quake.getLatitude();
                south_S = quake.getLocation();
            }
        }
        magnitude.setText("Maximum magnitude: " + mgnt);
        deep.setText("Maximum depth: " + dpth + "km");
        shallow.setText("Minimum depth: " + shlw + "km");
        west.setText("Furthest west earthquake: " + west_S);
        east.setText("Furthest east earthquake: " + east_S);
        north.setText("Furthest north earthquake: " + north_S);
        south.setText("Furthest south earthquake: " + south_S);
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        if (flag == 0){
            from_date.setText(sdf.format(myCalendar.getTime()));
            df = myCalendar.getTime();
        }
        else {
            to_date.setText(sdf.format(myCalendar.getTime()));
            dt = myCalendar.getTime();
        }
    }

}