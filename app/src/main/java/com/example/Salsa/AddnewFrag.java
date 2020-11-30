package com.example.Salsa;

import android.content.Context;
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
import android.widget.ListView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddnewFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddnewFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button signout,deleteaccount;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<Upload> mLikedmoviesList;
    RecyclerView likedmoviesview;
    private FirebaseUser user;
    private DatabaseReference mDatabaseRefLikedMovies;
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


        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_addnew, container, false);

        likedmoviesview = (RecyclerView) v.findViewById(R.id.movieRecyclerView);
        likedmoviesview.hasFixedSize();
        likedmoviesview.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        mLikedmoviesList = new ArrayList<>();

        final Context currentContext = this.getActivity();

        user = FirebaseAuth.getInstance().getCurrentUser();

        String userID = user.getUid();

        mDatabaseRefLikedMovies= FirebaseDatabase.getInstance().getReference("LikedFilms/"+userID);

        mDatabaseRefLikedMovies.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        Upload upload = snapshot1.getValue(Upload.class);
                        mLikedmoviesList.add(upload);
                        Log.v("Imageuploaded",upload.getImageURi());
                    }
                    mCardAdapter = new LikedMoviesAdapter(currentContext,mLikedmoviesList);
                    likedmoviesview.setAdapter(mCardAdapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();;
                }
            });
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}