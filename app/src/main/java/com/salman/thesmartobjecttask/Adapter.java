package com.salman.thesmartobjecttask;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salman.thesmartobjecttask.databinding.SingleItemBinding;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {

    private Activity activity;
    private ArrayList<String> countryNames;

    public Adapter(ArrayList<String> countryNames,Activity activity) {

        this.countryNames = countryNames;
        this.activity = activity;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.single_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.binding.txtCountryName.setText(countryNames.get(position));

        holder.itemView.setOnClickListener(v->{
            InterstialAdManager.showAndLoadInterstitial(activity,false,activity.getResources().getString(R.string.interstitial_ad_id));
        });

    }

    @Override
    public int getItemCount() {
        return countryNames.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        private SingleItemBinding binding;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SingleItemBinding.bind(itemView);
        }
    }
}
