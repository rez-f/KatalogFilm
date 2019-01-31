package com.rezapp.katalogfilm;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String Judul;
    private String Sinopsis;
    private String Poster;
    private String Rating;
    private String Rilis;
    private String Popularitas;

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String judul) {
        Judul = judul;
    }

    public String getSinopsis() {
        return Sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        Sinopsis = sinopsis;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getRilis() {
        return Rilis;
    }

    public void setRilis(String rilis) {
        Rilis = rilis;
    }

    public String getPopularitas() {
        return Popularitas;
    }

    public void setPopularitas(String popularitas) {
        Popularitas = popularitas;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Judul);
        dest.writeString(this.Sinopsis);
        dest.writeString(this.Poster);
        dest.writeString(this.Rating);
        dest.writeString(this.Rilis);
        dest.writeString(this.Popularitas);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.Judul = in.readString();
        this.Sinopsis = in.readString();
        this.Poster = in.readString();
        this.Rating = in.readString();
        this.Rilis = in.readString();
        this.Popularitas = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
