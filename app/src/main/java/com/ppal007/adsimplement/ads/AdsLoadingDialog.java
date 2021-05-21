package com.ppal007.adsimplement.ads;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;

import com.ppal007.adsimplement.R;

public class AdsLoadingDialog {
    private static AdsLoadingDialog instance = null;

    public AdsLoadingDialog() {

    }

    public static AdsLoadingDialog getInstance() {
        if (instance == null){
            instance = new AdsLoadingDialog();
        }
        return instance;
    }


    private int progressStatus = 0;
    private Dialog dialog;
    public void showAdsLoader(Activity activity){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.ad_loader_dialog_layout);

        //for full screen dialog
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //find progress bar
        ProgressBar progressBar = dialog.findViewById(R.id.progress_bar);

        //set progress
        new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus += 1;

                new Handler(Looper.getMainLooper()).post(() ->
                        //set progress status
                        progressBar.setProgress(progressStatus)
                );
                try {

                    Thread.sleep(8);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //show dialog
        dialog.show();

    }


    //dismiss dialog
    public void dismissAdsLoader(){
        dialog.dismiss();
        progressStatus = 0;
    }

}
