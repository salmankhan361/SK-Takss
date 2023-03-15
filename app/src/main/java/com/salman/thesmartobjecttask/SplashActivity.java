package com.salman.thesmartobjecttask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.ads.MobileAds;
import com.salman.thesmartobjecttask.databinding.ActivityMainBinding;
import com.salman.thesmartobjecttask.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MobileAds.initialize(this);
        handler = new Handler();

        InterstialAdManager.loadInterstitialAd(this,getResources().getString(R.string.interstitial_ad_id),true);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               InterstialAdManager.showAndLoadInterstitial(SplashActivity.this,true,getResources().getString(R.string.interstitial_ad_id));
            }
        },5000);



    }
}