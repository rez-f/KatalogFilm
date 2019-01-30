package com.rezapp.katalogfilm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView Judul;
    TextView Sinopsis;
    ImageView Poster;
    TextView Rating;
    TextView Rilis;
    TextView Popularitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Judul = (TextView) findViewById(R.id.textJudul);
        Sinopsis = (TextView) findViewById(R.id.textSinopsis);
        Poster = (ImageView) findViewById(R.id.imagePoster);
        Rating = (TextView) findViewById(R.id.textRating);
        Rilis = (TextView) findViewById(R.id.textRilis);
        Popularitas = (TextView) findViewById(R.id.textPopularitas);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            Judul.setText(bundle.getString("judul"));
            Sinopsis.setText("Sinopsis: \n \n"+bundle.getString("sinopsis"));
            Glide.with(Poster).load(bundle.getString("poster")).into(Poster);
            Rating.setText("Rating: "+bundle.getString("rating"));
            Rilis.setText("Rilis: "+bundle.getString("rilis"));
            Popularitas.setText("Popularitas: "+bundle.getString("popularitas"));
        }
    }
}
