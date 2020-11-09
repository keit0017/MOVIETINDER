package com.example.movietinder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addnew#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addnew extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button signout,deleteaccount;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<Movie> likedmoviesList;
    ListView likedmoviesview;
    private MovieAdapter mCardAdapter;
    GlovVal globalmovie = GlovVal.getInstance();

    public addnew() {
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
    public static addnew newInstance(String param1, String param2) {
        addnew fragment = new addnew();
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

        likedmoviesList=globalmovie.getMovie1ArrayList();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_addnew, container, false);

        Makemovies();


        likedmoviesview = (ListView) v.findViewById(R.id.likedmovielistview);


        mCardAdapter = new MovieAdapter(this.getActivity(),likedmoviesList);
        likedmoviesview.setAdapter(mCardAdapter);
        return v;
    }

    void Makemovies(){

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





    }
}