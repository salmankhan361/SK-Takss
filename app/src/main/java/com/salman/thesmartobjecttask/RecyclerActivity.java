package com.salman.thesmartobjecttask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.salman.thesmartobjecttask.databinding.ActivityRecyclerBinding;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {
    private ActivityRecyclerBinding binding;
    private Adapter adapter;
    private ArrayList<String> countryNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecyclerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        countryNames = new ArrayList<>();
        addDataToList();

        adapter = new Adapter(countryNames,this);
        binding.recView.setAdapter(adapter);

    }

    private void addDataToList() {
        countryNames.add("Pakistan");
        countryNames.add("Afghanistan");
        countryNames.add("Turkey");
        countryNames.add("USA");
        countryNames.add("China");
        countryNames.add("Germany");
        countryNames.add("Italy");
        countryNames.add("Sweden");
        countryNames.add("Spain");
        countryNames.add("Portugal");
    }
}