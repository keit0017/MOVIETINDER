package com.example.movietinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends ArrayAdapter<Movie> {
    private Context mContext;
    private ArrayList<Movie> moviesList = new ArrayList<>();
    public Movie currentMovie;



    public CardAdapter(Context context, ArrayList<Movie> list) {
        super(context, 0, list);
        moviesList=list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        Movie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_layout, parent, false);
        }
         currentMovie = moviesList.get(position);

        TextView moviedescription = (TextView) convertView.findViewById(R.id.moviedescription);
        TextView movietitle = (TextView) convertView.findViewById(R.id.movietitle);
        ImageView image = (ImageView)listItem.findViewById(R.id.movieposter);

        movietitle.setText(currentMovie.getMovietitle());
        moviedescription.setText(currentMovie.getDescription());
        image.setImageResource(currentMovie.getMovieposter());

        return convertView;
    }

}
