package com.example.movietinder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenchao.cardstack.CardAnimator;
import com.wenchao.cardstack.CardStack;
import android.util.Log;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {
    //adapter således jeg kan få swipe funktionen til at fungere.

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CardStack mCardStack;
    private CardAdapter mCardAdapter;
    public static ArrayList<Movie> likedmovies= new ArrayList<>();
    public static ArrayList<Movie> movies= new ArrayList<>();
    SwipeListener swipeListener;

    public home() {
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
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mCardStack = (CardStack) getView().findViewById(R.id.container);


        mCardStack.setContentResource(R.layout.card_layout);
//      mCardStack.setStackMargin(20);
        createmovies();
        mCardAdapter = new CardAdapter(this.getActivity(),movies);
        mCardStack.setAdapter(mCardAdapter);



        if (mCardStack.getAdapter() != null) {
            Log.i("MyActivity", "Card Stack size: " + mCardStack.getAdapter().getCount());
        }

    }

    void createmovies(){
        Movie zombiekiller = new Movie("Zombiekiller", "I was not sure about copyright so am using safe pictures", R.drawable.skeleton);
        Movie adreneline = new Movie("Adreneline", "Explosions, guns, everthing you need", R.drawable.zombies);
        Movie yogamaster= new Movie("Yogamaster", "The interdimentional demon can only be stopped by_", R.drawable.electric);
        Movie film1= new Movie("Star trey 1", "THe first in the trey universe", R.drawable.electric);
        Movie film2= new Movie("Star War 1", "Stars go to war with each other", R.drawable.zombies);
        Movie film3= new Movie("Return", "Return of what? ", R.drawable.electric);
        Movie film4= new Movie("Star War 2", "THis time they fight harder", R.drawable.zombies);
        Movie film6= new Movie("Howard", "An Oscar winning picture about Howard university. ", R.drawable.electric);
        Movie film7= new Movie("Samanthas dreams", "A teenage girl realizes she has magical superpowers", R.drawable.electric);
        Movie film8= new Movie("Kamehameha", "The legendary japanese saga about people fighting in space", R.drawable.zombies);
        Movie film9= new Movie("Same plot, New movie", "Comedy duo Dimitri and Kevin are at it again", R.drawable.electric);
        Movie film10= new Movie("Remake", "A movie about a movie", R.drawable.electric);
        Movie film11= new Movie("Remake 2", "A movie about a movie about a movie", R.drawable.skeleton);
        Movie film12= new Movie("Remake 3, the plot twist", "A movie about a movie about a movie about a movie", R.drawable.electric);
        Movie film13= new Movie("Remake 4", "A movie about a movie about a movie about a movie about a movie", R.drawable.zombies);
        Movie film14= new Movie("aventedors", "Avengers but with lambos", R.drawable.electric);
        Movie film15= new Movie("Ladykilla", "A story about seltabium who grew up in post industrial greece", R.drawable.zombies);
        Movie film16= new Movie("1944 1/2", "They really tried winning a oscar with this one", R.drawable.electric);
        Movie film17= new Movie("Welcome to hell", "Scariest movie since remake 3, the plot twist ", R.drawable.zombies);
        Movie film19= new Movie("Ninja", "The interdimentional ninja allergic to hygene attemps to save the world", R.drawable.electric);

        movies.add(zombiekiller);
        movies.add(adreneline);
        movies.add(yogamaster);
        movies.add(film1);
        movies.add(film2);
        movies.add(film3);
        movies.add(film4);
        movies.add(film16);
        movies.add(film7);
        movies.add(film8);
        movies.add(film9);
        movies.add(film10);
        movies.add(film11);
        movies.add(film12);
        movies.add(film13);
        movies.add(film14);
        movies.add(film15);
        movies.add(film16);
        movies.add(film17);
        movies.add(film19);

    }




}