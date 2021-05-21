package com.ppal007.adsimplement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ppal007.adsimplement.ads.AdsCallBack;
import com.ppal007.adsimplement.ads.AdsLoadingDialog;
import com.ppal007.adsimplement.ads.AdsManager;

public class MainActivity extends AppCompatActivity implements AdsCallBack {

    private Button buttonAdmobInter;
    private AdsManager adsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdmobInter = findViewById(R.id.show_admob_inter);
        //initialize ads
        adsManager = new AdsManager(MainActivity.this,this);

        buttonAdmobInter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AdsLoadingDialog.getInstance().showAdsLoader(MainActivity.this);
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //show ads
                        adsManager.showInterAds();

                    }
                },2000);

            }
        });
    }

    @Override
    public void onAdClose() {

        Toast.makeText(this, "ok done !", Toast.LENGTH_SHORT).show();
        AdsLoadingDialog.getInstance().dismissAdsLoader();

    }

    @Override
    public void onAdFailToLoad() {

        Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        AdsLoadingDialog.getInstance().dismissAdsLoader();

    }

    @Override
    public void onAdError() {

        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        AdsLoadingDialog.getInstance().dismissAdsLoader();

    }
}