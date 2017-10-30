package oxymium.moodtracker.controller;


import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.ImageButton;



import oxymium.moodtracker.R;



public class MainActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;

    //variables init
    private ConstraintLayout mMainLayout; //Main layout frame

    private ImageButton mNoteButton; // Bottom left button (note)
    private ImageButton mHistoryButton; // Bottom right button (history)

    private MediaPlayer mPlaySong; // MediaPlayer to play happy_song.wave

    private SharedPreferences mPreferences;
    private String SAVE_SMILEY_STATE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout);
        mHistoryButton = (ImageButton) findViewById(R.id.mt_history_button);
        mNoteButton = (ImageButton) findViewById(R.id.mt_note_button);

        mPreferences = getPreferences(MODE_PRIVATE);

        VerticalViewPager vpPager = (VerticalViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new CustomPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        // onCreate, set vpPager.setCurrentItem(1) (Happy Smiley) by default

        String savedState = getPreferences(MODE_PRIVATE).getString(SAVE_SMILEY_STATE, null);

        // Checks the value stored in the SharedPreferences API when activity is recreated.
        // If Null (by default or when reset at midnight), set default view (happy smiley)

        //mPreferences.edit().remove(SAVE_SMILEY_STATE).apply();


        /* Checks the value stored in the SharedPreferences API when activity is recreated.
           If Null (by default or when reset at midnight), set default view 「happy」 */

        if (savedState == null) {
            vpPager.setCurrentItem(3);
            mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.happy_song); }

        else {  /* Cases corresponding to non-default states 「sad」= 0, 「disappointed」 = 1, 「normal」 = 2, 「happy」 = 3, 「super_happy」 = 4 */

            switch (savedState) {

                case "SAD_STATE" :
                    vpPager.setCurrentItem(0);
                    /* This is required in case the app is relaunched and state is not changed (or else, it will reset to null */
                    mPreferences.edit().putString(SAVE_SMILEY_STATE, "SAD_STATE").apply();


                    break;

                case "DISAPPOINTED_STATE" :
                    vpPager.setCurrentItem(1);
                    mPreferences.edit().putString(SAVE_SMILEY_STATE, "DISAPPOINTED_STATE").apply();

                    break;

                case "NORMAL_STATE" :
                    vpPager.setCurrentItem(2);
                    mPreferences.edit().putString(SAVE_SMILEY_STATE, "NORMAL_STATE").apply();

                    break;

                case "HAPPY_STATE" :
                    vpPager.setCurrentItem(3);
                    mPreferences.edit().putString(SAVE_SMILEY_STATE, "HAPPY_STATE").apply();

                    break;

                case "SUPER_HAPPY_STATE" :
                    vpPager.setCurrentItem(4);
                    mPreferences.edit().putString(SAVE_SMILEY_STATE, "SUPER_HAPPY_STATE").apply();

                    break;

                default:
            }

        }


        // Other cases corresponding to non-default states 「sad」= 0, 「disappointed」 = 1, 「normal」 = 2, 「super_happy」 =


        // TEST

        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position)

                {

                    case 0:

                        mPreferences.edit().putString(SAVE_SMILEY_STATE, "SAD_STATE").apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.sad_song);
                        mPlaySong.start();

                        break;

                    case 1:

                        mPreferences.edit().putString(SAVE_SMILEY_STATE, "DISAPPOINTED_STATE").apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.disappointed_song);
                        mPlaySong.start();

                        break;

                    case 2:

                        mPreferences.edit().putString(SAVE_SMILEY_STATE, "NORMAL_STATE").apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.normal_song);
                        mPlaySong.start();

                        break;

                    case 3:

                        mPreferences.edit().putString(SAVE_SMILEY_STATE, "HAPPY_STATE").apply();

                        mPlaySong = MediaPlayer.create(getApplicationContext(), R.raw.happy_song);
                        mPlaySong.start();

                        break;

                    case 4:

                        mPreferences.edit().putString(SAVE_SMILEY_STATE, "SUPER_HAPPY_STATE").apply();

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

        // Attach onClickListener to mHistoryButton
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When clicked, start HistoryActivity
                Intent historyActivity = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivity);

            }

        });


    }



}
