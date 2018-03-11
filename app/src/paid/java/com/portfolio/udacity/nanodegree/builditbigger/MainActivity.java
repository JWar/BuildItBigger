package com.portfolio.udacity.nanodegree.builditbigger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.portfolio.udacity.android.builditbigger.AppsAsyncTask;
import com.portfolio.udacity.android.builditbigger.AppsAsyncTaskCallback;
import com.portfolio.udacity.android.builditbigger.R;
import com.portfolio.udacity.android.jokeintent.JokeIntentActivity;

//Probably could use a fragment to avoid redundant code. But it works.
public class MainActivity extends AppCompatActivity implements AppsAsyncTaskCallback {
    private AppsAsyncTask mAppsAsyncTask;

    //Checks to see if AsyncTask has been called. This is so system knows
    //to recall if config change happens during execute. As asynctask should
    //be cancelled and destroyed if activity is destroyed.
    private static final String ASYNC_CHECK = "asyncCheck";
    private boolean mAsyncCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState!=null) {
            mAsyncCheck = savedInstanceState.getBoolean(ASYNC_CHECK);
        }
    }
    public void onJokes(View v) {
        mAsyncCheck=true;
        mAppsAsyncTask = new AppsAsyncTask(this);
        mAppsAsyncTask.execute();
    }
    @Override
    public void setJoke(String aJoke) {
//        Toast.makeText(this, aJoke, Toast.LENGTH_SHORT).show();
        JokeIntentActivity.start(this,aJoke);
        mAsyncCheck=false;
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mAsyncCheck) {
            onJokes(null);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAppsAsyncTask!=null&&!mAppsAsyncTask.isCancelled()) {
            mAppsAsyncTask.cancel(true);
            mAppsAsyncTask=null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ASYNC_CHECK,mAsyncCheck);
    }
}
