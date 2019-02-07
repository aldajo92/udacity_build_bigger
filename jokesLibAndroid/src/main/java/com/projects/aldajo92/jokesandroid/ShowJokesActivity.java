package com.projects.aldajo92.jokesandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShowJokesActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA_KEY = "com.projects.aldajo92.jokes_extra";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_jokes);

        textView = findViewById(R.id.displayView);

        Intent intent = getIntent();

        if (intent.hasExtra(JOKE_EXTRA_KEY)) {
            textView.setText(intent.getStringExtra(JOKE_EXTRA_KEY));

        }
    }

}
