package com.rezapp.katalogfilm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.annotation.GlideModule;


import com.rezapp.katalogfilm.R;
import com.rezapp.katalogfilm.MovieItems;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;

    public MovieAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Gunakan method ini jika semua datanya akan diganti
     *
     * @param items kumpulan data baru yang akan mengganti semua data yang sudah ada
     */
    public void setData(ArrayList<MovieItems> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * Gunakan method ini jika ada 1 data yang ditambahkan
     *
     * @param item data baru yang akan ditambahkan
     */
    public void addItem(final MovieItems item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        // Pengecekan null, diperlukan agar tidak terjadi force close ketika datanya null
        // return 0 sehingga adapter tidak akan menampilkan apapun
        if (mData == null) return 0;

        // Jika tidak null, maka return banyaknya jumlah data yang ada
        return mData.size();
    }

    @Override
    public MovieItems getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.movie_items, null);
            holder.textViewJudul = (TextView) convertView.findViewById(R.id.textJudul);
            holder.textViewDeskripsi = (TextView) convertView.findViewById(R.id.textDeskripsi);
            holder.imgPoster = (ImageView) convertView.findViewById(R.id.imgPoster);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewJudul.setText(mData.get(position).getJudul());
        holder.textViewDeskripsi.setText(mData.get(position).getDeskripsi());
        String url_poster = "https://image.tmdb.org/t/p/w92/"+mData.get(position).getPoster();
        Glide.with(convertView).load(url_poster).into(holder.imgPoster);
        return convertView;
    }

    private static class ViewHolder {
        TextView textViewJudul;
        TextView textViewDeskripsi;
        ImageView imgPoster;
    }
}
