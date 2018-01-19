package com.example.android.hockeyscoreboard;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText minutes;
    public ImageButton setPlayTime;
    public TextView mTimeElapsed;
    private ImageButton mCountDownStartButton;
    private Button mCountDownResetButton;
    private RadioButton mPeriodOne;
    private RadioButton mPeriodTwo;
    private RadioButton mPeriodThree;
    private RadioButton mPeriodFour;
    private RadioButton mPeriodFive;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = 0;
    private boolean mTimerIsRunning;
    private Button mResetAllButton;

    // Global variables

    int scoreHomeTeam = 0;
    int scoreGuestTeam = 0;
    int periodValue = 0;
    int countHomeFouls = 0;
    int countHomeTimeOut = 0;
    int countGuestFouls = 0;
    int countGuestTimeOut = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minutes = (EditText) findViewById(R.id.edit_time_text);
        setPlayTime = (ImageButton) findViewById(R.id.set_timer_button);
        mTimeElapsed = (TextView) findViewById(R.id.editText);
        mCountDownStartButton = (ImageButton) findViewById(R.id.start_pause_button);
        mCountDownResetButton = (Button) findViewById(R.id.reset_button);
        mPeriodOne = (RadioButton) findViewById(R.id.period_one);
        mPeriodTwo = (RadioButton) findViewById(R.id.period_two);
        mPeriodThree = (RadioButton) findViewById(R.id.period_tree);
        mPeriodFour = (RadioButton) findViewById(R.id.period_four);
        mPeriodFive = (RadioButton) findViewById(R.id.period_five);
        mResetAllButton = (Button) findViewById(R.id.reset_all_button);
        Button mOneScoreHome = (Button) findViewById(R.id.one_score_home);
        mOneScoreHome.setOnClickListener(this);
        Button mTwoScoreHome = (Button) findViewById(R.id.two_score_home);
        mTwoScoreHome.setOnClickListener(this);
        Button mThreeScoreHome = (Button) findViewById(R.id.three_score_home);
        mThreeScoreHome.setOnClickListener(this);
        Button mMinusOneScoreHome = (Button) findViewById(R.id.minus_one_score_home);
        mMinusOneScoreHome.setOnClickListener(this);
        Button mOneScoreGuest = (Button) findViewById(R.id.one_score_guest);
        mOneScoreGuest.setOnClickListener(this);
        Button mTwoScoreGuest = (Button) findViewById(R.id.two_score_guest);
        mTwoScoreGuest.setOnClickListener(this);
        Button mThreeScoreGuest = (Button) findViewById(R.id.three_score_guest);
        mThreeScoreGuest.setOnClickListener(this);
        Button mMinusOneScoreGuest = (Button) findViewById(R.id.minus_one_score_guest);
        mMinusOneScoreGuest.setOnClickListener(this);
        Button mDecrementGuestFouls = (Button) findViewById(R.id.decrement_fouls_guest);
        mDecrementGuestFouls.setOnClickListener(this);
        Button mIncrementGuestFouls = (Button) findViewById(R.id.increment_fouls_guest);
        mIncrementGuestFouls.setOnClickListener(this);
        Button mDecrementGuestTimeOut = (Button) findViewById(R.id.decrement_timeOut_guest);
        mDecrementGuestTimeOut.setOnClickListener(this);
        Button mIncrementGuestTimeOut = (Button) findViewById(R.id.increment_timeOut_guest);
        mIncrementGuestTimeOut.setOnClickListener(this);
        Button mDecrementHomeFouls = (Button) findViewById(R.id.decrement_fouls_home);
        mDecrementHomeFouls.setOnClickListener(this);
        Button mIncrementHomeFouls = (Button) findViewById(R.id.increment_fouls_home);
        mIncrementHomeFouls.setOnClickListener(this);
        Button mDecrementHomeTimeOut = (Button) findViewById(R.id.decrement_timeOut_home);
        mDecrementHomeTimeOut.setOnClickListener(this);
        Button mIncrementHomeTimeOut = (Button) findViewById(R.id.increment_timeOut_home);
        mIncrementHomeTimeOut.setOnClickListener(this);

