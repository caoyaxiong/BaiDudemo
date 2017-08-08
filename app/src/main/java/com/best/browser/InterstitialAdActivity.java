package com.best.browser;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.baidu.mobads.InterstitialAd;
import com.baidu.mobads.InterstitialAdListener;

// import com.baidu.mobads.standarddemo.R;

public class InterstitialAdActivity extends Activity {

    InterstitialAd interAd;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interstitial);
        sharedPreferences = getSharedPreferences("baidu",0);
        editor = sharedPreferences.edit();
        flag = sharedPreferences.getBoolean("spf",false);
        // 默认请求http广告，若需要请求https广告，请设置AdSettings.setSupportHttps为true
        // AdSettings.setSupportHttps(true);
        
        String adPlaceId = "4621032"; // 重要：请填上您的广告位ID，代码位错误会导致无法请求到广告
        interAd = new InterstitialAd(this, adPlaceId);
        interAd.setListener(new InterstitialAdListener() {

            @Override
            public void onAdClick(InterstitialAd arg0) {
                Log.i("InterstitialAd", "onAdClick");
            }

            @Override
            public void onAdDismissed() {
                Log.i("InterstitialAd", "onAdDismissed");
                interAd.loadAd();
            }

            @Override
            public void onAdFailed(String arg0) {
                Log.i("InterstitialAd", "onAdFailed");
            }

            @Override
            public void onAdPresent() {
                Log.i("InterstitialAd", "onAdPresent");
            }

            @Override
            public void onAdReady() {
                Log.i("InterstitialAd", "onAdReady");
                if(flag==false) {
                    interAd.showAd(InterstitialAdActivity.this);
                    flag=true;
                    editor.putBoolean("spf",flag);
                    editor.commit();
                }
            }
        });
        interAd.loadAd();
    }

    @Override
    protected void onStop() {
        super.onStop();
        flag=false;
        editor.putBoolean("spf",flag);
        editor.commit();
    }
}
