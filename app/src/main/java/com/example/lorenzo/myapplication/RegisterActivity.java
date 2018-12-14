package com.example.lorenzo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";

    EditText emailET;
    EditText passwordET;
    EditText numberET;


    Button registerBtn;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_register);


        emailET = findViewById(R.id.email_et);
        passwordET = findViewById(R.id.password_et);
        numberET = findViewById(R.id.number_et);

        emailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                registerBtn.setEnabled(isValidEmail() && isValidNumber() && isValidPassword());

            }
        });

        numberET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                registerBtn.setEnabled(isValidEmail() && isValidNumber() && isValidPassword());
            }
        });

        passwordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                registerBtn.setEnabled(isValidEmail() && isValidNumber() && isValidPassword());
            }
        });


        registerBtn = findViewById(R.id.register_btn);


        registerBtn.setVisibility(View.VISIBLE);
        registerBtn.setOnClickListener(this);




        Log.i(TAG, "activity created");
    }

    private boolean isValidEmail(){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailET.getText()).matches();

    }
    private boolean isValidNumber(){
        return (numberET.getText().toString().length()==10);
    }

    private boolean isValidPassword(){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9a-zA-Z])(?=\\S+$).{6,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(passwordET.getText());
        return matcher.matches();

    }

    /*private void showErrorMessage(String message){
        Toast.makeText(this , message, Toast.LENGTH_LONG).show();
    }

    private void showSuccessMessage(){

        Toast.makeText(this , getString(R.string.register_success), Toast.LENGTH_LONG).show();


    }*/
    public void sendMessage(View view)
    {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.register_btn) {
            /*if (!isValidEmail()) {
                showErrorMessage(getString(R.string.email_error));
                return;
            }
            if (!isValidPassword()) {
                showErrorMessage(getString(R.string.password_error));
                return;
            }
            if (!isValidNumber()) {
                showErrorMessage("Wrong Number!");
            }
            if (isValidNumber() && isValidEmail() && isValidPassword()) {
                showSuccessMessage();
                sendMessage(this.registerBtn);
            }*/
            sendMessage(this.registerBtn);
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


