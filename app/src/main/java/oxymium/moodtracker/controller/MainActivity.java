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

    private int mCurrentDay; /* Current day of the year */
    public static final String CURRENT_DAY = "CURRENT_DAY";

    private int mDayChecker;

    private int mPreviousRecordedDay; // Last saved day
    public static final String PREVIOUS_RECORDED_DAY = "PREVIOUS_RECORDED_DAY";

    private String mCurrentMoodComment = ""; /* Get text from currentMoodText */
    private int mCurrentMoodColor;
    private static final String SAVED_COMMENT_MOOD = "SAVED_COMMENT_MOOD";

    /* 7 days on the week - all variables */

    private int YESTERDAY_MOOD_COLOR, TWO_DAYS_AGO_MOOD_COLOR, THREE_DAYS_AGO_MOOD_COLOR, FOUR_DAYS_AGO_MOOD_COLOR, FIVE_DAYS_AGO_MOOD_COLOR, SIX_DAYS_AGO_MOOD_COLOR, SEVEN_DAYS_AGO_MOOD_COLOR;
    private String YESTERDAY_MOOD_COMMENT, TWO_DAYS_AGO_MOOD_COMMENT, THREE_DAYS_AGO_MOOD_COMMENT, FOUR_DAYS_AGO_MOOD_COMMENT, FIVE_DAYS_AGO_MOOD_COMMENT, SIX_DAYS_AGO_MOOD_COMMENT, SEVEN_DAYS_AGO_MOOD_COMMENT;





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

        /* Date */ mCalendar = Calendar.getInstance();
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

            if (mCurrentDay != mDayChecker || mDayChecker == -1) {

                YESTERDAY_MOOD_COLOR = mCurrentMoodColor;
                YESTERDAY_MOOD_COMMENT = mCurrentMoodComment;
            }


        } else {  /* Cases corresponding to non-default states 「sad」= 0, 「disappointed」 = 1, 「normal」 = 2, 「happy」 = 3, 「super_happy」 = 4 */

            switch (mSmileyState) {

                case "SAD_STATE":
                    mVerticalViewPager.setCurrentItem(0);
                    /* This is required in case the app is relaunched and state is not changed (or else, it will reset to null) */
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "SAD_STATE").apply();
                    mCurrentMoodColor = R.color.faded_red;

                    break;

                case "DISAPPOINTED_STATE":
                    mVerticalViewPager.setCurrentItem(1);
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "DISAPPOINTED_STATE").apply();
                    mCurrentMoodColor = R.color.warm_grey;

                    break;

                case "NORMAL_STATE":
                    mVerticalViewPager.setCurrentItem(2);
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "NORMAL_STATE").apply();
                    mCurrentMoodColor = R.color.cornflower_blue_65;

                    break;

                case "HAPPY_STATE":
                    mVerticalViewPager.setCurrentItem(3);
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "HAPPY_STATE").apply();
                    mCurrentMoodColor = R.color.light_sage;



                    break;

                case "SUPER_HAPPY_STATE":
                    mVerticalViewPager.setCurrentItem(4);
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "SUPER_HAPPY_STATE").apply();
                    mCurrentMoodColor = R.color.banana_yellow;

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

                        mCurrentMoodColor = R.color.faded_red;

                        break;

                    case 1:

                        mPreferences.edit().putString(SAVED_SMILEY_STATE, "DISAPPOINTED_STATE").apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.disappointed_song);
                        mPlaySong.start();

                        mCurrentMoodColor = R.color.warm_grey;

                        break;

                    case 2:

                        mPreferences.edit().putString(SAVED_SMILEY_STATE, "NORMAL_STATE").apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.normal_song);
                        mPlaySong.start();

                        mCurrentMoodColor = R.color.cornflower_blue_65;

                        break;

                    case 3:

                        mPreferences.edit().putString(SAVED_SMILEY_STATE, "HAPPY_STATE").apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.happy_song);
                        mPlaySong.start();

                        mCurrentMoodColor = R.color.light_sage;

                        break;

                    case 4:

                        mPreferences.edit().putString(SAVED_SMILEY_STATE, "SUPER_HAPPY_STATE").apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.super_happy_song);
                        mPlaySong.start();

                        mCurrentMoodColor = R.color.banana_yellow;

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

                historyActivity.putExtra("YESTERDAY_COLOR", YESTERDAY_MOOD_COLOR);
                historyActivity.putExtra("YESTERDAY_COMMENT", YESTERDAY_MOOD_COMMENT);

                historyActivity.putExtra("TWO_DAY_SAGO_COLOR", TWO_DAYS_AGO_MOOD_COLOR);
                historyActivity.putExtra("TWO_DAYS_AGO_COMMENT", TWO_DAYS_AGO_MOOD_COMMENT);

                historyActivity.putExtra("THREE_DAYS_AGO_COLOR", THREE_DAYS_AGO_MOOD_COLOR);
                historyActivity.putExtra("THREE_DAYS_AGO_COMMENT", THREE_DAYS_AGO_MOOD_COMMENT);

                historyActivity.putExtra("FOUR_DAYS_AGO_COLOR", FOUR_DAYS_AGO_MOOD_COLOR);
                historyActivity.putExtra("FOUR_DAYS_AGO_COMMENT", FOUR_DAYS_AGO_MOOD_COMMENT);

                historyActivity.putExtra("FIVE_DAYS_AGO_COLOR", FIVE_DAYS_AGO_MOOD_COLOR);
                historyActivity.putExtra("FIVE_DAYS_AGO_COMMENT", FIVE_DAYS_AGO_MOOD_COMMENT);

                historyActivity.putExtra("SIX_DAYS_AGO_COLOR", SIX_DAYS_AGO_MOOD_COLOR);
                historyActivity.putExtra("SIX_DAYS_AGO_COMMENT", SIX_DAYS_AGO_MOOD_COMMENT);

                historyActivity.putExtra("SEVEN_DAYS_AGO_COLOR", SEVEN_DAYS_AGO_MOOD_COLOR);
                historyActivity.putExtra("SEVEN_DAYS_AGO_COMMENT", SEVEN_DAYS_AGO_MOOD_COMMENT);


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
                        mCurrentMoodComment = input.getText().toString();
                        mPreferences.edit().putString(SAVED_COMMENT_MOOD, mCurrentMoodComment).apply();

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





