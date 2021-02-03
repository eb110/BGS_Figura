package com.example.bgs_figura.connector_RSS;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bgs_figura.data.Earthquake;
import com.example.bgs_figura.list_manipulation.ListCreator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RSSParser extends AsyncTask<Void, Void, Boolean> {

    Context c;
    InputStream is;
    ListView lv;

    ProgressDialog pd;
   // ArrayList<String> headlines = new ArrayList<>();

    ArrayList<Earthquake> earthquakes = new ArrayList<>();

    public RSSParser(Context c, InputStream is, ListView lv) {
        this.c = c;
        this.is = is;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Parse Data");
        pd.setMessage("Parsing...Please wait");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.parseRSS();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);
        pd.dismiss();

        if(isParsed){
            //bind
            new ListCreator(c, earthquakes, lv).execute();
        }else{

            Toast.makeText(c, "Unable To Parse", Toast.LENGTH_SHORT).show();

        }
    }

    private Boolean parseRSS() {

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            //Set stream
            parser.setInput(is, null);
         //   headlines.clear();
            Earthquake earthquake = new Earthquake();
            String headline = null;
            int flag = 0;
            int event = parser.getEventType();

            do {
                String name = parser.getName();

                switch (event) {

                    case XmlPullParser.START_TAG:
                        if(name.equals("item")) earthquake = new Earthquake();
                        break;

                    case XmlPullParser.TEXT:
                        headline = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if(name.equals("item")) {
                            earthquakes.add(earthquake);
                        }
                        else if(name.equals("geo:long")) earthquake.setLongitude(Double.parseDouble(headline));
                        else if(name.equals("geo:lat")) earthquake.setLatitude(Double.parseDouble(headline));
                        else if(name.equals("pubDate")) earthquake.setDate(headline);
                        else if(name.equals("link")) earthquake.setUrl(headline);
                        else if(name.equals("description") && flag++ != 0){
                            String[] temp = headline.split(";");
                            earthquake.setLocation(temp[1].substring(temp[1].indexOf(":") + 1));
                            earthquake.setMagnitude(Double.parseDouble(temp[4].substring(temp[4].indexOf(":") + 2)));
                            earthquake.setDepth(Integer.parseInt(temp[3].replaceAll("\\D+", "")));
                        }
                        break;
                }
                event = parser.next();

            } while (event != XmlPullParser.END_DOCUMENT);

            return true;

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }
/*
    private void FillArray(){
        int l = earthquakes.size();
        for(int i = 0; i < l; i++){
            Earthquake ele = earthquakes.get(i);
         //   headlines.add(ele.getDate());
            headlines.add(ele.getLocation());
          //  headlines.add(ele.getUrl());
           // headlines.add("next kolo");
        }
    }*/
}