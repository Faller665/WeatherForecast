package com.example.weatherforecast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.InnerHolder> {

    private ArrayList<data> dataArrayList;

    rvAdapter(ArrayList<data> dataArrayList){
        this.dataArrayList=dataArrayList;
    }

    @NonNull
    @Override
    public rvAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull rvAdapter.InnerHolder holder, int position) {
holder.date.setText(dataArrayList.get(position).getDate());
holder.tem.setText(dataArrayList.get(position).getTem_min()+"℃/"+dataArrayList.get(position).getTem_max()+"℃");
holder.weather.setText(dataArrayList.get(position).getWeather());
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }
    public static class InnerHolder extends RecyclerView.ViewHolder{
           TextView date;
           TextView tem;
           TextView weather;
        public InnerHolder(View view){
            super(view);
            weather=(TextView) view.findViewById(R.id.weather);
            tem=(TextView)view.findViewById(R.id.tem);
            date=(TextView)view.findViewById(R.id.tv_date);
        }
    }
}
