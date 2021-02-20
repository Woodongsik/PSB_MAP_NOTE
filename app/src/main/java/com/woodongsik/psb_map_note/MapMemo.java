package com.woodongsik.psb_map_note;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

import static com.woodongsik.psb_map_note.MapsActivity.database_count;

// This is Memo class which save a new memo user can creates on the Google Map API
// The MapMemo Class has many elements and functions to work

public class MapMemo {
    private String id;
    private double longitude;
    private double latitude;
    private String date;
    private String title = "";
    private String memo = "";

    public static int totalNumber = database_count;

    MapMemo(double x, double y, String memo, String date, String title){
        this.longitude = x;
        this.latitude = y;
        this.memo = memo;
        this.date = date;
        this.title = title;
        this.totalNumber ++;
        this.id = Integer.toString(totalNumber);
    }

    protected String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString(){
        return "1. GPS => Longitude: " + longitude + ", Latitude: " + latitude
                + "\n2. Date/Titme: " + date + "\n3. Title: " + title + "\n4. Memo:\n" + memo;
    }
}
