package com.example.lorenzo.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener, FoodAdapter.OnQuantityChanged {

    String mail;
    String faimail;
    TextView tot;
    float total;
    Button buyBtn;
    ProgressBar progressBar;
    float totForBar=0f;




    public void mailSend() {

        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, mail);
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
        progressBar=findViewById(R.id.progress_bar);
        progressBar.setMax(5);
        progressBar.setProgress(0);


        mail = getIntent().getStringExtra("EMAIL");
        mail_passata.setOnClickListener(this);


        tot.setText("0");


        Intent intent = getIntent();



        if (intent.getData() != null) {
            faimail = Uri.decode(intent.getData().toString().substring(7));

            mail_passata.setText(faimail);
        }else mail_passata.setText(mail);



        layoutManager = new LinearLayoutManager(this);






        adapter = new FoodAdapter(this);
        adapter.setOnQuantityChange(this);
        getProducts();
        recyclerView = findViewById(R.id.food_rv);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }






    private void getProducts(){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://5ba19290ee710f0014dd764c.mockapi.io/Food";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Success", response);
                        try {
                            JSONArray responseJSON = new JSONArray(response);

                            ArrayList<Food> foodArrayList = new ArrayList<>();

                            for (int i=0; i<responseJSON.length(); i++) {
                                Food food = new Food(responseJSON.getJSONObject(i));
                                if (food.isAvailable()){
                                    foodArrayList.add(food);
                                }

                            }

                            adapter.setData(foodArrayList);
                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(WelcomeActivity.this,"Qualcosa è andato storto",Toast.LENGTH_LONG);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", error.getMessage());
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


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
        totForBar+=price;
        tot.setText(String.valueOf(newQuantity));
        progressBar.setProgress((int) totForBar);
        if(newQuantity>=5){
            buyBtn.setEnabled(true);
        }else buyBtn.setEnabled(false);
    }

    @Override
    public void onItemRemoved(float price) {
        Float newQuantity = (Float.parseFloat(tot.getText().toString()));
        if (newQuantity > 0.99) {
            newQuantity -= price;
            totForBar-=price;
            progressBar.setProgress((int) totForBar);
            tot.setText(String.valueOf(newQuantity));
        }
        if(newQuantity>=5){
            buyBtn.setEnabled(true);
        }else buyBtn.setEnabled(false);
    }
}