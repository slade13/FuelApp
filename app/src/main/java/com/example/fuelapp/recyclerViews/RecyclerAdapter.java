package com.example.fuelapp.recyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuelapp.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<String> vehicleIds, vehicleBrands, vehicleModels, vehicleHorses, vehicleEngines, vehicleYears, plateNumbers;
    private Context context;
    private RecyclerViewClickListener listener;

    public RecyclerAdapter(Context context,
                           ArrayList<String> vehicleIds,
                           ArrayList<String> vehicleBrands,
                           ArrayList<String> vehicleModels,
                           ArrayList<String> vehicleEngines,
                           ArrayList<String> vehicleHorses,
                           ArrayList<String> vehicleYears,
                           ArrayList<String> plateNumbers,
                           RecyclerViewClickListener listener) {
        this.vehicleIds = vehicleIds;
        this.vehicleBrands = vehicleBrands;
        this.vehicleModels = vehicleModels;
        this.vehicleEngines = vehicleEngines;
        this.vehicleHorses = vehicleHorses;
        this.vehicleYears = vehicleYears;
        this.plateNumbers = plateNumbers;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    //choosing what to put on the list
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        holder.vehicleBrandTv.setText(String.valueOf(vehicleBrands.get(position)));
        holder.vehicleModelTv.setText(String.valueOf(vehicleModels.get(position)));
        holder.plateNumberTv.setText(String.valueOf(plateNumbers.get(position)));
    }

    @Override
    public int getItemCount() {
        return vehicleIds.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView vehicleBrandTv, vehicleModelTv, plateNumberTv;

        //Defining elements of a itemView
        public MyViewHolder(@NonNull View view) {
            super(view);
            vehicleBrandTv = view.findViewById(R.id.fuelAmountTv);
            vehicleModelTv = view.findViewById(R.id.fuelCostTv);
            plateNumberTv = view.findViewById(R.id.plateNumberTv);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}
