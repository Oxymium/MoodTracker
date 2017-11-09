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
    private String SAVED_SMILEY_STATE = ""; /* Key used to save Smiley state */
    private String SAVED_DAY_STATE = "";

    private int mThisDay; /* Check day of the year (int) */

    private String mCurrentMoodText = ""; /* Get text from currentText */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Identify both ImageButton by their layout's IDs with the findViewById method */
        mHistoryButton = (ImageButton) findViewById(R.id.mt_history_button);
        mNoteButton = (ImageButton) findViewById(R.id.mt_note_button);

        mPreferences = getPreferences(MODE_PRIVATE);

        /* Calendar to check day */
        Calendar calendar = Calendar.getInstance();
        mThisDay = calendar.get(Calendar.DAY_OF_YEAR);

        VerticalViewPager vpPager = (VerticalViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new CustomPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        String savedState = getPreferences(MODE_PRIVATE).getString(SAVED_SMILEY_STATE, null);

        /* onCreate, set vpPager.setCurrentItem(1) (Happy Smiley) by default */

        /* Check the value stored in the SharedPreferences API when activity is recreated.
           If Null (by default or when reset at midnight), set default view 「happy」 */


        if (savedState == null) {
            vpPager.setCurrentItem(3);
            mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.happy_song);
        } else {  /* Cases corresponding to non-default states 「sad」= 0, 「disappointed」 = 1, 「normal」 = 2, 「happy」 = 3, 「super_happy」 = 4 */

            switch (savedState) {

                case "SAD_STATE":
                    vpPager.setCurrentItem(0);
                    /* This is required in case the app is relaunched and state is not changed (or else, it will reset to null */
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
