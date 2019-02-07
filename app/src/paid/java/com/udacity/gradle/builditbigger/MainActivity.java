package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.projects.aldajo92.jokesandroid.ShowJokesActivity;

public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.EndPointRequestListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void requestJoke(View view) {
        new EndpointsAsyncTask(this).execute();
    }

    @Override
    public void processFinish(String output) {
        Intent intent = new Intent(this, ShowJokesActivity.class);
        intent.putExtra(ShowJokesActivity.JOKE_EXTRA_KEY, output);
        startActivity(intent);
    }
}
