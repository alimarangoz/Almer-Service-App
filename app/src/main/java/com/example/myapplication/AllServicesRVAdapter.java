package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllServicesRVAdapter extends RecyclerView.Adapter<AllServicesRVAdapter.ViewHolder> {


    private ArrayList<User> allServicesArray;
    private Context context;

    public AllServicesRVAdapter(ArrayList<User> allServicesArray, Context context) {
        this.allServicesArray = allServicesArray;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.all_services_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User user = allServicesArray.get(position);

        holder.userName.setText("Name     : "+user.getName());
        holder.userJob.setText("Job         : "+user.getJob());
        holder.day.setText("Day         : "+user.getDay()+" days"); //TODO
        holder.location.setText("Location : "+user.getLocation());


    }

    @Override
    public int getItemCount() {
        return allServicesArray.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView userName;
        private final TextView userJob;
        private final TextView day;
        private final TextView location;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.idAllServicesName);
            userJob = itemView.findViewById(R.id.idAllServicesJob);
            day = itemView.findViewById(R.id.idAllServicesDay);
            location = itemView.findViewById(R.id.idAllServicesLocation);
        }
    }
}
