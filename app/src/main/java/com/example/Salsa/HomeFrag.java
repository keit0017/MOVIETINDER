package com.example.Salsa;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Salsa.model.GlovVal;
import com.example.Salsa.model.Movie;
import com.example.Salsa.model.Upload;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.wenchao.cardstack.CardStack;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFrag extends Fragment implements CardStack.CardEventListener{
    //adapter således jeg kan få swipe funktionen til at fungere.

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    GlovVal globmov = GlovVal.getInstance();
    private DatabaseReference mDatabaseref;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CardStack mCardStack;
    private CardAdapter mCardAdapter;

    public static ArrayList<Movie> likedmovies= new ArrayList<>();
    public static ArrayList<Upload> movies= new ArrayList<>();


    public HomeFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFrag newInstance(String param1, String param2) {
        HomeFrag fragment = new HomeFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }



        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mCardStack = (CardStack) v.findViewById(R.id.container);
        mCardStack.setContentResource(R.layout.card_layout);
        mCardAdapter = new CardAdapter(this.getActivity(),movies);

        mDatabaseref = FirebaseDatabase.getInstance().getReference("uploads");

        mDatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Upload upload = snapshot1.getValue(Upload.class);
                    movies.add(upload);
                    Log.v("Imageuploaded",upload.getImageURi());
                }

                mCardStack.setAdapter(mCardAdapter);
                mCardStack.setListener(HomeFrag.this);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();;
            }
        });






        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //      mCardStack.setStackMargin(20);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if (mCardStack.getAdapter() != null) {
            Log.i("MyActivity", "Card Stack size: " + mCardStack.getAdapter().getCount());
        }
    }

    @Override
    public boolean swipeEnd(int i, float v) {

        Log.v("totalResultskeith", String.valueOf(movies));

        return v > 300;


    }

    @Override
    public boolean swipeStart(int i, float v) {
        return true;
    }

    @Override
    public boolean swipeContinue(int i, float v, float v1) {
        return true;
    }

    @Override
    public void discarded(int i, int i1) {
        Log.v("test", "pikimund");


        if(i1==1 || i1==3){
            uploadLikedMovie();



        } else{
            Toast.makeText(getActivity(), "swipe left", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void topCardTapped() {

    }

    public class MoviesandSwipeAsync extends AsyncTask<Void,Void,ArrayList<Movie>>{
        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            SwipeListener swipeCardListener = new SwipeListener();
            Log.v("Status:","Vi i listeneren");

            super.onPostExecute(movies);
        }



        private String getimageExtention(Uri uri){
            ContentResolver cr =getActivity().getContentResolver();
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            return mime.getExtensionFromMimeType(cr.getType(uri));
        }

    }

    //uploader de film folk liker til deres eget depository
    private FirebaseUser user;
    private StorageReference mStorageRefLikedMovies;
    private DatabaseReference mDatabaseRefLikedMovies;

    private void uploadLikedMovie(){

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userID = user.getUid();
            Log.v("userness", userID);

            mCardAdapter.currentMovie.getmTitle();

            Toast.makeText(getActivity(), "swipe Right"+mCardAdapter.currentMovie.getmTitle(), Toast.LENGTH_SHORT).show();

            mDatabaseRefLikedMovies= FirebaseDatabase.getInstance().getReference("LikedFilms/"+userID);

            Uri uri=Uri.parse(mCardAdapter.currentMovie.getImageURi());

            Log.v("Databasecheck", uri.toString());

            Upload upload = mCardAdapter.currentMovie;
            String uploadID = mDatabaseRefLikedMovies.push().getKey();
            mDatabaseRefLikedMovies.child(uploadID).setValue(upload);
            //createNewPost(imageUrl);

    } else {

        }

    }}