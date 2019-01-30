package com.rezapp.katalogfilm;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.rezapp.katalogfilm.Adapter.MovieAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>>  {

    ListView listView;
    MovieAdapter adapter;
    EditText edtMovie;
    Button btnSearch;

    private ArrayList<MovieItems> movies = new ArrayList<>();

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        edtMovie = (EditText) findViewById(R.id.edit_movie);
        btnSearch = (Button) findViewById(R.id.btn_movie);

        btnSearch.setOnClickListener(btnListener);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                MovieItems item = adapter.getItem(position);

                String Judul = item.getJudul();
                String Sinopsis = item.getSinopsis();
                String Poster = "https://image.tmdb.org/t/p/w154/"+item.getPoster();
                String Rating = item.getRating();
                String Rilis = item.getRilis();
                String Popularitas = item.getPopularitas();

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle b = new Bundle();

                b.putString("judul", Judul);
                b.putString("sinopsis", Sinopsis);
                b.putString("poster", Poster);
                b.putString("rating", Rating);
                b.putString("rilis", Rilis);
                b.putString("popularitas", Popularitas);

                intent.putExtras(b);
                startActivity(intent);
            }
        });

        String movie = edtMovie.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, movie);
        getLoaderManager().initLoader(0, bundle, this);
    }

    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {
        String movies = "";
        if (args != null) movies = args.getString(EXTRAS_MOVIE);

        return new MyAsyncTaskLoader(this, movies);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        movies.clear();
        movies.addAll(data);
        adapter.setData(movies);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.clearData();
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String Movie = edtMovie.getText().toString();
            if (TextUtils.isEmpty(Movie)) return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, Movie);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };
}