/**
 * Set Button listener on setPlayTimeButton.
 */

        setPlayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimeLeftInMillis == 0) {
                    String getMinutes = minutes.getText().toString();                 // Get minutes from edittext and convert to string
                    //Check validation over edit text
                    if (!getMinutes.equals("") && getMinutes.length() > 0) {          // Check if time was set before
                        mTimeLeftInMillis = Integer.parseInt(getMinutes) * 60 * 1000; // Convert minutes into milliseconds
                        updateCountDownText();                                        // Update countdown text
                    }
                } else {
                    displayMessage("Please enter no. of Minutes."); // Error message to set minutes
                }
            }
        });
/**
 * Set Button listeners on play periods buttons.
 */

        mPeriodOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (periodValue == 0) {
                    if (mTimeLeftInMillis != 0) {
                        periodValue = 1;
                        displayPeriod(periodValue);
                    } else {
                        mPeriodOne.setChecked(false);
                        displayMessage("Set period time!");
                    }
                } else {
                    displayMessage("Please check next period button");
                }
            }
        });

        mPeriodTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mPeriodOne.isChecked()) {
                    if (mPeriodTwo.isChecked() && periodValue == 1) {
                        periodValue = periodValue + 1;
                        displayPeriod(periodValue);

                    } else {
                        displayMessage("Please check next period button");
                    }

                } else {
                    displayMessage("Please check first period");
                    mPeriodTwo.setChecked(false);
                }
            }
        });

        mPeriodThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPeriodTwo.isChecked()) {
                    if (mPeriodThree.isChecked() && periodValue == 2) {
                        periodValue = periodValue + 1;
                        displayPeriod(periodValue);

                    } else {
                        displayMessage("Please check next period button");
                    }

                } else {
                    displayMessage("Please check second period");
                    mPeriodThree.setChecked(false);
                }
            }
        });
        mPeriodFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPeriodThree.isChecked()) {
                    if (mPeriodFour.isChecked() && periodValue == 3) {
                        periodValue = periodValue + 1;
                        displayPeriod(periodValue);

                    } else {
                        displayMessage("Please check next period button");
                    }
                } else {
                    displayMessage("Please check third period");
                    mPeriodFour.setChecked(false);
                }
            }
        });
        mPeriodFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPeriodFour.isChecked()) {
                    if (mPeriodFive.isChecked() && periodValue == 5) {
                        displayMessage("No more periods to be checked!");
                        return;
                    }
                    periodValue = periodValue + 1;
                    displayPeriod(periodValue);
                } else {
                    displayMessage("Please check fourth period");
                    mPeriodFive.setChecked(false);
                }
            }
        });

        /**
         * Set Start countDownTimer Button listener
         */

        mCountDownStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mTimerIsRunning) { //check countdown timer is running.
                    startTimer(); // Call startTimer method
                } else {
                    pauseTimer(); // or Call pauseTimer method
                }
            }
        });

        /**
         * Set Reset Button listener
         */

        mCountDownResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer(); // Call resetTimer method
            }
        });

        /**
         * Set Reset All Button listener
         */

        mResetAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAll(); // Call reset all method
            }
        });
    }

    /**
     * Set One onClick listener for score, fouls and timeout buttons. Here is used only one listener for few buttons.
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.minus_one_score_home:
                if (scoreHomeTeam >= 1) { // We have to decrement only positive values
                    scoreHomeTeam = scoreHomeTeam - 1; // Decrement scores
                    displayHomeTeamScores(scoreHomeTeam); // Update textView scores
                } else {
                    displayMessage("Team score is zero."); // Display error message
                }
                break;

            case R.id.one_score_home:
                scoreHomeTeam = scoreHomeTeam + 1; // Increment scores with 1
                displayHomeTeamScores(scoreHomeTeam); // Update textView scores
                break;

            case R.id.two_score_home:
                scoreHomeTeam = scoreHomeTeam + 2;
                displayHomeTeamScores(scoreHomeTeam);
                break;

            case R.id.three_score_home:
                scoreHomeTeam = scoreHomeTeam + 3;
                displayHomeTeamScores(scoreHomeTeam);
                break;

            case R.id.minus_one_score_guest:
                if (scoreGuestTeam >= 1) {
                    scoreGuestTeam = scoreGuestTeam - 1;
                    displayGuestTeamScores(scoreGuestTeam);
                } else {
                    displayMessage("Team score is zero.");
                }
                break;

            case R.id.one_score_guest:
                scoreGuestTeam = scoreGuestTeam + 1;
                displayGuestTeamScores(scoreGuestTeam);
                break;

            case R.id.two_score_guest:
                scoreGuestTeam = scoreGuestTeam + 2;
                displayGuestTeamScores(scoreGuestTeam);
                break;

            case R.id.three_score_guest:
                scoreGuestTeam = scoreGuestTeam + 3;
                displayGuestTeamScores(scoreGuestTeam);
                break;

            case R.id.decrement_fouls_home: // Decrement fouls
                if (countHomeFouls >= 1) { // Check if fouls are positive value
                    countHomeFouls = countHomeFouls - 1; // Decrement with 1
                    displayHomeTeamFouls(countHomeFouls); // Update fouls textView
                } else {
                    displayMessage("Error: You cannot have negative fouls counter!"); // Display error message
                }
                break;

            case R.id.increment_fouls_home:
                countHomeFouls = countHomeFouls + 1;
                displayHomeTeamFouls(countHomeFouls);
                break;

            case R.id.decrement_timeOut_home:
                if (countHomeTimeOut >= 1) {
                    countHomeTimeOut = countHomeTimeOut - 1;
                    displayHomeTeamTimeOut(countHomeTimeOut);
                } else {
                    displayMessage("Error: You cannot have negative timeout counter!");
                }
                break;

            case R.id.increment_timeOut_home:
                countHomeTimeOut = countHomeTimeOut + 1;
                displayHomeTeamTimeOut(countHomeTimeOut);
                break;

            case R.id.decrement_fouls_guest:
                if (countGuestFouls >= 1) {
                    countGuestFouls = countGuestFouls - 1;
                    displayGuestTeamFouls(countGuestFouls);
                } else {
                    displayMessage("Error: You cannot have negative fouls counter!");
                }
                break;

            case R.id.increment_fouls_guest:
                countGuestFouls = countGuestFouls + 1;
                displayGuestTeamFouls(countGuestFouls);
                break;

            case R.id.decrement_timeOut_guest:
                if (countGuestTimeOut >= 1) {
                    countGuestTimeOut = countGuestTimeOut - 1;
                    displayGuestTeamTimeOut(countGuestTimeOut);
                } else {
                    displayMessage("Error: You cannot have negative timeout counter!");
                }
                break;

            case R.id.increment_timeOut_guest:
                countGuestTimeOut = countGuestTimeOut + 1;
                displayGuestTeamTimeOut(countGuestTimeOut);
                break;


            default:
                break;
        }
    }

    /**
     * Start timer method
     */

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 10) { // create new object and set refresh interval in milliseconds
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                if (mTimeLeftInMillis <= 100) { // If remaining time left
                    displayMessage("Times UP!"); // Display times Up
                    mTimeLeftInMillis = 0; // Set global variable to zero
                    updateCountDownText(); // Update countdown textView
                }
            }
        }.start(); //start method
        mTimerIsRunning = true; // set boolean variable to true. Timer is running
        mCountDownStartButton.setImageResource(R.drawable.ic_stop_black_24dp); // change button state to stop
    }

    /**
     * Convert timer value from millisecond to MM:SS:MM
     */

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60; // To get minutes from milliseconds
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60; // To get remaining seconds
        int millis = (int) (mTimeLeftInMillis) % 100;        // To get remaining millisecond


        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", minutes, seconds, millis); // Format time to 00:00:00
        mTimeElapsed.setText(timeLeftFormatted); //display formatted string on screen
    }

    /**
     * Create countdown timer pause method
     */

    private void pauseTimer() {
        mCountDownTimer.cancel(); // Stop timer
        mTimerIsRunning = false; // Change time state to false - is not running
        mCountDownStartButton.setImageResource(R.drawable.ic_play_arrow_black_24dp); // display play button
    }

    /**
     * Create reset method for countdown timer
     */

    private void resetTimer() {
        pauseTimer(); // Call pause method
        mTimeLeftInMillis = 0; // Set global variable to zero. Reset timer in milliseconds
        updateCountDownText(); // Update countdown timer

    }

    /**
     * Create method to reset period radio buttons
     */

    public void resetAll() {

        // Change Button state to false and uncheck them all.

        mPeriodOne.setChecked(false);
        mPeriodTwo.setChecked(false);
        mPeriodThree.setChecked(false);
        mPeriodFour.setChecked(false);
        mPeriodFive.setChecked(false);
        periodValue = 0; // set periods global variables to zero
        displayMessage(""); // display empty message and reset error message textView
        displayPeriod(periodValue); // update period textView with start value
        pauseTimer(); // Call pause method
        mTimeLeftInMillis = 0; // Set global variable to zero. Reset timer in milliseconds
        updateCountDownText(); // Update countdown timer

        // Set global variables to zero and display on screen
        scoreHomeTeam = 0;
        scoreGuestTeam = 0;
        countHomeFouls = 0;
        countHomeTimeOut = 0;
        countGuestFouls = 0;
        countGuestTimeOut = 0;
        displayHomeTeamScores(scoreHomeTeam);
        displayGuestTeamScores(scoreGuestTeam);
        displayHomeTeamFouls(countHomeFouls);
        displayGuestTeamFouls(countGuestFouls);
        displayHomeTeamTimeOut(countHomeTimeOut);
        displayGuestTeamTimeOut(countGuestTimeOut);

    }

    public void displayPeriod(int number) {
        TextView periodTextView = (TextView) findViewById(R.id.period_value);
        periodTextView.setText("" + number);
    }

    public void displayRemainigTime(long number) {
        TextView periodTextView = (TextView) findViewById(R.id.editText);
        periodTextView.setText("" + number);
    }

    public void displayHomeTeamScores(int number) {
        TextView homeScoresTextView = (TextView) findViewById(R.id.home_team_scores);
        homeScoresTextView.setText("" + number);
    }

    public void displayGuestTeamScores(int number) {
        TextView guestScoresTextView = (TextView) findViewById(R.id.guest_team_scores);
        guestScoresTextView.setText("" + number);
    }

    public void displayGuestTeamFouls(int number) {
        TextView guestFoulsTextView = (TextView) findViewById(R.id.fouls_count_guest);
        guestFoulsTextView.setText("" + number);
    }

    public void displayGuestTeamTimeOut(int number) {
        TextView guestTimeOutTextView = (TextView) findViewById(R.id.timeOut_count_guest);
        guestTimeOutTextView.setText("" + number);
    }

    public void displayHomeTeamFouls(int number) {
        TextView homeFoulsTextView = (TextView) findViewById(R.id.fouls_count_home);
        homeFoulsTextView.setText("" + number);
    }

    public void displayHomeTeamTimeOut(int number) {
        TextView homeTimeOutTextView = (TextView) findViewById(R.id.timeOut_count_home);
        homeTimeOutTextView.setText("" + number);
    }

    public void displayMessage(String message) {
        TextView messageTextView = (TextView) findViewById(R.id.message_text_view);
        messageTextView.setText(message);
    }
}

