package com.moussa.ali.galgelegam;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mOpenGameIntentButton;
    private Button mOpenHighScoreIntentButton;
    private Button mOpenSettingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");

        mOpenGameIntentButton = (Button) findViewById(R.id.button_playGame);
        mOpenGameIntentButton.setOnClickListener(this);

        mOpenHighScoreIntentButton = (Button) findViewById(R.id.button_highScoreList);
        mOpenHighScoreIntentButton.setOnClickListener(this);

        mOpenSettingsButton = (Button) findViewById(R.id.button_viewSettings);
        mOpenSettingsButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //If statement for opening the appropriate view
        if (v == mOpenGameIntentButton) {
            Intent openGame = new Intent(this, GameActivity.class);
            startActivity(openGame);
        } else if (v == mOpenHighScoreIntentButton) {
            Intent openHighScore = new Intent(this, HighScoreActivity.class);
            startActivity(openHighScore);
        } else if (v == mOpenSettingsButton) {
            Intent openSettings = new Intent(this, SettingsActivity.class);
            startActivity(openSettings);
        }

    }

}
