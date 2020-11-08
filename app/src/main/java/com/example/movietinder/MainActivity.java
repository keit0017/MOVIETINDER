package com.example.movietinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/*Keith Birongo Momanyi
  01-10-2020
  welcome to the login and signup page
 */


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

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
                };
            };
}
