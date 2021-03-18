/*
    Wladyslaw Figura
    Glasgow Caledonian University
    Mobile Technology 2021
    S1920048
 */
package com.example.bgs_figura.data;

import java.io.Serializable;

public class Earthquake implements Serializable {
    private String location;
    private double longitude;
    private double latitude;
    private double magnitude;
    private int depth;
    private String date;
    private String url;

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getMagnitude() {
        return magnitude;
    }
    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public int getDepth() {
        return depth;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
