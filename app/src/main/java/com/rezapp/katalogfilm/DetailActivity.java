package com.rezapp.katalogfilm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRAS_MOVIE = "extra_movie";

    @BindView(R.id.textJudul) TextView Judul;
    @BindView(R.id.textSinopsis) TextView Sinopsis;
    @BindView(R.id.imagePoster) ImageView Poster;
    @BindView(R.id.textRating) TextView Rating;
    @BindView(R.id.textRilis) TextView Rilis;
    @BindView(R.id.textPopularitas) TextView Popularitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Movie movie = getIntent().getParcelableExtra(EXTRAS_MOVIE);

        if(movie != null) {
            Judul.setText(movie.getJudul());
            Sinopsis.setText("Sinopsis: \n \n"+movie.getSinopsis());
            Glide.with(Poster).load(movie.getPoster()).into(Poster);
            Rating.setText("Rating: "+movie.getRating());
            Rilis.setText("Rilis: "+movie.getRilis());
            Popularitas.setText("Popularitas: "+movie.getPopularitas());
        }
    }
}
