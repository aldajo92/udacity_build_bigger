package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.projects.aldajo92.jokesandroid.ShowJokesActivity;

public class MainActivity extends BaseActivity implements EndpointsAsyncTask.EndPointRequestListener {

    private InterstitialAd interstitialAd;

    private AdView adView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        interstitialAd = new InterstitialAd(this);

        initViews();

        configureAd();

    }

    @Override
    protected void onResume() {
        super.onResume();
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }

    private void initViews() {
        adView = findViewById(R.id.adView);
        button = findViewById(R.id.button_request);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoader(true);
                showInterstitialAd();
                requestJoke();

            }
        });
    }

    private void configureAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        adView.loadAd(adRequest);
    }

    private void showInterstitialAd() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
    }

    private void requestJoke() {
        new EndpointsAsyncTask(this).execute();
    }

    @Override
    public void processFinish(String output) {
        Intent intent = new Intent(this, ShowJokesActivity.class);
        intent.putExtra(ShowJokesActivity.JOKE_EXTRA_KEY, output);
        startActivity(intent);
        showLoader(false);
    }

}
