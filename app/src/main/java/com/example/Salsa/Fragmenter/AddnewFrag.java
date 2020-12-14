package com.example.Salsa.Fragmenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.Salsa.Aktiviteter.MainActivity;
import com.example.Salsa.R;
import com.example.Salsa.Aktiviteter.ReviewskaermAktivitet;
import com.example.Salsa.controllere.LikedMoviesAdapter;
import com.example.Salsa.model.GlovVal;
import com.example.Salsa.model.Upload;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddnewFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddnewFrag extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button signout,deleteaccount;

    public static int currentPosition;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<Upload> mLikedmoviesList;
    RecyclerView likedmoviesview;
    private FirebaseUser user;
    private DatabaseReference mDatabaseRefLikedMovies;
    private ValueEventListener mValulistener;
    private LikedMoviesAdapter mCardAdapter;


    GlovVal globalmovie = GlovVal.getInstance();

    public AddnewFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addnew.
     */
    // TODO: Rename and change types and number of parameters
    public static AddnewFrag newInstance(String param1, String param2) {
        AddnewFrag fragment = new AddnewFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        int superPosition=0;

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


        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_addnew, container, false);

        likedmoviesview = (RecyclerView) v.findViewById(R.id.movieRecyclerView);
        likedmoviesview.hasFixedSize();
        likedmoviesview.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        mLikedmoviesList = new ArrayList<>();

        final Context currentContext = this.getActivity();

        user = FirebaseAuth.getInstance().getCurrentUser();

        String userID = user.getUid();

        mCardAdapter = new LikedMoviesAdapter(currentContext,mLikedmoviesList);

        likedmoviesview.setAdapter(mCardAdapter);
        mDatabaseRefLikedMovies= FirebaseDatabase.getInstance().getReference("LikedFilms/"+userID);

        mValulistener = mDatabaseRefLikedMovies.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    mLikedmoviesList.clear();
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        Upload upload = snapshot1.getValue(Upload.class);
                        mLikedmoviesList.add(upload);
                        Log.v("Imageuploaded",upload.getImageURi());
                    }
                    likedmoviesview.setAdapter(mCardAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();;
                }
            });

        //listener
        mCardAdapter.setOnItemClickListener(new LikedMoviesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                currentPosition =position;
                Intent intent = new Intent(getActivity(), ReviewskaermAktivitet.class);
                intent.putExtra("cmTitle",getcurrentMOvie().getmTitle());
                intent.putExtra("cmDesc",getcurrentMOvie().getmDescription());
                intent.putExtra("cmImg",getcurrentMOvie().getImageURi());
                getActivity().startActivity(intent);
            }

            @Override
            public void onWhatMenuItem(int position) {

            }

            @Override
            public void onDeleteclick(int position) {
                user = FirebaseAuth.getInstance().getCurrentUser();

                String userID = user.getUid();
                Upload selctedfilm = mLikedmoviesList.get(position);
                final String DeleteKey = selctedfilm.getmTitle();

                mDatabaseRefLikedMovies.child(DeleteKey).removeValue();
                Toast.makeText(getActivity(), selctedfilm.getmTitle()+" Was deleted :/", Toast.LENGTH_SHORT).show();


            }
        });

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public Upload getcurrentMOvie() {
        int postion;
        return mLikedmoviesList.get(currentPosition);
    }
}