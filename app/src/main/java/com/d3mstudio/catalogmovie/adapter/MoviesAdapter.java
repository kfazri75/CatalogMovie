package com.d3mstudio.catalogmovie.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.d3mstudio.catalogmovie.R;
import com.d3mstudio.catalogmovie.model.MovieModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.d3mstudio.catalogmovie.BuildConfig.URL_POSTER;

/**
 * Created by kfazri75 on 1/14/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<MovieModel> movies;
    private int rowLayout;
    private Context context;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title) TextView movieTitle;
        @BindView(R.id.subtitle) TextView data;
        @BindView(R.id.description) TextView movieDescription;
        @BindView(R.id.backbg) ImageView backbg;

        public MovieViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public MoviesAdapter(List<MovieModel> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());

        String time = movies.get(position).getReleaseDate();
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = parser.parse(time);
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM d, yyyy");
            String formattedDate = formatter.format(date);
            holder.data.setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.movieDescription.setText(movies.get(position).getOverview());
        Glide.with(context).load(URL_POSTER + movies.get(position).getBackdropPath()).into(holder.backbg);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}

