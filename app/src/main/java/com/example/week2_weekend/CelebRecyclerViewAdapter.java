package com.example.week2_weekend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.week2_weekend.model.celebrity.Celebrity;

import java.util.ArrayList;

public class CelebRecyclerViewAdapter extends RecyclerView.Adapter<CelebRecyclerViewAdapter.ViewHolder> {
    ArrayList<Celebrity> celebList = new ArrayList<>();
    //CelebViewCallback callback;

    public CelebRecyclerViewAdapter(ArrayList<Celebrity> celebList)//CelebViewCallback callback){
    {this.celebList = celebList;
        //this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.celebrity_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Celebrity currentCeleb = celebList.get(position);
        holder.tvCelebName.setText(currentCeleb.getCelebrityName());
        holder.tvCelebType.setText(currentCeleb.getCelebrityType());
        holder.setCelebrity(currentCeleb);
    }

    public void onDatabaseChange(ArrayList<Celebrity> updateList){
        celebList = updateList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return celebList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvCelebName;
        TextView tvCelebType;
        Celebrity celebrity;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCelebName = itemView.findViewById(R.id.tvCelebName);
            tvCelebType = itemView.findViewById(R.id.tvCelebType);
            itemView.setOnClickListener(this);
        }
        public Celebrity getCelebrity() {
            return celebrity;
        }

        public void setCelebrity(Celebrity celebrity) {
            this.celebrity = celebrity;
        }

        @Override
        public void onClick(View view) { //callback.onCelebSelected(celebrity);
        }
    }

//    interface CelebViewCallback{
//        void onCelebSelected(Celebrity celebSelected);
//    }
}