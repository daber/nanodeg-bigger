package abitcreative.pl.mylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

  public static String EXTRA_JOKE ="EXTRA_JOKE";
  private TextView jokeTv;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_joke);
    jokeTv = (TextView) findViewById(R.id.joke_tv);
    jokeTv.setText(getIntent().getStringExtra(EXTRA_JOKE));

  }
}
