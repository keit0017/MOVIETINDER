package com.example.movietinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    Button gotomain;
    TextView gotosignup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        gotomain=findViewById(R.id.loginbutton);
        gotosignup =findViewById(R.id.textView2);



        final Intent gomain = new Intent(this, MainActivity.class);
        final Intent gosignup = new Intent(this, Register.class);

        gotomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(gomain);
            }
        });

        gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(gosignup);
            }
        });
    }

}