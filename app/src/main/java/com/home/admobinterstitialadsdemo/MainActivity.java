package com.home.admobinterstitialadsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** 初始化移動廣告SDK */
        MobileAds.initialize(this, "應用程式ID");

        /** 實例化InterstitialAd 並設置其廣告單元ID 以及監聽 */
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("廣告單元ID");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {

            /** 廣告完成加載時要執行的代碼 */
            @Override
            public void onAdLoaded() {
            }

            /** 廣告請求失敗時要執行的代碼 */
            @Override
            public void onAdFailedToLoad(int errorCode) {
            }

            /** 顯示廣告時要執行的代碼 */
            @Override
            public void onAdOpened() {
            }

            /** 用戶離開應用程序時要執行的代碼 */
            @Override
            public void onAdLeftApplication() {
            }

            /** 關閉插頁式廣告時要執行的代碼 */
            @Override
            public void onAdClosed() {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                finish();
            }
        });
    }

    /** 先判斷要展示用的插頁式廣告 是否已經準備好, 如果準備好就直接展示, 否則就跳頁 */
    public void startMain2Activity(View view) {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        }
    }
}
