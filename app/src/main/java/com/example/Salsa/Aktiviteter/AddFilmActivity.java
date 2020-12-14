package com.example.Salsa.Aktiviteter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.*;

import com.example.Salsa.R;
import com.example.Salsa.model.Upload;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
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

    private StorageTask mUploadTask;


    private Uri mImageURI;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film);
        updateStatusBarColor("#202120");



        //Finding matching buttons
        mButtonChooseImage=findViewById(R.id.choosefilebutton);
        mButtonUploadImage=findViewById(R.id.UploadButton);
        mTextviewshowMovies=findViewById(R.id.Showsalsaatextview);
        mEditmoviedescription= findViewById(R.id.editTextTextmoviedescription);
        mEditmovietitle=findViewById(R.id.editmovietitle);
        mDisplayImage=findViewById(R.id.MovieposterUpload);
        mprogressbar=findViewById(R.id.progressBar3Uploadingmovie);



        //firebase connection
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");


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

                if(mUploadTask != null && mUploadTask.isInProgress()){
                    Log.i("button", "upload button connected");
                } else {
                    uploadMovie();
                }

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

    private String getimageExtention(Uri uri){
        ContentResolver cr =getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadMovie(){

        if(mImageURI != null){
            StorageReference fileref = mStorageRef.child(System.currentTimeMillis() + "."+
                    getimageExtention(mImageURI));

            Log.i("Fileref; ",fileref.toString());

            mUploadTask = fileref.putFile(mImageURI)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Handler handler1 = new Handler();
                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mprogressbar.setProgress(0);
                                }}, 500);

                            //Uploading the movie to the database.
                            if (taskSnapshot.getMetadata() != null) {
                                if (taskSnapshot.getMetadata().getReference() != null) {
                                    Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {

                                            Upload upload = new Upload(mEditmovietitle.getText().toString().trim(),
                                                    mEditmoviedescription.getText().toString().trim(),uri.toString());

                                            String uploadID = mDatabaseRef.push().getKey();
                                            mDatabaseRef.child(uploadID).setValue(upload);
                                            //createNewPost(imageUrl);
                                        }
                                    });
                                }
                            }

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                            double progress =(100 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                            mprogressbar.setProgress((int) progress);
                        }
                    });



        } else{
            Toast.makeText(this,"no file selected",Toast.LENGTH_SHORT);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!= null && data.getData() != null){
            mImageURI = data.getData();
            Picasso.with(this).load(mImageURI).into(mDisplayImage);
        }
    }

    public void updateStatusBarColor(String color){// Color must be in hexadecimal fromat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }


}