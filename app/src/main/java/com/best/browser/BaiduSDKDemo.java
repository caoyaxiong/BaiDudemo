package com.best.browser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.mobads.BaiduManager;



public class BaiduSDKDemo extends Activity {

    public static final String TAG = "BaiduSDKDemo";
    
    Button simpleCoding;
    Button simpleVideo;
    Button simpleInter;
    Button simpleInterForVideoApp;
    Button prerollVideo;
    Button simpleIcon;
    Button simpleRecomAd;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // 广告展现前先调用sdk初始化方法，可以有效缩短广告第一次展现所需时间
        BaiduManager.init(this);
        Button simpleCoding = (Button) findViewById(R.id.simple_coding);
        simpleCoding.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BaiduSDKDemo.this, BannerAd1Activity.class);
                startActivity(intent);
            }
        });

        Button simpleInter = (Button) findViewById(R.id.simple_inters);
        simpleInter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BaiduSDKDemo.this, InterstitialAdActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");

        // 通过BaiduXAdSDKContext.exit()来告知AdSDK，以便AdSDK能够释放资源.
        com.baidu.mobads.production.BaiduXAdSDKContext.exit();

        super.onDestroy();
    }
}

