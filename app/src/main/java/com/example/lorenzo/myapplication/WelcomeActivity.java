package com.example.lorenzo.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener, FoodAdapter.OnQuantityChanged {

    String mail;
    String faimail;
    TextView tot;
    float total;
    Button buyBtn;



    public void mailSend() {

        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"deiv-93@hotmail.it"});
        emailIntent.putExtra(Intent.EXTRA_TEXT, "body text");
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }


    List<View> righe = new ArrayList<>();

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    FoodAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView mail_passata = findViewById(R.id.welcome_email_ed);
        buyBtn = findViewById(R.id.buy_btn);
        tot = findViewById(R.id.total_shop);


        mail = getIntent().getStringExtra("EMAIL");
        mail_passata.setOnClickListener(this);


        tot.setText("0");


        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (intent.getData() != null) {
            faimail = Uri.decode(intent.getData().toString().substring(7));

            mail_passata.setText(faimail);
        }else mail_passata.setText(mail);



        layoutManager = new LinearLayoutManager(this);

        ArrayList<Food> foodList = new ArrayList<>();

        foodList.add(new Food("bread", "1", "0"));
        foodList.add(new Food("milk", "2", "0"));
        foodList.add(new Food("meat", "3.50", "0"));
        foodList.add(new Food("bread", "1", "0"));
        foodList.add(new Food("milk", "2", "0"));
        foodList.add(new Food("meat", "3.50", "0"));
        foodList.add(new Food("bread", "1", "0"));
        foodList.add(new Food("milk", "2", "0"));
        foodList.add(new Food("meat", "3.50", "0"));
        foodList.add(new Food("bread", "1", "0"));
        foodList.add(new Food("milk", "2", "0"));
        foodList.add(new Food("meat", "3.50", "0"));


        adapter = new FoodAdapter(this, foodList);
        adapter.setOnQuantityChange(this);


        recyclerView = findViewById(R.id.food_rv);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }


    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.welcome_email_ed) {
            mailSend();
        }
    }

    @Override
    public void onItemAdded(float price) {

        Float newQuantity = (Float.parseFloat(tot.getText().toString()));
        newQuantity += price;
        tot.setText(String.valueOf(newQuantity));
        if(newQuantity>=5){
            buyBtn.setEnabled(true);
        }else buyBtn.setEnabled(false);
    }

    @Override
    public void onItemRemoved(float price) {
        Float newQuantity = (Float.parseFloat(tot.getText().toString()));
        if (newQuantity > 0.99) {
            newQuantity -= price;
            tot.setText(String.valueOf(newQuantity));
        }
        if(newQuantity>=5){
            buyBtn.setEnabled(true);
        }else buyBtn.setEnabled(false);
    }
}