package com.example.Salsa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Salsa.model.Movie;
import com.example.Salsa.model.Upload;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<Upload> {
    private Context mContext;
    private ArrayList<Upload> moviesList = new ArrayList<>();
    public Upload currentMovie;



    public MovieAdapter(Context context, ArrayList<Upload> list) {
        super(context, 0, list);
        mContext=context;
        moviesList=list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        Upload movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.newlayout, parent, false);
        }
         currentMovie = getItem(position);

        TextView moviedescription = (TextView) convertView.findViewById(R.id.descriptionmovieList);
        TextView movietitle = (TextView) convertView.findViewById(R.id.movietitleList);
        ImageView image = (ImageView) convertView.findViewById(R.id.likedpictureList);

        movietitle.setText(currentMovie.getmTitle());
        moviedescription.setText(currentMovie.getmDescription());
        Picasso.with(mContext)
                .load(currentMovie.getImageURi()).fit().centerCrop().into(image);

        return convertView;
    }

    public Upload getCurrentMovie() {
        return currentMovie;
    }
}
