package com.example.Salsa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Salsa.model.Upload;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LikedMoviesAdapter extends RecyclerView.Adapter<LikedMoviesAdapter.ImageviewHolder> {
    private Context mcontext;
    private List<Upload> mUploads;

    public LikedMoviesAdapter(Context context, List uploads){
        mUploads=uploads;
        mcontext=context;
    }

    @NonNull
    @Override
    public ImageviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.newlayout,parent,false);


        return new ImageviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageviewHolder holder, int position) {
        Upload uploadCurrent =  mUploads.get(position);
        holder.likedMoveiTit.setText(uploadCurrent.getmTitle());
        holder.likedMOvieDes.setText(uploadCurrent.getmDescription());
        Picasso.with(mcontext)
                .load(uploadCurrent.getImageURi()).fit().centerCrop().into(holder.likedMoviePoster);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageviewHolder extends RecyclerView.ViewHolder{
        TextView likedMOvieDes ;
        TextView likedMoveiTit ;
        ImageView likedMoviePoster ;

        public ImageviewHolder(@NonNull View itemView) {
            super(itemView);

            likedMOvieDes= itemView.findViewById(R.id.descriptionmovieList);
            likedMoveiTit= itemView.findViewById(R.id.movietitleList);
            likedMoviePoster = itemView.findViewById(R.id.likedpictureList);


        }
    }
}
