package aaaa.dfs.model;

import com.google.android.gms.maps.model.LatLng;

public class Record {

    private int score;
    private String date;

    private LatLng latLng;


    public Record() {
    }

    public Record(int score, String date, LatLng latLng) {
        this.score = score;
        this.date = date;
        this.latLng = latLng;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public Record setLatLng(LatLng  LatLng) {
        this.latLng = LatLng;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Record setScore(int score) {
        this.score = score;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Record setDate(String date) {
        this.date = date;
        return this;
    }

    @Override
    public String toString() {
        return "Record{" +
                "score=" + score +
                ", date=" + date +
                '}';
    }


}
