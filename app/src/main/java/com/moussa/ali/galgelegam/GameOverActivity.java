package com.moussa.ali.galgelegam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.moussa.ali.galgelegam.GameLogic.Galgelogik;

import static com.moussa.ali.galgelegam.GameActivity.logik;

public class GameOverActivity extends AppCompatActivity {

    private String getOrdet;
    private Boolean soundStatus;

    private TextView mGetOrdetTextview;

    SharedPreferences settingsSharedPreferences;
    SharedPreferences sharedPreferencesCounter;
    SharedPreferences highScoreSharedPreferences;
    SharedPreferences.Editor editorCounter;
    SharedPreferences.Editor editorHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        getOrdet = getIntent().getStringExtra("getOrdet");
        mGetOrdetTextview = (TextView) findViewById(R.id.textView_getOrdet);

        settingsSharedPreferences = getSharedPreferences("settingsPref", Context.MODE_PRIVATE);
        soundStatus = settingsSharedPreferences.getBoolean("soundPref", true);

        gameOver();
    }

    private void gameOver() {
        mGetOrdetTextview.setText("" + getOrdet);

        if (soundStatus) {
            MediaPlayer.create(this, R.raw.decay).start();
        }

        logik.nulstil();

        //Wait 4 seconds, then clear screen by using updateScreen() method.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //GameActivity.updateScreen();
                finish();

                /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);*/
            }
        }, 4000);
    }
}
