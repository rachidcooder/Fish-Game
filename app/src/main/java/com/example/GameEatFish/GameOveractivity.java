package com.example.GameEatFish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class GameOveractivity extends AppCompatActivity {
TextView txtbtn,textscore;
    private InterstitialAd mInterstitialAd;
AdView mAdView;
boolean isActivityWork=false;
    AdRequest adRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_overactivity);
        txtbtn=findViewById(R.id.button);
        textscore=findViewById(R.id.text_score);

        //Ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);

        mAdView.loadAd(adRequest);

        //get Data
        Intent back=getIntent();
       int score= back.getIntExtra("score",0);
        textscore.setText("score :"+score);
        txtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inback=new Intent(GameOveractivity.this, com.example.GameEatFish.MainActivity.class);
                startActivity(inback);
            }
        });


        if(isActivityWork){
            LoadAdsInterstitial();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        isActivityWork=true;
    }



    @Override
    protected void onStop() {
        super.onStop();
        isActivityWork=false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isActivityWork=false;
    }

    private void LoadAdsInterstitial(){
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        //if(mInterstitialAd!=null)
                            mInterstitialAd.show(GameOveractivity.this);
                        Log.i("Load Ads", "onAdLoaded");

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                        Log.d("Error", loadAdError.toString());
                    }
                });
    }
}