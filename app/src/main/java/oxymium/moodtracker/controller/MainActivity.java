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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import org.joda.time.DateTime;

import java.util.Calendar;

import oxymium.moodtracker.R;

public class MainActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;

    /* Variables init */

    private ConstraintLayout mMainLayout; /* Main layout frame */
    private ImageButton mNoteButton; /* Bottom-left image button (expected to call a Text box to input Mood) */
    private ImageButton mHistoryButton; /* Bottom-right image button (expected to call the HistoryActivity activity) */
    private MediaPlayer mPlaySong; /* MediaPlayer to play songs from the raw folder */
    private VerticalViewPager mVerticalViewPager;
    public SharedPreferences mPreferences; /* API to save data (smiley state, text input etc) */

    private static final String TAG = "MyActivity"; // LOG TEST

    private static final String SAVED_SMILEY_STATE = "SAVED_SMILEY_STATE"; /* Key used to read & save Smiley state */


    /* Day stored variables */
    private Calendar mCalendar;

    private int mCurrentDay; // Daily day
    public static final String CURRENT_DAY = "CURRENT_DAY";

    private int mDayChecker;

    private int mPreviousRecordedDay; // Last saved day
    public static final String PREVIOUS_RECORDED_DAY = "PREVIOUS_RECORDED_DAY";



    private String mCurrentMoodText = ""; /* Get text from currentMoodText */
    private int MOOD_COLOR;
    private String SAVED_TEXT_MOOD = "";
    /* 7 days on the week - all variables */

    private int mYesterday, m2DaysAgo, m3DaysAgo, m4DaysAgo, m5DaysAgo, m6DaysAgo, m7DaysAgo;

    private String YESTERDAY_SAVED_TEXT_MOOD, TWO_DAYSAGO_TEXT_MOOD, THREE_DAYSAGO_TEXT_MOOD, FOUR_DAYSAGO_TEXT_MOOD, FIVE_DAYSAGO_TEXT_MOOD, SIX_DAYSAGO_TEXT_MOOD, SEVEN_DAYSAGO_TEXT_MOOD;
    private String YESTERDAY_MOOD, TWO_DAYSAGO_MOOD, THREE_DAYSAGO_MOOD, FOUR_DAYSAGO_MOOD, FIVE_DAYSAGO_MOOD, SIX_DAYSAGO_MOOD, SEVEN_DAYSAGO_MOOD;



    /* Arrays to stock everything (test for later) */

    /*
    int daysOfTheWeekSavedKey[] = {SAVED_DAY_STATE_1, SAVED_DAY_STATE_2, SAVED_DAY_STATE_3, SAVED_DAY_STATE_4, SAVED_DAY_STATE_5, SAVED_DAY_STATE_6, SAVED_DAY_STATE_7};
    String textMoodSaved[] = {SAVED_MOOD_TEXT_1, SAVED_MOOD_TEXT_2, SAVED_MOOD_TEXT_3, SAVED_MOOD_TEXT_4, SAVED_MOOD_TEXT_5, SAVED_MOOD_TEXT_6, SAVED_MOOD_TEXT_7};
    String smileyStateColor[] = {SAVED_SMILEY_STATE_1,SAVED_SMILEY_STATE_2, SAVED_SMILEY_STATE_3, SAVED_SMILEY_STATE_4, SAVED_SMILEY_STATE_5, SAVED_SMILEY_STATE_6, SAVED_SMILEY_STATE_7}; */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Identifies both ImageButton by their layout's IDs with the findViewById method */
        mHistoryButton = (ImageButton) findViewById(R.id.mt_history_button);
        mNoteButton = (ImageButton) findViewById(R.id.mt_note_button);

        mPreferences = getPreferences(MODE_PRIVATE);

        /* VP & adapter */
        mVerticalViewPager = (VerticalViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new CustomPagerAdapter(getSupportFragmentManager());
        mVerticalViewPager.setAdapter(adapterViewPager);

        /* SharedPreferences reading */
        String mSmileyState = getPreferences(MODE_PRIVATE).getString(SAVED_SMILEY_STATE, null);

        /* Date */

        mCalendar = Calendar.getInstance();
        mCurrentDay = mCalendar.get(Calendar.DAY_OF_YEAR); // current day

        mDayChecker = getPreferences(MODE_PRIVATE).getInt(PREVIOUS_RECORDED_DAY,  -1);



        Log.d(TAG, "Value: " + Float.toString(mCurrentDay)); // Helper
        Log.d(TAG, "Value: " + Float.toString(mDayChecker));





        //if (mCurrentDay2 != mCurrentDay && mLastDay != 0)
        //mCurrentDay2 = mCalendar.get(Calendar.DAY_OF_YEAR);
        //mPreferences.edit().putInt(LAST_DAY_SAVED, mCurrentDay2).apply();


        /* onCreate, set vpPager.setCurrentItem(1) (Happy Smiley) by default
           Check the values stored in the SharedPreferences API when activity is recreated.
           If Null (by default or when new day), set default view 「happy」 */

        if (mSmileyState == null || mCurrentDay != mDayChecker || mDayChecker == -1) {
            mPreferences.edit().putInt(PREVIOUS_RECORDED_DAY, mCurrentDay).apply();
            mVerticalViewPager.setCurrentItem(3);
            mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.happy_song);

        } else {  /* Cases corresponding to non-default states 「sad」= 0, 「disappointed」 = 1, 「normal」 = 2, 「happy」 = 3, 「super_happy」 = 4 */

            switch (mSmileyState) {

                case "SAD_STATE":
                    mVerticalViewPager.setCurrentItem(0);
                    /* This is required in case the app is relaunched and state is not changed (or else, it will reset to null) */
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "SAD_STATE").apply();

                    break;

                case "DISAPPOINTED_STATE":
                    mVerticalViewPager.setCurrentItem(1);
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "DISAPPOINTED_STATE").apply();


                    break;

                case "NORMAL_STATE":
                    mVerticalViewPager.setCurrentItem(2);
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "NORMAL_STATE").apply();

                    break;

                case "HAPPY_STATE":
                    mVerticalViewPager.setCurrentItem(3);
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "HAPPY_STATE").apply();



                    break;

                case "SUPER_HAPPY_STATE":
                    mVerticalViewPager.setCurrentItem(4);
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "SUPER_HAPPY_STATE").apply();

                    break;

                default:
            }

        }


        /* Add onPageChangeListener to the VerticalPager */

        mVerticalViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        /* Will convert the saved mood into int for color */
        switch (SAVED_SMILEY_STATE) {
            case "SAD_STATE":

                MOOD_COLOR = R.color.faded_red;
                break;

            case "DISAPPOINTED_STATE":

                MOOD_COLOR = R.color.warm_grey;

                break;

            case "NORMAL_STATE":

                MOOD_COLOR = R.color.cornflower_blue_65;

                break;

            case "HAPPY_STATE":

                MOOD_COLOR = R.color.light_sage;

                break;

            case "SUPER_HAPPY_STATE":

                MOOD_COLOR = R.color.banana_yellow;

                break;

            default:

        }

        ///////////////////////////////////////////////////
        Log.d(TAG, "Test state : " + MOOD_COLOR);



        /* Add onClickListener to mHistoryButton */
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* When mHistoryButton is clicked on, starts HistoryActivity */
                Intent historyActivity = new Intent(MainActivity.this, HistoryActivity.class);
                /* Intent passes key value to the historyActivity */
                historyActivity.putExtra("Yesterday", mYesterday);
                historyActivity.putExtra("TwoDaysAgo", m2DaysAgo);
                historyActivity.putExtra("ThreeDaysAgo", m3DaysAgo);
                historyActivity.putExtra("FourDaysAgo", m4DaysAgo);
                historyActivity.putExtra("FiveDaysAgo", m5DaysAgo);
                historyActivity.putExtra("SixDaysAgo", m6DaysAgo);
                historyActivity.putExtra("SevenDaysAgo", m7DaysAgo);
                // historyActivity.putExtra("MoodTextTest", mCurrentMoodText);
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
                        mPreferences.edit().putString(SAVED_TEXT_MOOD, mCurrentMoodText).apply();

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





