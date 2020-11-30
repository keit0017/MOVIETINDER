package com.example.Salsa;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.Salsa.model.Movie;
import com.example.Salsa.model.Upload;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardAdapter extends ArrayAdapter<Upload> {
    private Context mContext;
    private ArrayList<Upload> moviesList = new ArrayList<>();
    public Upload currentMovie;


    public CardAdapter(Context context, ArrayList<Upload> list) {
        super(context,
                0,
                list);
        moviesList=list;
        mContext =context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        Movieholder holder = null;
        Upload movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_layout, parent, false);


            convertView.setTag(holder);

        }
         currentMovie = getItem(position);


        TextView moviedescription = (TextView) convertView.findViewById(R.id.moviedescription);
        TextView movietitle = (TextView) convertView.findViewById(R.id.movietitle);
        ImageView image = (ImageView) convertView.findViewById(R.id.movieposter);


        movietitle.setText(currentMovie.getmTitle());
        moviedescription.setText(currentMovie.getmDescription());
        Picasso.with(mContext)
                .load(currentMovie.getImageURi()).fit().centerCrop().into(image);


        return convertView;
    }


    static class Movieholder
    {
        ImageView imageIcon;
        TextView textTitle;
        TextView textScore;
    }


}
