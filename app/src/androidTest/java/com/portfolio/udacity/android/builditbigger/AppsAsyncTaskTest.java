package com.portfolio.udacity.android.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by JonGaming on 11/03/2018.
 * Does what it says on the tin...
 */
@RunWith(AndroidJUnit4.class)
public class AppsAsyncTaskTest extends AndroidTestCase implements AppsAsyncTaskCallback {
    @Test
    public void testAsyncTask() throws Exception {
        try {
            AppsAsyncTask appsAsyncTask = new AppsAsyncTask(this);
            String joke = appsAsyncTask.execute().get(30, TimeUnit.SECONDS);
            assertThat(joke, notNullValue());
            assertTrue(joke.length()>0);
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Override
    public void setJoke(String aJoke) {
        //Not needed for test?
    }
}
