package com.example.Salsa;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Salsa.model.GlovVal;
import com.example.Salsa.model.Movie;
import com.wenchao.cardstack.CardStack;
import android.util.Log;

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



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CardStack mCardStack;
    private CardAdapter mCardAdapter;
    public static ArrayList<Movie> likedmovies= new ArrayList<>();
    public static ArrayList<Movie> movies= new ArrayList<>();


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

        movies= GlovVal.getMovie1ArrayList();

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mCardStack = (CardStack) v.findViewById(R.id.container);
        mCardStack.setContentResource(R.layout.card_layout);
        mCardAdapter = new CardAdapter(this.getActivity(),movies);
        mCardStack.setAdapter(mCardAdapter);
        mCardStack.setListener(HomeFrag.this);



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

        GlovVal.removemovie(mCardAdapter.currentMovie);
        movies=GlovVal.getMovie1ArrayList();

        if(i1==1 || i1==3){
           GlovVal.addLikedmovie(mCardAdapter.currentMovie);
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
    }

    }