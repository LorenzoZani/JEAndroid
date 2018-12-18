package com.example.lorenzo.myapplication;

public class Food {
    public Food (String name, String prezzo, String quantity){
        this.name = name;
        this.quantity = quantity;
        this.prezzo = prezzo;
    }



    private String name;

    public String getName() {
        return name;
    }



    private String prezzo;

    public String getPrezzo() {
        return prezzo;
    }



    private String quantity;

    public String getQuantity() {
        return quantity;
    }


    public void increaseQuantity(){
        int a =Integer.parseInt(quantity);
        a++;
        quantity = String.valueOf(a);
    }
    public void decreaseQuantity(){
        int a =Integer.parseInt(quantity);
        if (a>0) {
            a--;
        }
        quantity = String.valueOf(a);
    }
}
