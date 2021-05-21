package com.ppal007.adsimplement.ads;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.ppal007.adsimplement.R;

public class AdsManager {

    private Activity activity;
    private AdsCallBack adsCallBack;
    private InterstitialAd mInterstitialAd;

    public AdsManager(Activity activity, AdsCallBack adsCallBack) {
        this.activity = activity;
        this.adsCallBack = adsCallBack;

        //load ads
        loadInterAds();
    }



    public void showInterAds(){

        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);

            setCallback();

        } else {
            adsCallBack.onAdError();
        }

    }

    private void setCallback() {
        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);

                adsCallBack.onAdFailToLoad();
            }

            @Override
            public void onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent();

                loadInterAds();
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent();

                adsCallBack.onAdClose();

            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }
        });
    }


    public void loadInterAds() {
        MobileAds.initialize(activity, initializationStatus -> {});
        AdRequest adRequest = new AdRequest.Builder().build();
        String adCode = activity.getResources().getString(R.string.admob_inter);

        InterstitialAd.load(activity, adCode, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;
            }
        });
    }
}
