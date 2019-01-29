package com.rezapp.katalogfilm;

import org.json.JSONObject;

import java.text.DecimalFormat;

public class MovieItems {

    private int id;
    private String judul;
    private String deskripsi;
    private String poster;

    public MovieItems(JSONObject object) {

        try {
            int id = object.getInt("id");
            String title = object.getString("original_title");
            String overview = object.getString("overview");
            String poster = object.getString("poster_path");

            this.id = id;
            this.judul = title;
            this.deskripsi = overview;
            this.poster = poster;

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getPoster() {
        return poster;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJudul(String name) {
        this.judul = name;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
