package com.example.Salsa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.squareup.picasso.Picasso;

import java.net.URI;

public class AddFilmActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST=1;

    private Button mButtonChooseImage;
    private Button mButtonUploadImage;
    private TextView mTextviewshowMovies;
    private EditText mEditmovietitle;
    private EditText mEditmoviedescription;
    private ImageView mDisplayImage;
    private ProgressBar mprogressbar;


    private Uri mImageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film);

        //Finding matching buttons
        mButtonChooseImage=findViewById(R.id.choosefilebutton);
        mButtonUploadImage=findViewById(R.id.UploadButton);
        mTextviewshowMovies=findViewById(R.id.Showsalsaatextview);
        mEditmoviedescription= findViewById(R.id.editTextTextmoviedescription);
        mEditmovietitle=findViewById(R.id.editmovietitle);
        mDisplayImage=findViewById(R.id.MovieposterUpload);
        mprogressbar=findViewById(R.id.progressBar3Uploadingmovie);


        //choosing image
        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Openfilechooser();
            }
        });

        mButtonUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTextviewshowMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void Openfilechooser(){
        Intent intent = new Intent();
        intent .setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!= null && data.getData() != null){
            mImageURI = data.getData();

            Picasso.with(this).load(mImageURI).into(mDisplayImage);
        }
    }
}