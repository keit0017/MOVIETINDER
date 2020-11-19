package com.example.Salsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Salsa.model.GlovVal;
import com.example.Salsa.model.Movie;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class LoginAkt extends AppCompatActivity {
    Button gotomain;
    TextView gotosignup;
    EditText emailuser, passworduser;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    public static ArrayList<Movie> movies= new ArrayList<>();
    GlovVal globmov = GlovVal.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createmovies();

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginAkt.this, MainActivity.class));
            finish();
        }

        //inputter users.
        emailuser=findViewById(R.id.loginemail);
        passworduser=findViewById(R.id.loginpassword);
        gotomain=findViewById(R.id.loginbutton);
        gotosignup =findViewById(R.id.textView2);
        progressBar= findViewById(R.id.progressBar2);



        final Intent gomain = new Intent(this, MainActivity.class);
        final Intent gosignup = new Intent(this, RegisterAkt.class);

        gotomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailuser.getText().toString();
                final String password = passworduser.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authentication
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginAkt.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        passworduser.setError("Password too short, enter minimum 6 characters!");
                                    } else {
                                        Toast.makeText(LoginAkt.this, "Authentication failed", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(LoginAkt.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });




            }
        });


        gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(gosignup);
            }
        });





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


        globmov.setMovie1ArrayList(movies);


    }



}