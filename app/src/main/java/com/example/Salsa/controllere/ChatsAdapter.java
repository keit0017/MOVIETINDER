package com.example.Salsa.controllere;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Salsa.R;
import com.example.Salsa.model.ChatmessageModel;

import java.util.List;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ImageviewHolder> {
    private Context mcontext;
    private List<ChatmessageModel> mUploads;


    public ChatsAdapter(Context context, List uploads){
        mUploads=uploads;
        mcontext=context;
    }

    @NonNull
    @Override
    public ImageviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.chatlayout,parent,false);
        return new ImageviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageviewHolder holder, int position) {
        ChatmessageModel uploadCurrent =  mUploads.get(position);
        holder.likedMoveiTit.setText(uploadCurrent.getmMessage());
        holder.likedMOvieDes.setText(uploadCurrent.getMusername());
        holder.likedMoviePoster.setText(uploadCurrent.getmTimeOfMessage());

    }

    @Override
    public int getItemCount() {
        if(mUploads!=null){
        return mUploads.size();} else {
        return 0;}
    }

    public class ImageviewHolder extends RecyclerView.ViewHolder  {
        TextView likedMOvieDes ;
        TextView likedMoveiTit ;
        TextView likedMoviePoster ;

        public ImageviewHolder(@NonNull View itemView) {
            super(itemView);

            likedMOvieDes= itemView.findViewById(R.id.mCommentText2);
            likedMoveiTit= itemView.findViewById(R.id.mUsernameText2);
            likedMoviePoster = itemView.findViewById(R.id.mDatetask2);

        }}

        //Tjekker om den giver fuldt mening




}
