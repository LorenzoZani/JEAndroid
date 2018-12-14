package com.example.lorenzo.myapplication;


import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.TextView;

import java.util.Objects;


public class WelcomeActivity extends AppCompatActivity implements OnClickListener {

    TextView welcomeTV;
    String email;
    String openedEmail;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        welcomeTV=findViewById(R.id.welcome_email_ed);
        welcomeTV.setOnClickListener(this);




        if (getIntent().getStringExtra("EMAIL") != null){
            email= getIntent().getStringExtra("EMAIL");

            welcomeTV.setText(email);
        }
        else{
            openedEmail=Objects.requireNonNull(getIntent().getData()).toString().substring(7);
            openedEmail=Uri.decode(openedEmail);
            welcomeTV.setText(openedEmail);
        }


    }

    public void sendEmail(View view){
        Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",getIntent().getStringExtra("EMAIL"), null));
        startActivity(Intent.createChooser(i, "Choose an Email client :"));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.welcome_email_ed){
            sendEmail(this.welcomeTV);
        }

        }
}

