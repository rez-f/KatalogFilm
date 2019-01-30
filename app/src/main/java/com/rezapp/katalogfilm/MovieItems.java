package com.rezapp.katalogfilm;

import org.json.JSONObject;

public class MovieItems {

    private String judul;
    private String sinopsis;
    private String rating;
    private String rilis;
    private String popularitas;
    private String poster;

    public MovieItems(JSONObject object) {
        try {
            String judul = object.getString("original_title");
            String sinopsis = object.getString("overview");
            String rating = object.getString("vote_average");
            String rilis = object.getString("release_date");
            String popularitas = object.getString("popularity");
            String poster = object.getString("poster_path");

            this.judul = judul;
            this.sinopsis = sinopsis;
            this.rating = rating;
            this.rilis = rilis;
            this.popularitas = popularitas;
            this.poster = poster;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getJudul() {
        return judul;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getRating() {
        return rating;
    }

    public String getRilis() {
        return rilis;
    }

    public String getPopularitas() {
        return popularitas;
    }

    public String getPoster() {
        return poster;
    }
}
