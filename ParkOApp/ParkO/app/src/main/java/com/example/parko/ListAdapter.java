package com.example.parko;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parko.CarPark.CarPark;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    ArrayList<CarPark> parking;

    public ListAdapter(ArrayList<CarPark> mparking) {
        parking=mparking;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder viewHolder, int i) {
        viewHolder.mFullName.setText(("Car Park Number: ")+ parking.get(i).getCarParkNumber());
        viewHolder.distance.setText("(Occupied/Available): "+String.valueOf(parking.get(i).getLotsTaken())+"/"+String.valueOf(parking.get(i).getTotalLots()));
        viewHolder.description.setText(parking.get(i).getAddress());

    }

    @Override
    public int getItemCount() {
        return parking.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mFullName,distance,description,freeParking;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mFullName=itemView.findViewById(R.id.full_name);
            distance=itemView.findViewById(R.id.full_name1);
            description=itemView.findViewById(R.id.full_name2);
            freeParking=itemView.findViewById(R.id.full_name3);

        }
    }
}
