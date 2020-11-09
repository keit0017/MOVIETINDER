package com.example.movietinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.time.chrono.MinguoChronology;
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
                            selectedfragment = new settings();
                            break;

                        case R.id.navigation_likedmovies:
                            selectedfragment = new home();
                            break;
                        case R.id.navigation_settings:
                            selectedfragment = new addnew();

                            break;
                    }
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,selectedfragment).commit();


                    return true;
                }};
}



