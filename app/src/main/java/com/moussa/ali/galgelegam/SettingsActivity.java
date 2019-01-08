package com.moussa.ali.galgelegam;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.preference.EditTextPreference;
import android.preference.SwitchPreference;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SettingsActivity extends PreferenceActivity implements Preference.OnPreferenceClickListener {

    private Preference deleteHighScore;
    private Preference rulesScreen;
    private Preference aboutScreen;
    private Preference playerNameInput;
    private Preference soundStatusSwitch;

    private Boolean soundStatus;

    SharedPreferences playerNameSharedPreferences;
    SharedPreferences settingsSharedPreferences;
    SharedPreferences SharedPreferencesCounter;
    SharedPreferences highScoreSharedPreferences;
    SharedPreferences playerNameSharedPreferencesPrefsManager;
    SharedPreferences sharedPreferencesPlayerNameCounter;
    SharedPreferences.Editor editorSettings;
    SharedPreferences.Editor editorPlayerNamePrefsManager;
    SharedPreferences.Editor editorPlayerNameCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.gamesettings);

        SharedPreferencesCounter = getSharedPreferences("sharedPrefCounter", Context.MODE_PRIVATE);
        highScoreSharedPreferences = getSharedPreferences("highScorePref", Context.MODE_PRIVATE);
        settingsSharedPreferences = getSharedPreferences("settingsPref", Context.MODE_PRIVATE);
        playerNameSharedPreferences = getSharedPreferences("playerNamePref", Context.MODE_PRIVATE);
        playerNameSharedPreferencesPrefsManager = getSharedPreferences("playerNamePrefsManager", Context.MODE_PRIVATE);
        sharedPreferencesPlayerNameCounter = getSharedPreferences("sharedPlayerCounter", Context.MODE_PRIVATE);


        deleteHighScore = findPreference("deleteHighScore");
        deleteHighScore.setOnPreferenceClickListener(this);
        rulesScreen = findPreference("rulesScreen");
        rulesScreen.setOnPreferenceClickListener(this);
        aboutScreen = findPreference("aboutScreen");
        aboutScreen.setOnPreferenceClickListener(this);
        playerNameInput = findPreference("playerNameInput");
        playerNameInput.setOnPreferenceClickListener(this);
        soundStatusSwitch = findPreference("soundEffectsSwitch");
        soundStatusSwitch.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {

        if (preference == deleteHighScore) {
            SharedPreferences.Editor editor = SharedPreferencesCounter.edit().clear();
            SharedPreferences.Editor editor2 = highScoreSharedPreferences.edit().clear();
            SharedPreferences.Editor editor3 = playerNameSharedPreferences.edit().clear();
            editor.apply();
            editor2.apply();
            editor3.apply();

            Toast.makeText(this, "Highscore list er nulstillet!",
                    Toast.LENGTH_SHORT).show();

        }

        if (preference == aboutScreen) {
            showAbout();
        }

        if (preference == playerNameInput) {
            playerNameInput();
        }

        if (preference == rulesScreen) {
            showRules();
        }

        if (preference == soundStatusSwitch) {
            soundStatusSwitch();
        }

        return true;
    }

    private void soundStatusSwitch() {
        SwitchPreference soundSwitch = (SwitchPreference) findPreference("soundEffectsSwitch");
        if (soundSwitch != null) {
            soundSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    soundStatus = settingsSharedPreferences.getBoolean("soundPref", true);

                    if (soundStatus) {
                        editorSettings = settingsSharedPreferences.edit();
                        editorSettings.putBoolean("soundPref", soundStatus);
                        editorSettings.apply();
                    } else {
                        editorSettings = settingsSharedPreferences.edit();
                        editorSettings.putBoolean("soundPref", soundStatus);
                        editorSettings.apply();
                    }
                    return true;
                }
            });
        }
    }


    private void showAbout() {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Galgeleg spillet er udviklet af Ali Harb El-Haj Moussa, s175119.");
        dlgAlert.setTitle("Om spillet");
        dlgAlert.create().show();
    }

    private void showRules() {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Galgespil er et spil for to eller flere spillere. Det er typisk spillet på papir eller en på en tavle. Spillet går ud på at gætte et ord eller en sætning, hvor kun antallet af bogstaver er kendt, lidt i stil med Lykkehjulet. Der gættes på skift et bogstav ad gangen, og korrekte bogstaver bliver placeret på de respektive pladser i ordet eller sætningen. For hver gang, der gættes forkert bliver der tegnet en del af en galge med en hængt mand på papiret eller tavlen. Opgaven går ud på gætte hele ordet eller sætningen inden tegningen bliver færdiggjort. Mængden af streger der bliver bliver tegnet for hvert forkert gæt, og tegningen kompleksitet kan varieres for at give spillerne mere eller mindre tid.");
        dlgAlert.setTitle("Spillets regler");
        dlgAlert.create().show();
    }

    private void playerNameInput() {
        //playerNameSharedPreferences

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String playerNameFromPrefsManager = preferences.getString("playerNameInput", "");

        editorPlayerNamePrefsManager = playerNameSharedPreferencesPrefsManager.edit();
        editorPlayerNamePrefsManager.putString("playerNamePrefsManager", playerNameFromPrefsManager);
        editorPlayerNamePrefsManager.apply();

        System.out.print(playerNameFromPrefsManager);

        //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //String username = preferences.getString("your_key", "default_value");
    }

}
