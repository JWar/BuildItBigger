package com.portfolio.udacity.android.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by JonGaming on 10/03/2018.
 * Handles Asynctask, connecting to Api Endpoint and delivering result via the listener/callback
 */

public class AppsAsyncTask  extends AsyncTask<Void,Void,String> {
    private static MyApi sMyApi = null;

    private AppsAsyncTaskCallback mListener;

    public AppsAsyncTask(AppsAsyncTaskCallback aAppsAsyncTaskCallback) {
        mListener=aAppsAsyncTaskCallback;
    }

    @Override
    protected String doInBackground(Void... aVoids) {
        if(sMyApi == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(
                    AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),
                    null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver
            sMyApi = builder.build();
        }
        try {
            return sMyApi.getJoke().execute().getData();
        } catch (IOException e) {
            Log.e("IOException: ",e+"");
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String aJoke) {
        if (mListener!=null) {
            mListener.setJoke(aJoke);
        } else {
            Log.i("BuildItBigger","AppsAsyncTask.onPostExecute listener is null");
        }
    }
}
