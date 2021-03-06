package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest implements EndpointsAsyncTask.EndPointRequestListener {

    private Context instrumentationContext;

    @Before
    public void setup() {
        instrumentationContext = InstrumentationRegistry.getContext();
    }

    @Test
    public void testVerifyJoke() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        EndpointsAsyncTask testTask = new EndpointsAsyncTask(this) {

            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);

                if (result != null) {
                    assertTrue(result.length() > 0);
                    latch.countDown();
                }
            }
        };
        testTask.execute();
        latch.await();
    }

    @Override
    public void processFinish(String output) {

    }
}