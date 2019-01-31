package com.rezapp.katalogfilm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

import com.rezapp.katalogfilm.BuildConfig;
import com.rezapp.katalogfilm.R;
import com.rezapp.katalogfilm.MovieItems;

public class MovieAdapter extends BaseAdapter  {

    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;

    public MovieAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<MovieItems> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
    }

    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    public MovieItems getItem(int position) { return mData.get(position); }

    public long getItemId(int position) { return position; }

    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder holder;
        if (itemView == null) {
            holder = new ViewHolder();
            itemView = mInflater.inflate(R.layout.movie_items, null);
            holder.textViewJudul = (TextView) itemView.findViewById(R.id.textJudul);
            holder.textViewDeskripsi = (TextView) itemView.findViewById(R.id.textDeskripsi);
            holder.imgPoster = (ImageView) itemView.findViewById(R.id.imgPoster);
            itemView.setTag(holder);
        } else {
            holder = (ViewHolder) itemView.getTag();
        }
        holder.textViewJudul.setText(mData.get(position).getJudul());
        holder.textViewDeskripsi.setText(mData.get(position).getSinopsis());
        String url_poster = BuildConfig.THUMBNAIL_URL+mData.get(position).getPoster();
        Glide.with(itemView).load(url_poster).into(holder.imgPoster);
        return itemView;
    }

    private static class ViewHolder {
        TextView textViewJudul;
        TextView textViewDeskripsi;
        ImageView imgPoster;
    }
}
