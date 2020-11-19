package com.example.Salsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.Salsa.model.Movie;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.GestureDetector;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

/*Keith Birongo Momanyi
  01-10-2020
  welcome to the login and signup page
 */


public class MainActivity extends AppCompatActivity{
    BottomNavigationView bottomNavigation;


    //gør swipe funktion mulig
    private static final String Tag="swipeposition";
    private float x1,x2,y1,y2;
    private static int MINdistance= 100;
    private GestureDetector gestureDetector;
    private SwipeFlingAdapterView mCardStack;
    private CardAdapter mCardAdapter;
    public static ArrayList<Movie> likedmovies= new ArrayList<>();
    public static ArrayList<Movie> movies= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemReselectedListener);
    }



    //metode til at navigere mine fragmenter;
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemReselectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedfragment= null;

                    switch (item.getItemId()){
                        case R.id.navigation_home:
                            selectedfragment = new SettingsFrag();
                            break;

                        case R.id.navigation_likedmovies:
                            selectedfragment = new HomeFrag();
                            break;
                        case R.id.navigation_settings:
                            selectedfragment = new AddnewFrag();

                            break;
                    }
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,selectedfragment).commit();


                    return true;
                }};
}



