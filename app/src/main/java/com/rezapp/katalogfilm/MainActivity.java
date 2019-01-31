package com.rezapp.katalogfilm;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;

import com.rezapp.katalogfilm.Adapter.MovieAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>>  {

    @BindView(R.id.listView) ListView listView;
    @BindView(R.id.edit_movie) EditText edtMovie;
    @BindView(R.id.btn_movie) Button btnSearch;
    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeLayout;

    MovieAdapter adapter;

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        swipeLayout.setRefreshing(false);
                        String Movie = edtMovie.getText().toString();
                        if (TextUtils.isEmpty(Movie)) return;
                        Bundle bundle = new Bundle();
                        bundle.putString(EXTRAS_MOVIE, Movie);
                        getLoaderManager().restartLoader(0, bundle, MainActivity.this);
                    }
                    }, 2000);
            }
        });

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        swipeLayout.setRefreshing(false);
                        String Movie = edtMovie.getText().toString();
                        if (TextUtils.isEmpty(Movie)) return;

                        Bundle bundle = new Bundle();
                        bundle.putString(EXTRAS_MOVIE, Movie);
                        getLoaderManager().restartLoader(0, bundle, MainActivity.this);
                    }
                }, 2000);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                MovieItems item = adapter.getItem(position);
                Movie movie = new Movie();

                movie.setJudul(item.getJudul());
                movie.setSinopsis(item.getSinopsis());
                movie.setPoster(BuildConfig.IMAGE_URL+item.getPoster());
                movie.setRating(item.getRating());
                movie.setRilis(item.getRilis());
                movie.setPopularitas(item.getPopularitas());

                Intent moveWithObjectIntent = new Intent(MainActivity.this, DetailActivity.class);
                moveWithObjectIntent.putExtra(DetailActivity.EXTRAS_MOVIE, movie);
                startActivity(moveWithObjectIntent);
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
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.clearData();
    }
}
