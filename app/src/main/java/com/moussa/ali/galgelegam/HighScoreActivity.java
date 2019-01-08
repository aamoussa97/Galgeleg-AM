package com.moussa.ali.galgelegam;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HighScoreActivity extends AppCompatActivity {

    private ListView mListViewHighScoreList;
    private ListView mListViewHighScoreListName;

    private int scoreCounter;
    private int score;
    private String playerNameFromSharedPrefs;
    private int playerCounter;
    private int newPlayerCounter;

    SharedPreferences playerNameSharedPreferences;
    SharedPreferences SharedPreferencesCounter;
    SharedPreferences highScoreSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        setTitle("Highscore liste");

        mListViewHighScoreList = (ListView) findViewById(R.id.listView_highScoreListView);
        mListViewHighScoreListName = (ListView) findViewById(R.id.listView_highScoreListViewName);

        //Sets SharedPreferences up
        SharedPreferencesCounter = getSharedPreferences("sharedPrefCounter", Context.MODE_PRIVATE);
        highScoreSharedPreferences = getSharedPreferences("highScorePref", Context.MODE_PRIVATE);
        playerNameSharedPreferences = getSharedPreferences("playerNamePref", Context.MODE_PRIVATE);

        //Builds Android back button.
        if (getSupportActionBar() != null) {
            //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        readHighScoreFromSharedPreferences();
    }

    public void readHighScoreFromSharedPreferences() {

        //https://stackoverflow.com/questions/5070830/populating-a-listview-using-an-arraylist
        ArrayList<Integer> highScoreArrayList = new ArrayList<Integer>();
        ArrayList<String> highScoreArrayListName = new ArrayList<String>();

        SharedPreferencesCounter = getSharedPreferences("sharedPrefCounter", Context.MODE_PRIVATE);
        scoreCounter = SharedPreferencesCounter.getInt("prefCount", 0);
        playerCounter = SharedPreferencesCounter.getInt("prefNameCount", 0);

        for (int i = 0; i < scoreCounter; i++) {
            highScoreSharedPreferences = getSharedPreferences("highScorePref", Context.MODE_PRIVATE);
            score = highScoreSharedPreferences.getInt("highScorePref" + i, 0);

            highScoreArrayList.add(score);
        }

        for (int i = 0; i < playerCounter; i++) {
            playerNameSharedPreferences = getSharedPreferences("playerNamePref", Context.MODE_PRIVATE);
            playerNameFromSharedPrefs = playerNameSharedPreferences.getString("playerNamePref" + i, "Default");

            highScoreArrayListName.add(playerNameFromSharedPrefs);
        }

        /*for (int i = 0; i < scoreCounter; i++) {
            highScoreSharedPreferences = getSharedPreferences("highScorePref", Context.MODE_PRIVATE);
            score = highScoreSharedPreferences.getInt("highScorePref" + i, 0);

            playerNameSharedPreferences = getSharedPreferences("playerNamePref", Context.MODE_PRIVATE);
            playerNameFromSharedPrefs = playerNameSharedPreferences.getString("playerNamePref" + i, "");

            highScoreArrayList.add(score);
            highScoreArrayListName.add(playerNameFromSharedPrefs);
        }*/

        /*
        Collections.sort(highScoreArrayList);
        Collections.reverse(highScoreArrayList);
        */

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, highScoreArrayList);
        ArrayAdapter<String> arrayAdapterName = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, highScoreArrayListName);
        mListViewHighScoreList.setAdapter(arrayAdapter);
        mListViewHighScoreListName.setAdapter(arrayAdapterName);
    }

    //https://developer.android.com/training/appbar/actions#java
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
