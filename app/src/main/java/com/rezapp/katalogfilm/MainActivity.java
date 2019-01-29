package com.rezapp.katalogfilm;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
        adapter.notifyDataSetChanged();
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        edtMovie = (EditText) findViewById(R.id.edit_movie);
        btnSearch = (Button) findViewById(R.id.btn_movie);

        btnSearch.setOnClickListener(myListener);

        String movie = edtMovie.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, movie);

        //Inisiasi dari Loader, dimasukkan ke dalam onCreate
        getLoaderManager().initLoader(0, bundle, this);
    }

    //Fungsi ini yang akan menjalankan proses myasynctaskloader
    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {

        String movies = "";
        if (args != null) {
            movies = args.getString(EXTRAS_MOVIE);
        }

        return new MyAsyncTaskLoader(this, movies);
    }

    // Fungsi ini dipanggil ketika proses load sudah selesai
    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        movies.clear();
        movies.addAll(data);
        adapter.setData(movies);
    }

    // Fungsi ini dipanggil ketika loader direset
    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.clearData();
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String Movie = edtMovie.getText().toString();

            // Jika edit text-nya kosong maka do nothing
            if (TextUtils.isEmpty(Movie)) return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, Movie);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };

}
