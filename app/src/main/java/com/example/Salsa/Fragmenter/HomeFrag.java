package com.example.Salsa.Fragmenter;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Salsa.Aktiviteter.MainActivity;
import com.example.Salsa.R;

import com.example.Salsa.model.GlovVal;
import com.example.Salsa.model.Movie;
import com.example.Salsa.model.Upload;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.wenchao.cardstack.CardStack;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import com.example.Salsa.controllere.CardAdapter;

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
    private CardAdapter mCardAdapter,Currentmovie;

    public static ArrayList<Movie> likedmovies= new ArrayList<>();
    public static ArrayList<Upload> movies= new ArrayList<>();
    public static ArrayList<Upload> currentMov= new ArrayList<>();
    private int index1;


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
        ((MainActivity) Objects.requireNonNull(getActivity())).updateStatusBarColor("#202120");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        index1=0;



        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mCardStack = (CardStack) v.findViewById(R.id.container);
        mCardStack.setContentResource(R.layout.card_layout);
        mCardAdapter = new CardAdapter(this.getActivity(),movies);



        mCardStack.setListener(HomeFrag.this);

        mDatabaseref = FirebaseDatabase.getInstance().getReference("uploads");

        mDatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                movies.clear();
                currentMov.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Upload upload = snapshot1.getValue(Upload.class);
                    movies.add(upload);
                    Log.v("Imageuploaded",upload.getImageURi());
                }
                mCardStack.setAdapter(mCardAdapter);
                currentMov=movies;

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
            //sørger for at den tæller ned regardless

            if(currentMov.size()>index1){
                index1++;
            }
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
        int arraylenghth;

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userID = user.getUid();
            Log.v("userness", userID);
           // mCardAdapter..getmTitle();

            Toast.makeText(getActivity(), "swipe Right"+currentMov.get(index1).getmTitle(), Toast.LENGTH_SHORT).show();

            mDatabaseRefLikedMovies= FirebaseDatabase.getInstance().getReference("LikedFilms/"+userID);

            Uri uri=Uri.parse(currentMov.get(index1).getImageURi());

            Log.v("Databasecheck", uri.toString());

            Upload upload = currentMov.get(index1);
            //String uploadID = mDatabaseRefLikedMovies.push().getKey();
            mDatabaseRefLikedMovies.child(currentMov.get(index1).getmTitle()).setValue(upload);
            //createNewPost(imageUrl);

            if(currentMov.size()>index1){
                index1++;
            }

    } else {


        }

    }}