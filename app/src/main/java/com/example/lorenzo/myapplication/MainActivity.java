package com.example.lorenzo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private static final String TAG = "MainActivity";

    EditText emailET;
    EditText passwordET;

    Button loginBtn;
    Button registerBtn;

    Switch nightSW;
    LinearLayout homeLT;
    SharedPreferences  prefs;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);


        emailET = findViewById(R.id.email_et);
        passwordET = findViewById(R.id.password_et);

        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);

        nightSW = findViewById(R.id.night_sw);
        homeLT = findViewById(R.id.home_lt);


        registerBtn.setVisibility(View.VISIBLE);
        registerBtn.setOnClickListener(this);
        nightSW.setOnClickListener(this);

        loginBtn.setOnClickListener(this);

        registerBtn.setOnClickListener(this);
        prefs = getSharedPreferences("com.mobileapp.smartapplocker", MODE_PRIVATE);
        boolean switchState = prefs.getBoolean("service_status", true);

        nightSW.setChecked(switchState);
        changeMode(switchState);





    }

    private void changeMode(boolean bool){
        if(bool){
            homeLT.setBackgroundColor(getResources().getColor(R.color.nightBack));
            emailET.setHintTextColor(getResources().getColor(R.color.lightBack));
            emailET.setTextColor(getResources().getColor(R.color.lightBack));
            passwordET.setTextColor(getResources().getColor(R.color.lightBack));
            passwordET.setHintTextColor(getResources().getColor(R.color.lightBack));
        }else if (!bool){
            homeLT.setBackgroundColor(getResources().getColor(R.color.lightBack));
            emailET.setHintTextColor(getResources().getColor(R.color.hint));
            emailET.setTextColor(getResources().getColor(R.color.black));
            passwordET.setTextColor(getResources().getColor(R.color.black));
            passwordET.setHintTextColor(getResources().getColor(R.color.hint));
        }
    }

    private boolean isValidEmail(){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailET.getText()).matches();

    }

    private boolean isValidPassword(){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9a-zA-Z])(?=\\S+$).{6,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(passwordET.getText());
        return matcher.matches();

    }

    private void showErrorMessage(String message){
        Toast.makeText(this , message, Toast.LENGTH_LONG).show();
    }

    private void showSuccessMessage(){

        Toast.makeText(this , getString(R.string.login_success), Toast.LENGTH_LONG).show();

        Log.i(TAG, getString(R.string.login_success));
    }
    public void sendMessage(View view)
    {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
    public void sendLogIn(View view){
        Intent logIntent = new Intent(this, WelcomeActivity.class);
        logIntent.putExtra("EMAIL", emailET.getText().toString());
        startActivity(logIntent);
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.login_btn){
            if(!isValidEmail()){
                showErrorMessage(getString(R.string.email_error));
                return;
            }
            if(!isValidPassword()){
                showErrorMessage(getString(R.string.password_error));
                return;
            }
            showSuccessMessage();
            sendLogIn(this.loginBtn);
        }else if(view.getId()  == R.id.register_btn){

            sendMessage(this.registerBtn);
        }
        else if(view.getId() == R.id.night_sw){
            changeMode(nightSW.isChecked());
            SharedPreferences.Editor editor = getSharedPreferences("com.mobileapp.smartapplocker", MODE_PRIVATE).edit();
            editor.putBoolean("service_status", nightSW.isChecked());
            editor.commit();
        }


    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "activity CREATED");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "activity RESUMED");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "activity PAUSED");

    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(TAG, "activity STOPPED");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "activity DESTROYED");
    }


}
