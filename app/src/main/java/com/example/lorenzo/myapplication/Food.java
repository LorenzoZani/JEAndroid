package com.example.lorenzo.myapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Food {
    private String name;
    private Float price;
    private int quantity=0;

    public Food (String name, Float price){
        this.name = name;
        this.price = price;
    }

    public Food(JSONObject food) throws JSONException {

        name=food.getString("name");
        price= (float) food.getInt("price");

    }





    public String getName() {
        return name;
    }





    public Float getPrice() {
        return price;
    }





    public int getQuantity() {
        return quantity;
    }


    public void increaseQuantity(){
        int a =quantity;
        a++;
        quantity = a;
    }
    public void decreaseQuantity(){
        int a =(quantity);
        if (a>0) {
            a--;
        }
        quantity = a;
    }
}
