package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.projects.aldajo92.jokesandroid.ShowJokesActivity;

public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.AsyncResponse {

    private String joke;

    private InterstitialAd mInterstitial;

    private AdView mAdView;
    private Button mButton;

    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitial = new InterstitialAd(this);

        mAdView = findViewById(R.id.adView);
        mButton = findViewById(R.id.buttonAsync);

        spinner = findViewById(R.id.progressBar);

        configureAd();

        configureViews();

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);


    }

    private void configureViews() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requestJoke();

                spinner.setVisibility(View.VISIBLE);
                if (mInterstitial.isLoaded()) {
                    mInterstitial.show();

                }
//                else {
//                    Log.d("TAG", "The interstitial wasn't loaded yet.");
//                }

            }
        });
    }

    private void requestJoke() {
        new EndpointsAsyncTask() {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                joke = result;

            }
        }.execute(this);
    }

    private void configureAd() {
        mInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                openJokeActivity();
            }
        });

        mInterstitial.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitial.loadAd(new AdRequest.Builder().build());
    }

    private void openJokeActivity() {
        Intent intent = new Intent(this, ShowJokesActivity.class);
        intent.putExtra(ShowJokesActivity.JOKE_EXTRA_KEY, joke);
        spinner.setVisibility(View.INVISIBLE);
        startActivity(intent);
    }

    @Override
    public void processFinish(String output) {
        Intent intent = new Intent(this, ShowJokesActivity.class);
        intent.putExtra(ShowJokesActivity.JOKE_EXTRA_KEY, output);
        startActivity(intent);
    }

}
