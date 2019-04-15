package com.example.parko;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parko.CarPark.CarPark;

import java.util.ArrayList;

class MapAdapter extends RecyclerView.Adapter<MapAdapter.ViewHolder> {

    ArrayList<CarPark> parking;

    public MapAdapter(ArrayList<CarPark> mparking) {
        parking=mparking;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MapAdapter.ViewHolder viewHolder, int i) {
        viewHolder.mFullName.setText(parking.get(i).getCarParkNumber());
    }

    @Override
    public int getItemCount() {
        return parking.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mFullName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mFullName=itemView.findViewById(R.id.full_name);

        }
    }
}
