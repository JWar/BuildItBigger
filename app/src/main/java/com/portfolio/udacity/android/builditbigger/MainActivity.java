package com.portfolio.udacity.android.builditbigger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.portfolio.udacity.android.javajokes.Jokes;
import com.portfolio.udacity.android.jokeintent.JokeIntentActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onJokes(View v) {
        Jokes jokes = new Jokes();
        Toast.makeText(this, jokes.getJoke(), Toast.LENGTH_SHORT).show();
        JokeIntentActivity.start(this,jokes.getJoke());
    }
}
