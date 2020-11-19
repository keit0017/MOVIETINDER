package com.example.Salsa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<Movie> {
    private Context mContext;
    private ArrayList<Movie> moviesList = new ArrayList<>();
    public Movie currentMovie;



    public MovieAdapter(Context context, ArrayList<Movie> list) {
        super(context, 0, list);
        moviesList=list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        Movie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.newlayout, parent, false);
        }
         currentMovie = moviesList.get(position);

        TextView moviedescription = (TextView) convertView.findViewById(R.id.descriptionmovieList);
        TextView movietitle = (TextView) convertView.findViewById(R.id.movietitleList);
        ImageView image = (ImageView) convertView.findViewById(R.id.likedpictureList);

        movietitle.setText(currentMovie.getMovietitle());
        moviedescription.setText(currentMovie.getDescription());
        image.setImageResource(currentMovie.getMovieposter());

        return convertView;
    }

    public Movie getCurrentMovie() {
        return currentMovie;
    }
}
