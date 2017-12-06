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

    /* Variables init */

    private FragmentPagerAdapter adapterViewPager;
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
    private static final String PREVIOUS_RECORDED_DAY = "PREVIOUS_RECORDED_DAY";

    /* 7 days on the week history - all variables & keys */

    private static final String CURRENT_MOOD_COLOR = "CURRENT_MOOD_COLOR";
    private String mCurrentMoodComment;
    private static final String CURRENT_MOOD_COMMENT = "CURRENT_MOOD_COMMENT";

    private int mMainYesterdayColor, mMain2DaysAgoColor, mMain3DaysAgoColor, mMain4DaysAgoColor, mMain5DaysAgoColor, mMain6DaysAgoColor, mMain7DaysAgoColor;
    private String mMainYesterdayComment, mMain2DaysAgoComment, mMain3DaysAgoComment, mMain4DaysAgoComment, mMain5DaysAgoComment, mMain6DaysAgoComment, mMain7DaysAgoComment;

    private static final String YESTERDAY_MOOD_COLOR = "YESTERDAY_MOOD_COLOR";
    private static final String TWO_DAYS_AGO_MOOD_COLOR = "TWO_DAYS_AGO_MOOD_COLOR";
    private static final String THREE_DAYS_AGO_MOOD_COLOR = "THREE_DAYS_AGO_MOOD_COLOR";
    private static final String FOUR_DAYS_AGO_MOOD_COLOR = "FOUR_DAYS_AGO_MOOD_COLOR";
    private static final String FIVE_DAYS_AGO_MOOD_COLOR = "FIVE_DAYS_AGO_MOOD_COLOR";
    private static final String SIX_DAYS_AGO_MOOD_COLOR = "SIX_DAYS_AGO_MOOD_COLOR";
    private static final String SEVEN_DAYS_AGO_MOOD_COLOR = "SEVEN_DAYS_AGO_MOOD_COLOR";

    private static final String YESTERDAY_MOOD_COMMENT = "YESTERDAY_MOOD_COMMENT";
    private static final String TWO_DAYS_AGO_MOOD_COMMENT = "TWO_DAYS_AGO_MOOD_COMMENT";
    private static final String THREE_DAYS_AGO_MOOD_COMMENT = "THREE_DAYS_AGO_MOOD_COMMENT";
    private static final String FOUR_DAYS_AGO_MOOD_COMMENT = "FOUR_DAYS_AGO_MOOD_COMMENT";
    private static final String FIVE_DAYS_AGO_MOOD_COMMENT = "FIVE_DAYS_AGO_MOOD_COMMENT";
    private static final String SIX_DAYS_AGO_MOOD_COMMENT = "SIX_DAYS_AGO_MOOD_COMMENT";
    private static final String SEVEN_DAYS_AGO_MOOD_COMMENT = "SEVEN_DAYS_AGO_MOOD_COMMENT";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Identifies both ImageButton by their layout's IDs with the findViewById method */
        mHistoryButton = (ImageButton) findViewById(R.id.mt_history_button);
        mNoteButton = (ImageButton) findViewById(R.id.mt_note_button);

        mPreferences = getPreferences(MODE_PRIVATE);

           /* Date */ mCalendar = Calendar.getInstance();
        mCurrentDay = mCalendar.get(Calendar.DAY_OF_YEAR); // current day
        mPreferences.edit().putInt(CURRENT_DAY, mCurrentDay).apply();
        mDayChecker = getPreferences(MODE_PRIVATE).getInt(PREVIOUS_RECORDED_DAY,  -1);

        /* VP & adapter */
        mVerticalViewPager = (VerticalViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new CustomPagerAdapter(getSupportFragmentManager());
        mVerticalViewPager.setAdapter(adapterViewPager);

        /* SharedPreferences reading */
        String mSmileyState = getPreferences(MODE_PRIVATE).getString(SAVED_SMILEY_STATE, null);


        //if (mCurrentDay2 != mCurrentDay && mLastDay != 0)
        //mCurrentDay2 = mCalendar.get(Calendar.DAY_OF_YEAR);
        //mPreferences.edit().putInt(LAST_DAY_SAVED, mCurrentDay2).apply();


        /* onCreate, set vpPager.setCurrentItem(1) (Happy Smiley) by default
           Check the values stored in the SharedPreferences API when activity is recreated.
           If Null (by default or when new day), set default view 「happy」 */

        if (mSmileyState == null || mCurrentDay != mDayChecker || mDayChecker == -1) {


            // Day 7
            mPreferences.edit().putString(SEVEN_DAYS_AGO_MOOD_COMMENT, getPreferences(MODE_PRIVATE).getString(SIX_DAYS_AGO_MOOD_COMMENT, null)).apply();
            mPreferences.edit().putInt(SEVEN_DAYS_AGO_MOOD_COLOR, getPreferences(MODE_PRIVATE).getInt(SIX_DAYS_AGO_MOOD_COLOR, 0)).apply();

            // Day 6
            mPreferences.edit().putString(SIX_DAYS_AGO_MOOD_COMMENT, getPreferences(MODE_PRIVATE).getString(FIVE_DAYS_AGO_MOOD_COMMENT, null)).apply();
            mPreferences.edit().putInt(SIX_DAYS_AGO_MOOD_COLOR, getPreferences(MODE_PRIVATE).getInt(FIVE_DAYS_AGO_MOOD_COLOR, 0)).apply();

            // Day 5
            mPreferences.edit().putString(FIVE_DAYS_AGO_MOOD_COMMENT, getPreferences(MODE_PRIVATE).getString(FOUR_DAYS_AGO_MOOD_COMMENT, null)).apply();
            mPreferences.edit().putInt(FIVE_DAYS_AGO_MOOD_COLOR, getPreferences(MODE_PRIVATE).getInt(FOUR_DAYS_AGO_MOOD_COLOR, 0)).apply();

            // Day 4
            mPreferences.edit().putString(FOUR_DAYS_AGO_MOOD_COMMENT, getPreferences(MODE_PRIVATE).getString(THREE_DAYS_AGO_MOOD_COMMENT, null)).apply();
            mPreferences.edit().putInt(FOUR_DAYS_AGO_MOOD_COLOR, getPreferences(MODE_PRIVATE).getInt(THREE_DAYS_AGO_MOOD_COLOR, 0)).apply();

            // Day 3
            mPreferences.edit().putString(THREE_DAYS_AGO_MOOD_COMMENT, getPreferences(MODE_PRIVATE).getString(TWO_DAYS_AGO_MOOD_COMMENT, null)).apply();
            mPreferences.edit().putInt(THREE_DAYS_AGO_MOOD_COLOR, getPreferences(MODE_PRIVATE).getInt(TWO_DAYS_AGO_MOOD_COLOR, 0)).apply();

            // Day 2
            mPreferences.edit().putString(TWO_DAYS_AGO_MOOD_COMMENT, getPreferences(MODE_PRIVATE).getString(YESTERDAY_MOOD_COMMENT, null)).apply();
            mPreferences.edit().putInt(TWO_DAYS_AGO_MOOD_COLOR, getPreferences(MODE_PRIVATE).getInt(YESTERDAY_MOOD_COLOR, 0)).apply();

            // Day 1
            mPreferences.edit().putString(YESTERDAY_MOOD_COMMENT, getPreferences(MODE_PRIVATE).getString(CURRENT_MOOD_COMMENT, null)).apply();
            mPreferences.edit().putInt(YESTERDAY_MOOD_COLOR, getPreferences(MODE_PRIVATE).getInt(CURRENT_MOOD_COLOR, 0)).apply();

            mPreferences.edit().putString(CURRENT_MOOD_COMMENT, null).apply();

            mPreferences.edit().putInt(PREVIOUS_RECORDED_DAY, mCurrentDay).apply();

            mMain7DaysAgoComment = getPreferences(MODE_PRIVATE).getString(SEVEN_DAYS_AGO_MOOD_COMMENT, null);
            mMain6DaysAgoComment = getPreferences(MODE_PRIVATE).getString(SIX_DAYS_AGO_MOOD_COMMENT, null);
            mMain5DaysAgoComment = getPreferences(MODE_PRIVATE).getString(FIVE_DAYS_AGO_MOOD_COMMENT, null);
            mMain4DaysAgoComment = getPreferences(MODE_PRIVATE).getString(FOUR_DAYS_AGO_MOOD_COMMENT, null);
            mMain3DaysAgoComment = getPreferences(MODE_PRIVATE).getString(THREE_DAYS_AGO_MOOD_COMMENT, null);
            mMain2DaysAgoComment = getPreferences(MODE_PRIVATE).getString(TWO_DAYS_AGO_MOOD_COMMENT, null);
            mMainYesterdayComment = getPreferences(MODE_PRIVATE).getString(YESTERDAY_MOOD_COMMENT, null);

            mMain7DaysAgoColor = getPreferences(MODE_PRIVATE).getInt(SEVEN_DAYS_AGO_MOOD_COLOR, 0);
            mMain6DaysAgoColor = getPreferences(MODE_PRIVATE).getInt(SIX_DAYS_AGO_MOOD_COLOR, 0);
            mMain5DaysAgoColor = getPreferences(MODE_PRIVATE).getInt(FIVE_DAYS_AGO_MOOD_COLOR, 0);
            mMain4DaysAgoColor = getPreferences(MODE_PRIVATE).getInt(FOUR_DAYS_AGO_MOOD_COLOR, 0);
            mMain3DaysAgoColor = getPreferences(MODE_PRIVATE).getInt(THREE_DAYS_AGO_MOOD_COLOR, 0);
            mMain2DaysAgoColor = getPreferences(MODE_PRIVATE).getInt(TWO_DAYS_AGO_MOOD_COLOR, 0);
            mMainYesterdayColor = getPreferences(MODE_PRIVATE).getInt(YESTERDAY_MOOD_COLOR, 0);

            mPreferences.edit().putString(SAVED_SMILEY_STATE, "HAPPY_STATE").apply();
            mPreferences.edit().putInt(CURRENT_MOOD_COLOR, R.color.light_sage).apply();

            mVerticalViewPager.setCurrentItem(3);
            mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.happy_song);

    }


         else{  /* Cases corresponding to non-default states 「sad」= 0, 「disappointed」 = 1, 「normal」 = 2, 「happy」 = 3, 「super_happy」 = 4 */

            switch (mSmileyState) {

                case "SAD_STATE":
                    mVerticalViewPager.setCurrentItem(0);
                    /* This is required in case the app is relaunched and state is not changed (or else, it will reset to null) */
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "SAD_STATE").apply();
                    mPreferences.edit().putInt(CURRENT_MOOD_COLOR, R.color.faded_red).apply();

                    break;

                case "DISAPPOINTED_STATE":
                    mVerticalViewPager.setCurrentItem(1);
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "DISAPPOINTED_STATE").apply();
                    mPreferences.edit().putInt(CURRENT_MOOD_COLOR, R.color.warm_grey).apply();

                    break;

                case "NORMAL_STATE":
                    mVerticalViewPager.setCurrentItem(2);
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "NORMAL_STATE").apply();
                    mPreferences.edit().putInt(CURRENT_MOOD_COLOR, R.color.cornflower_blue_65).apply();

                    break;

                case "HAPPY_STATE":
                    mVerticalViewPager.setCurrentItem(3);
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "HAPPY_STATE").apply();
                    mPreferences.edit().putInt(CURRENT_MOOD_COLOR, R.color.light_sage).apply();

                    break;

                case "SUPER_HAPPY_STATE":
                    mVerticalViewPager.setCurrentItem(4);
                    mPreferences.edit().putString(SAVED_SMILEY_STATE, "SUPER_HAPPY_STATE").apply();
                    mPreferences.edit().putInt(CURRENT_MOOD_COLOR, R.color.banana_yellow).apply();

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
                        mPreferences.edit().putInt(CURRENT_MOOD_COLOR, R.color.faded_red).apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.sad_song);
                        mPlaySong.start();

                        break;

                    case 1:

                        mPreferences.edit().putString(SAVED_SMILEY_STATE, "DISAPPOINTED_STATE").apply();
                        mPreferences.edit().putInt(CURRENT_MOOD_COLOR, R.color.warm_grey).apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.disappointed_song);
                        mPlaySong.start();

                        break;

                    case 2:

                        mPreferences.edit().putString(SAVED_SMILEY_STATE, "NORMAL_STATE").apply();
                        mPreferences.edit().putInt(CURRENT_MOOD_COLOR, R.color.cornflower_blue_65).apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.normal_song);
                        mPlaySong.start();

                        break;

                    case 3:

                        mPreferences.edit().putString(SAVED_SMILEY_STATE, "HAPPY_STATE").apply();
                        mPreferences.edit().putInt(CURRENT_MOOD_COLOR, R.color.light_sage).apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.happy_song);
                        mPlaySong.start();

                        break;

                    case 4:

                        mPreferences.edit().putString(SAVED_SMILEY_STATE, "SUPER_HAPPY_STATE").apply();
                        mPreferences.edit().putInt(CURRENT_MOOD_COLOR, R.color.banana_yellow).apply();

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

                historyActivity.putExtra("YESTERDAY_COLOR", mMainYesterdayColor);
                historyActivity.putExtra("YESTERDAY_COMMENT", mMainYesterdayComment);

                historyActivity.putExtra("TWO_DAYS_AGO_COLOR", mMain2DaysAgoColor);
                historyActivity.putExtra("TWO_DAYS_AGO_COMMENT", mMain2DaysAgoComment);

                historyActivity.putExtra("THREE_DAYS_AGO_COLOR", mMain3DaysAgoColor);
                historyActivity.putExtra("THREE_DAYS_AGO_COMMENT", mMain3DaysAgoComment);

                historyActivity.putExtra("FOUR_DAYS_AGO_COLOR", mMain4DaysAgoColor);
                historyActivity.putExtra("FOUR_DAYS_AGO_COMMENT", mMain4DaysAgoComment);

                historyActivity.putExtra("FIVE_DAYS_AGO_COLOR", mMain5DaysAgoColor);
                historyActivity.putExtra("FIVE_DAYS_AGO_COMMENT", mMain5DaysAgoComment);

                historyActivity.putExtra("SIX_DAYS_AGO_COLOR", mMain6DaysAgoColor);
                historyActivity.putExtra("SIX_DAYS_AGO_COMMENT", mMain6DaysAgoComment);

                historyActivity.putExtra("SEVEN_DAYS_AGO_COLOR", mMain7DaysAgoColor);
                historyActivity.putExtra("SEVEN_DAYS_AGO_COMMENT", mMain7DaysAgoComment);



                startActivity(historyActivity);

            }

        });

        mNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* When mHistoryButton is clicked on, should display an AlertDialog box */

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
                        mPreferences.edit().putString(CURRENT_MOOD_COMMENT, mCurrentMoodComment).apply();

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

        Log.d(TAG, "Value: " + Float.toString(mCurrentDay)); // Helper
        Log.d(TAG, "Value: " + Float.toString(mDayChecker));



    }

}





