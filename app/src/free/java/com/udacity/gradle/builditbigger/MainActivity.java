package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.projects.aldajo92.jokesandroid.ShowJokesActivity;

import static com.udacity.gradle.builditbigger.Constants.AD_ID;

public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.EndPointRequestListener {

    private InterstitialAd interstitialAd;

    private AdView adView;
    private Button button;
    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        interstitialAd = new InterstitialAd(this);

        adView = findViewById(R.id.adView);
        button = findViewById(R.id.buttonAsync);

        spinner = findViewById(R.id.progressBar);

        configureAd();

        configureViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }

    private void configureAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.setAdUnitId(AD_ID);
        adView.loadAd(adRequest);
    }

    private void configureViews() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoader(true);
                requestJoke();

                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }

            }
        });
    }

    private void requestJoke() {
        new EndpointsAsyncTask(this).execute();
    }

    private void showLoader(boolean show) {
        if (show) {
            spinner.setVisibility(View.VISIBLE);
        } else {
            spinner.setVisibility(View.GONE);
        }
    }

    @Override
    public void processFinish(String output) {
        Intent intent = new Intent(this, ShowJokesActivity.class);
        intent.putExtra(ShowJokesActivity.JOKE_EXTRA_KEY, output);
        showLoader(false);
        startActivity(intent);
    }

}
