/*
 * (c) Neofonie Mobile GmbH (2017)
 *
 * This computer program is the sole property of Neofonie Mobile GmbH (http://mobile.neofonie.de)
 * and is protected under the German Copyright Act (paragraph 69a UrhG).
 *
 * All rights are reserved. Making copies, duplicating, modifying, using or distributing
 * this computer program in any form, without prior written consent of Neofonie Mobile GmbH, is prohibited.
 * Violation of copyright is punishable under the German Copyright Act (paragraph 106 UrhG).
 *
 * Removing this copyright statement is also a violation.
 */
package com.udacity.gradle.builditbigger.async;

import android.content.Context;
import android.os.AsyncTask;
import com.example.mdabrowski.myapplication.backend.myApi.MyApi;
import com.example.mdabrowski.myapplication.backend.myApi.model.JokeBean;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by mdabrowski on 24/01/17.
 */
public abstract class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
  private static MyApi myApiService = null;
  private Context context;

  @Override
  protected String doInBackground(Void... params) {
    if(myApiService == null) {  // Only do this once
      MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
          new AndroidJsonFactory(), null)
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

      myApiService = builder.build();
    }

    try {

      AbstractGoogleClientRequest<JokeBean> request =myApiService.sayJoke();
      JokeBean bean = request.execute();

      return bean.getJoke();
    } catch (IOException e) {
      return null;
    }
  }

  @Override
  protected abstract void onPostExecute(String result) ;
}