package oxymium.moodtracker.controller;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;

import oxymium.moodtracker.R;



public class MainActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;

    /* Variables init */

    private ConstraintLayout mMainLayout; /* Main layout frame */

    private ImageButton mNoteButton; /* Bottom-left image button (expected to call a Text box to input Mood) */
    private ImageButton mHistoryButton; /* Bottom-right image button (expected to call the History_Activity) */

    private MediaPlayer mPlaySong; /* MediaPlayer to play songs from the raw folder */

    private SharedPreferences mPreferences; /* API to save data (smiley state, text input etc) */

    private int mCurrentDate; /* Gets date of the current day (int value) */


    private String SAVED_SMILEY_STATE = ""; /* Key used to read & save Smiley state */
    private String SAVED_DAY_VALUE_ON_CREATE = ""; /* Key used to remember the day onCreate */

    private String mCurrentMoodText = ""; /* Get text from currentMoodText */

    /* Arrays to stock everything (test for later) */
    /* First array is constant */
    private static final String daysOfTheWeek[] = {"Yesterday", "Two days ago", "Three days ago", "Four days ago", "Five days ago", "Six days ago", "A week ago"};

    /*
    int daysOfTheWeekSavedKey[] = {SAVED_DAY_STATE_1, SAVED_DAY_STATE_2, SAVED_DAY_STATE_3, SAVED_DAY_STATE_4, SAVED_DAY_STATE_5, SAVED_DAY_STATE_6, SAVED_DAY_STATE_7};
    String textMoodSaved[] = {SAVED_MOOD_TEXT_1, SAVED_MOOD_TEXT_2, SAVED_MOOD_TEXT_3, SAVED_MOOD_TEXT_4, SAVED_MOOD_TEXT_5, SAVED_MOOD_TEXT_6, SAVED_MOOD_TEXT_7};
    String smileyStateColor[] = {SAVED_SMILEY_STATE_1,SAVED_SMILEY_STATE_2, SAVED_SMILEY_STATE_3, SAVED_SMILEY_STATE_4, SAVED_SMILEY_STATE_5, SAVED_SMILEY_STATE_6, SAVED_SMILEY_STATE_7}; */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Identifies both ImageButton by their layout's IDs with the findViewById method */
        mHistoryButton = (ImageButton) findViewById(R.id.mt_history_button);
        mNoteButton = (ImageButton) findViewById(R.id.mt_note_button);

        mPreferences = getPreferences(MODE_PRIVATE);

        /* Calendar object to get the DAY_OF_YEAR (int number) */
        Calendar calendar = Calendar.getInstance();
        mCurrentDate = calendar.get(Calendar.DAY_OF_YEAR);

        VerticalViewPager vpPager = (VerticalViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new CustomPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        /* SharedPreferences reading */
        String mSmileyState = getPreferences(MODE_PRIVATE).getString(SAVED_SMILEY_STATE, null);

        /* onCreate, stores the current day number (DAY_OF_YEAR) into the SharedPreferences */
        // mPreferences.edit().putInt(SAVED_DAY_VALUE_ON_CREATE, mTodayDate).apply();




        /* onCreate, set vpPager.setCurrentItem(1) (Happy Smiley) by default
           Check the values stored in the SharedPreferences API when activity is recreated.
           If Null (by default or when reset at midnight), set default view 「happy」 */

        if (mSmileyState == null) {
            vpPager.setCurrentItem(3);
            mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.happy_song);
            /* Save day into the Sharedpreferences if null (first time launch) */

        } else {  /* Cases corresponding to non-default states 「sad」= 0, 「disappointed」 = 1, 「normal」 = 2, 「happy」 = 3, 「super_happy」 = 4 */

            switch (mSmileyState) {

                case "SAD_STATE":
                    vpPager.setCurrentItem(0);
                    /* This is required in case the app is relaunched and state is not changed (or else, it will reset to null) */
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "SAD_STATE").apply();

                    break;

                case "DISAPPOINTED_STATE":
                    vpPager.setCurrentItem(1);
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "DISAPPOINTED_STATE").apply();

                    break;

                case "NORMAL_STATE":
                    vpPager.setCurrentItem(2);
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "NORMAL_STATE").apply();

                    break;

                case "HAPPY_STATE":
                    vpPager.setCurrentItem(3);
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "HAPPY_STATE").apply();

                    break;

                case "SUPER_HAPPY_STATE":
                    vpPager.setCurrentItem(4);
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "SUPER_HAPPY_STATE").apply();

                    break;

                default:
            }

        }


        /* Add onPageChangeListener to the VerticalPager */

        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position)

                {

                    case 0:

                        mPreferences.edit().putString(SAVED_SMILEY_STATE, "SAD_STATE").apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.sad_song);
                        mPlaySong.start();

                        break;

                    case 1:

                        mPreferences.edit().putString(SAVED_SMILEY_STATE, "DISAPPOINTED_STATE").apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.disappointed_song);
                        mPlaySong.start();

                        break;

                    case 2:

                        mPreferences.edit().putString(SAVED_SMILEY_STATE, "NORMAL_STATE").apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.normal_song);
                        mPlaySong.start();

                        break;

                    case 3:

                        mPreferences.edit().putString(SAVED_SMILEY_STATE, "HAPPY_STATE").apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.happy_song);
                        mPlaySong.start();

                        break;

                    case 4:

                        mPreferences.edit().putString(SAVED_SMILEY_STATE, "SUPER_HAPPY_STATE").apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.super_happy_song);
                        mPlaySong.start();

                        break;

                    default:

                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /* Add onClickListener to mHistoryButton */
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* When mHistoryButton is clicked on, starts HistoryActivity */
                Intent historyActivity = new Intent(MainActivity.this, HistoryActivity.class);
                /* Intent passes key value to the historyActivity */
                historyActivity.putExtra("CurrentDayTest",  mCurrentDate);
                historyActivity.putExtra("DaysOfTheWeekArray", daysOfTheWeek);
                startActivity(historyActivity);

            }

        });

        mNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* When mHistoryButton is clicked on, starts HistoryActivity */

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Describe your current mood!");

                /* Set up the input */
                final EditText input = new EditText(MainActivity.this);
                /* Specify the type of input expected; caps for new sentences */
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
                builder.setView(input);

                /* "Ok" and "Cancel" button */
                builder.setPositiveButton("Save Mood", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCurrentMoodText = input.getText().toString();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();


            }

        });





    }



}
