package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.projects.aldajo92.jokesandroid.ShowJokesActivity;

public class MainActivity extends BaseActivity implements EndpointsAsyncTask.EndPointRequestListener {

    private Button buttonRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        buttonRequest = findViewById(R.id.button_request);
        buttonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestJoke();
            }
        });
    }

    public void requestJoke() {
        showLoader(true);
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
