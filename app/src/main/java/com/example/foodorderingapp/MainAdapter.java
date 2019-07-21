package com.example.foodorderingapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Rasitha on 3/23/2018.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>  {

    private ArrayList<String> myDataset = null;

    public MainAdapter(ArrayList<String> myDataset) {
        this.myDataset = myDataset;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return  vh;

    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return myDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView myTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            myTitle = (ImageView) itemView.findViewById(R.id.title);
        }
    }
}
