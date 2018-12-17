package com.example.lorenzo.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FoodListAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FindViewHolder extends RecyclerView.ViewHolder{
        public TextView productName, productPrice, productQuantity;
        public Button addBtn,removeBtn;

        public FindViewHolder(@NonNull View itemView) {
            super(itemView);

            productName=itemView.findViewById(R.id.);//TODO create xml files

        }
    }
}
