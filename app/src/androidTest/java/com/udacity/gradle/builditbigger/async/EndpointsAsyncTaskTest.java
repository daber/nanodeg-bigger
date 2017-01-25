package com.udacity.gradle.builditbigger.async;

import android.os.AsyncTask;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

/**
 * Created by mdabrowski on 24/01/17.
 */
@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {
  @MediumTest
  @Test
  public void jokeReturnedTest() throws ExecutionException, InterruptedException {

    EndpointsAsyncTask task = new EndpointsAsyncTask() {
      @Override
      protected void onPostExecute(String result) {

      }
    };

    String result = task.execute().get();
    Assert.assertTrue(task.getStatus()== AsyncTask.Status.FINISHED);
    Assert.assertNotNull(result);
  }


}