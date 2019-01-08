package com.moussa.ali.galgelegam;

import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.moussa.ali.galgelegam.GameLogic.Galgelogik;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    static Galgelogik logik = new Galgelogik();

    private Button mCloseGameIntentButton;
    private Button mWordFromCategory;
    private Button mPlayRoundButton;
    private Button mGetWordFromDRButton;
    private EditText mEditTextTextBox;
    private ImageView mHangStatusImgView;
    private TextView mInfoTextView;
    private TextView mAmountOfLettersTextView;
    private ProgressDialog mProgressBar;

    private Boolean soundStatus;

    private String playerNameFromPrefsManager;
    private String playerNameFromSharedPrefs;
    private int amountOfLetters;
    private int scoreFromWrongAttempts;
    private int ImgViewCounter;
    private int newScoreCounter;
    private int scoreCounter;
    private int playerCounter;
    private int newPlayerCounter;

    SharedPreferences playerNameSharedPreferencesPrefsManager;
    SharedPreferences playerNameSharedPreferences;
    SharedPreferences settingsSharedPreferences;
    SharedPreferences sharedPreferencesCounter;
    SharedPreferences highScoreSharedPreferences;
    SharedPreferences.Editor editorPlayerName;
    SharedPreferences.Editor editorCounter;
    SharedPreferences.Editor editorHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setTitle("Galgeleg");

        mEditTextTextBox = (EditText) findViewById(R.id.editText_playTextboxInput);

        //Allows for enter taps on keyboard to play round.
        mEditTextTextBox.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mPlayRoundButton.performClick();
                    return true;
                }
                return false;
            }
        });

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        //Sets SharedPreferences up
        settingsSharedPreferences = getSharedPreferences("settingsPref", Context.MODE_PRIVATE);
        sharedPreferencesCounter = getSharedPreferences("sharedPrefCounter", Context.MODE_PRIVATE);
        highScoreSharedPreferences = getSharedPreferences("highScorePref", Context.MODE_PRIVATE);
        playerNameSharedPreferences = getSharedPreferences("playerNamePref", Context.MODE_PRIVATE);
        playerNameSharedPreferencesPrefsManager = getSharedPreferences("playerNamePrefsManager", Context.MODE_PRIVATE);

        soundStatus = settingsSharedPreferences.getBoolean("soundPref", true);

        mInfoTextView = (TextView) findViewById(R.id.textView_infoGame);
        mAmountOfLettersTextView = (TextView) findViewById(R.id.textView_amountOfLetters);

        //Sets standard image at boot.
        mHangStatusImgView = (ImageView) findViewById(R.id.imageView_hangStatus);
        mHangStatusImgView.setImageResource(R.drawable.galge);

        mWordFromCategory = (Button) findViewById(R.id.button_chooseCategory);
        mWordFromCategory.setOnClickListener(this);

        mPlayRoundButton = (Button) findViewById(R.id.button_playGameRound);
        mPlayRoundButton.setOnClickListener(this);

        //Builds Android back button.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        buildTextViews();

        logik.logStatus();
    }

    @SuppressLint("StaticFieldLeak")
    private void carWords() {
        new AsyncTask<Void, Void, Void>() {
            protected void onPreExecute() {
            }

            protected Void doInBackground(Void... unused) {
                try {
                    logik.muligeOrd.clear();
                    logik.muligeOrd.add("Audi");
                    logik.muligeOrd.add("BMW");
                    logik.muligeOrd.add("Subaru");
                    logik.muligeOrd.add("VW");
                    logik.nulstil();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(Void unused) {
                carWordsToast();
                ImgViewCounter = 0;
                mHangStatusImgView.setImageResource(R.drawable.galge);
                buildTextViews();
            }

        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void starwarsWords() {
        new AsyncTask<Void, Void, Void>() {
            protected void onPreExecute() {
            }

            protected Void doInBackground(Void... unused) {
                try {
                    logik.muligeOrd.clear();
                    logik.muligeOrd.add("Anakin");
                    logik.muligeOrd.add("Luke");
                    logik.muligeOrd.add("Lea");
                    logik.muligeOrd.add("R2D2");
                    logik.nulstil();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(Void unused) {
                starwarsWordsToast();
                ImgViewCounter = 0;
                mHangStatusImgView.setImageResource(R.drawable.galge);
                buildTextViews();
            }

        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void harrypotterWords() {
        new AsyncTask<Void, Void, Void>() {
            protected void onPreExecute() {
            }

            protected Void doInBackground(Void... unused) {
                try {
                    logik.muligeOrd.clear();
                    logik.muligeOrd.add("Hagrid");
                    logik.muligeOrd.add("Harry");
                    logik.nulstil();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(Void unused) {
                harrypotterWordsToast();
                ImgViewCounter = 0;
                mHangStatusImgView.setImageResource(R.drawable.galge);
                buildTextViews();
            }

        }.execute();
    }

    private void getDRWordsToast() {
        Toast.makeText(this, "Opdateret ord fra kategorien: DR",
                Toast.LENGTH_SHORT).show();
    }

    private void carWordsToast() {
        Toast.makeText(this, "Opdateret ord fra kategorien: Bilmærker",
                Toast.LENGTH_SHORT).show();
    }

    private void starwarsWordsToast() {
        Toast.makeText(this, "Opdateret ord fra kategorien: Star Wars",
                Toast.LENGTH_SHORT).show();
    }

    private void harrypotterWordsToast() {
        Toast.makeText(this, "Opdateret ord fra kategorien: Harry Potter",
                Toast.LENGTH_SHORT).show();
    }

    private void wordsFromCategoryDialog() {
        String[] categoryList = {"Ord fra DR", "Bilmærker", "Star Wars", "Harry Potter"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Vælg en kategori");
        builder.setItems(categoryList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int intFromList) {

                switch (intFromList) {
                    case 0:
                        getWordsFromDR();
                        break;
                    case 1:
                        carWords();
                        break;
                    case 2:
                        starwarsWords();
                        break;
                    case 3:
                        harrypotterWords();
                        break;
                    default:
                }

            }
        });
        builder.show();
    }

    private void playRound() {
        String bogstav = mEditTextTextBox.getText().toString();

        if (bogstav.length() != 1) {
            mEditTextTextBox.setError("Indtast et énkelt bogstav");
            return;
        }

        logik.gætBogstav(bogstav);
        mEditTextTextBox.setText("");
        mEditTextTextBox.setError(null);


        if (!logik.erSidsteBogstavKorrekt()) {
            wrongGuess();
            Toast.makeText(this, "Sidste bogstav er forkert!",
                    Toast.LENGTH_SHORT).show();
        }

        updateScreen();

        updateImgView();
    }

    private void progressBar(View v) {
        mProgressBar = new ProgressDialog(v.getContext());
        mProgressBar.setCancelable(true);
        mProgressBar.setMessage("Henter ord fra DR...");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.setProgress(0);
        mProgressBar.setMax(100);
        mProgressBar.show();
    }

    private void saveDialog() {
        //Save dialog alert
        final AlertDialog dlgAlert = new AlertDialog.Builder(this).create();
        dlgAlert.setMessage("Highscoren er blevet gemt.");
        dlgAlert.setTitle("");
        dlgAlert.show();

        //Wait 2 seconds, dmismisses dialog and closes activity.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                dlgAlert.dismiss();
                //finish();
            }
        }, 6000);
    }

    private void wrongGuess() {
        //https://stackoverflow.com/a/47758500/8968120
        final float FREQ = 3f;
        final float DECAY = 2f;
        //Interpolator that goes 1 -> -1 -> 1 -> -1 in a sine wave pattern.
        TimeInterpolator decayingSineWave = new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                double raw = Math.sin(FREQ * input * 2 * Math.PI);
                return (float) (raw * Math.exp(-input * DECAY));
            }
        };

        mHangStatusImgView.animate()
                .xBy(-100)
                .setInterpolator(decayingSineWave)
                .setDuration(500)
                .start();
    }

    private void gameOverDialog() {
        //logik.nulstil();

        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra("getOrdet", logik.getOrdet());
        startActivity(intent);
    }

    private void gameWonDialog() {
        //logik.nulstil();

        Intent intent = new Intent(this, GameWonActivity.class);
        intent.putExtra("getAntalForkerteBogstaver", logik.getAntalForkerteBogstaver());
        startActivity(intent);
    }

    private void buildTextViews() {
        //Get amount of letters and print to label.
        amountOfLetters = logik.getSynligtOrd().length();
        mAmountOfLettersTextView.setText("Ordet består af " + amountOfLetters + " bogstaver.");

        //Text about the word and length.
        mInfoTextView.setText("Gæt ordet: " + logik.getSynligtOrd());
    }

    private void updateImgView() {
        logik.getAntalForkerteBogstaver();

        ImgViewCounter = logik.getAntalForkerteBogstaver();

        switch (ImgViewCounter) {
            case 1:
                mHangStatusImgView.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                mHangStatusImgView.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                mHangStatusImgView.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                mHangStatusImgView.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                mHangStatusImgView.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                mHangStatusImgView.setImageResource(R.drawable.forkert6);
                break;
            default:
                mHangStatusImgView.setImageResource(R.drawable.galge);
        }
    }

    //https://gist.github.com/sheharyarn/5f66a854ce43e0c90521
    @SuppressLint("StaticFieldLeak")
    private void getWordsFromDR() {
        new AsyncTask<Void, Void, Void>() {
            protected void onPreExecute() {
            }

            protected Void doInBackground(Void... unused) {
                try {
                    logik.hentOrdFraDr();
                    logik.nulstil();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(Void unused) {
                getDRWordsToast();
                ImgViewCounter = 0;
                mHangStatusImgView.setImageResource(R.drawable.galge);
                buildTextViews();
                //mProgressBar.dismiss();
            }

        }.execute();
    }

    //https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
    private void saveHighScore() {
        scoreFromWrongAttempts = 6 - logik.getAntalForkerteBogstaver();
        playerNameFromPrefsManager = playerNameSharedPreferencesPrefsManager.getString("playerNamePrefsManager", "");
        playerNameFromSharedPrefs = playerNameSharedPreferences.getString("playerNamePref", "");

        sharedPreferencesCounter = getSharedPreferences("sharedPrefCounter", Context.MODE_PRIVATE);
        scoreCounter = sharedPreferencesCounter.getInt("prefCount", 0);
        playerCounter = sharedPreferencesCounter.getInt("prefNameCount", 0);

        if (scoreCounter == 0) {
            scoreCounter = scoreCounter + 1;
        }


        if (playerCounter == 0) {
            playerCounter = playerCounter + 1;
        }

        //log.d(playerCounter);

        editorPlayerName = playerNameSharedPreferences.edit();
        editorPlayerName.putString("playerNamePref" + playerCounter, playerNameFromPrefsManager);
        editorPlayerName.apply();

        editorHighScore = highScoreSharedPreferences.edit();
        editorHighScore.putInt("highScorePref" + scoreCounter, scoreFromWrongAttempts);
        editorHighScore.apply();

        newPlayerCounter = playerCounter + 1;
        newScoreCounter = scoreCounter + 1;

        editorCounter = sharedPreferencesCounter.edit();
        editorPlayerName = sharedPreferencesCounter.edit();

        editorCounter.putInt("prefCount", newScoreCounter);
        editorPlayerName.putInt("prefNameCount", newPlayerCounter);

        editorCounter.apply();
        editorPlayerName.apply();

        saveDialog();
    }

    @Override
    public void onClick(View v) {
        //If statement for opening the appropriate view/take action.
        if (v == mCloseGameIntentButton) {
            finish();
        } else if (v == mEditTextTextBox) {
            mEditTextTextBox.getText().clear();
        } else if (v == mWordFromCategory) {
            wordsFromCategoryDialog();
            //carWords();
        } else if (v == mGetWordFromDRButton) {
            progressBar(v);
            getWordsFromDR();
        } else if (v == mPlayRoundButton) {
            playRound();
        }
    }

    //https://developer.android.com/training/appbar/actions#java
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateScreen() {
        mInfoTextView.setText("Gæt ordet: " + logik.getSynligtOrd());
        mInfoTextView.append("\n\nDu har brugt " + logik.getAntalForkerteBogstaver() + " forsøg.");
        mInfoTextView.append("\n\nFølgende bogstaver er" + " blevet brugt: \n" + logik.getBrugteBogstaver());

        if (logik.erSpilletVundet()) {
            mInfoTextView.append("\nDu har vundet");
            saveHighScore();
            gameWonDialog();
            buildTextViews();
        }

        if (logik.erSpilletTabt()) {
            mInfoTextView.setText("");
            mInfoTextView.setText("\nDu har tabt. Ordet var: " + logik.getOrdet());
            gameOverDialog();
            buildTextViews();
        }

    }


}
