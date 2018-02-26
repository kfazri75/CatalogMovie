package com.d3mstudio.favoritemovie.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.d3mstudio.favoritemovie.R;

import static com.d3mstudio.favoritemovie.db.DatabaseContract.FavoriteColumns.DESCRIPTION;
import static com.d3mstudio.favoritemovie.db.DatabaseContract.FavoriteColumns.POSTER;
import static com.d3mstudio.favoritemovie.db.DatabaseContract.FavoriteColumns.TITLE;
import static com.d3mstudio.favoritemovie.db.DatabaseContract.getColumnString;

/**
 * Created by kfazri75 on 1/29/18.
 */

public class FavoriteAdapter extends CursorAdapter {

    private Context mContex;

    public FavoriteAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        this.mContex = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_content_provider, parent, false);
        return view;
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }

    @Override
    public void bindView(View itemView, Context context, Cursor cursor) {
        if (cursor != null){
            TextView tvJudul = (TextView)itemView.findViewById(R.id.np_text_judul);
            TextView tvDesc = (TextView)itemView.findViewById(R.id.np_text_desc);
            ImageView imgMovie = (ImageView)itemView.findViewById(R.id.np_img_movie);

            tvJudul.setText(getColumnString(cursor,TITLE));
            tvDesc.setText(getColumnString(cursor,DESCRIPTION));
            String url = "http://image.tmdb.org/t/p/w185/";
            Glide.with(context).load(url+getColumnString(cursor,POSTER)).into(imgMovie);
        }
    }
}