package com.salman.thesmartobjecttask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InterstialAdManager {
    private static final String TAG = "InterstitialADTag";
    public static InterstitialAd mInterstitialAd = null;
    private static ProgressDialog dialog;

    public static void showAndLoadInterstitial(Activity activity, Boolean isFromSplash, String ad_id) {
        if (isNetworkAvailable(activity)) {

            dialog = new ProgressDialog(activity);
            if(!isFromSplash){
                    dialog.setMessage("Loading...");
                    dialog.show();

            }

            Log.i("ErrorLogTest", "showAndLoadInterstitial: show and load called");

            if (mInterstitialAd != null) {


                    mInterstitialAd.show(activity);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdClicked() {
                            super.onAdClicked();
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            mInterstitialAd = null;
                            loadInterstitialAd(activity, ad_id,isFromSplash);
                            if(isFromSplash){
                                startActivity(activity);
                            }
                            try {
                                dialog.dismiss();
                            }catch (Exception e){e.printStackTrace();}

                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            if(isFromSplash){
                                startActivity(activity);
                            }
                            try {
                                dialog.dismiss();
                            }catch (Exception e){e.printStackTrace();}

                        }

                        @Override
                        public void onAdImpression() {
                            super.onAdImpression();


                        }
                    });


                }
            } else {
            if (isFromSplash){
                startActivity(activity);
            }
            }
        }


        public static void loadInterstitialAd (Activity activity, String ad_id,Boolean isFromSplash){

            if (mInterstitialAd == null) {

                Log.d(TAG, "Ad load called.");
                InterstitialAd.load(activity, ad_id, new AdRequest.Builder().build(), new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        mInterstitialAd = null;
                        if(isFromSplash){
                            startActivity(activity);
                        }
                        try {
                            dialog.dismiss();
                        }catch (Exception e){e.printStackTrace();}

                    }

                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        super.onAdLoaded(interstitialAd);
                        mInterstitialAd = interstitialAd;
                        try {
                            dialog.dismiss();
                        }catch (Exception e){e.printStackTrace();}

                    }
                });
            }
        }



        public static boolean isNetworkAvailable (Context context){
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Network nw = connectivityManager.getActiveNetwork();
                NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);

                if (actNw != null) {
                    if (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true;
                    } else if (actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true;
                    } else return actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
                } else
                    return false;
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    return connectivityManager.isDefaultNetworkActive();
                } else
                    return false;
            }

        }

        public static void startActivity(Activity context){
        context.startActivity(new Intent(context,MainActivity.class));
        context.finish();
        }


    }

