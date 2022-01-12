package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
//This part belongs to Mertcan Onur
public class AcommodatorRVAdapter extends RecyclerView.Adapter<AcommodatorRVAdapter.ViewHolder> {
    private ArrayList<User> acommodatorsArray;
    private Context context;

    public AcommodatorRVAdapter(ArrayList<User> acommodatorsArray, Context context) {
        this.acommodatorsArray = acommodatorsArray;
        this.context = context;
    }

    @NonNull
    @Override
    public AcommodatorRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.accommodator_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AcommodatorRVAdapter.ViewHolder holder, int position) {
        User user = acommodatorsArray.get(position);
        holder.userName.setText(user.getName());
        holder.userJob.setText(user.getJob());

    }

    @Override
    public int getItemCount() {
        return acommodatorsArray.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView userName;
        private final TextView userJob;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.idAccommodatorsName);
            userJob = itemView.findViewById(R.id.idAcommodatorsJob);
        }
    }
}
