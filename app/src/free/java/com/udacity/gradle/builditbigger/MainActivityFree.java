package com.udacity.gradle.builditbigger;

import abitcreative.pl.mylibrary.JokeActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.async.EndpointsAsyncTask;


public class MainActivityFree extends AppCompatActivity {
  private EndpointsAsyncTask task             = null;
  private InterstitialAd     mInterstitialAd  = null;
  private ProgressBar        loadingIndicator = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    loadingIndicator = (ProgressBar) findViewById(R.id.loadingIndicator);
    mInterstitialAd = new InterstitialAd(this);
    mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

    mInterstitialAd.setAdListener(new AdListener() {
      @Override
      public void onAdClosed() {
        requestNewInterstitial();
        doTellJoke();
      }
    });

    requestNewInterstitial();
  }

  private void requestNewInterstitial() {
    AdRequest adRequest = new AdRequest.Builder()
        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
        .build();

    mInterstitialAd.loadAd(adRequest);
  }


  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (task != null) {
      task.cancel(true);
      task = null;
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void doTellJoke() {
    task = new EndpointsAsyncTask() {
      @Override
      protected void onPreExecute() {
        super.onPreExecute();
        indicateLoading(true);
      }

      @Override
      protected void onPostExecute(String result) {
        indicateLoading(false);
        if(result!=null) {
          Intent i = new Intent(MainActivityFree.this, JokeActivity.class);
          i.putExtra(JokeActivity.EXTRA_JOKE, result);
          startActivity(i);
        }else{
          Toast.makeText(MainActivityFree.this, R.string.error_failed_joke_fetch,Toast.LENGTH_LONG).show();
        }
      }
    };
    task.execute();


  }


  private void indicateLoading(boolean loading) {
    loadingIndicator.setVisibility(loading ? View.VISIBLE : View.GONE);
  }

  public void tellJoke(View view) {
    if (mInterstitialAd.isLoaded()) {
      mInterstitialAd.show();
    } else {
      doTellJoke();
    }


  }
}
