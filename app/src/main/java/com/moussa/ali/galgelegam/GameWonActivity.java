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

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

import static com.moussa.ali.galgelegam.GameActivity.logik;

public class GameWonActivity extends AppCompatActivity {

    private int getAntalForkerteBogstaver;
    private Boolean soundStatus;

    private TextView mGetAntalForkerteBogstaverTextview;
    private KonfettiView mKonfettiView;

    SharedPreferences settingsSharedPreferences;
    SharedPreferences sharedPreferencesCounter;
    SharedPreferences highScoreSharedPreferences;
    SharedPreferences.Editor editorCounter;
    SharedPreferences.Editor editorHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);

        getAntalForkerteBogstaver = getIntent().getIntExtra("getAntalForkerteBogstaver", 0);

        mGetAntalForkerteBogstaverTextview = (TextView) findViewById(R.id.textView_getAntalForkerteBogstaver);
        mKonfettiView = (KonfettiView) findViewById(R.id.viewKonfetti);

        settingsSharedPreferences = getSharedPreferences("settingsPref", Context.MODE_PRIVATE);
        soundStatus = settingsSharedPreferences.getBoolean("soundPref", true);

        gameWon();
    }

    private void gameWon() {
        mGetAntalForkerteBogstaverTextview.setText("" + getAntalForkerteBogstaver);

        if (soundStatus) {
            MediaPlayer.create(this, R.raw.congratulations).start();
        }

        //https://github.com/DanielMartinus/Konfetti
        mKonfettiView.build()
                .addColors(Color.DKGRAY, Color.rgb(80, 73, 51), Color.rgb(96, 89, 68))
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5))
                .setPosition(-50f, mKonfettiView.getWidth() + 50f, -50f, -50f)
                .streamFor(300, 3000L);

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
