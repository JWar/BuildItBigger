package com.portfolio.udacity.android.jokeintent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeIntentActivity extends AppCompatActivity {

    private static String JOKE_KEY = "jokeKey";

    public static void start(Context aContext, String aJoke) {
        Intent intent = new Intent(aContext,JokeIntentActivity.class);
        intent.putExtra(JOKE_KEY,aJoke);
        aContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_intent);
        ((TextView)findViewById(R.id.activity_joke_intent_tv)).setText(getIntent().getStringExtra(JOKE_KEY));
    }
}
