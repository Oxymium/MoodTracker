package oxymium.moodtracker.controller;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import oxymium.moodtracker.R;

public class SuperHappyActivity extends AppCompatActivity {

    //variables init
    private ConstraintLayout mSuperHappyLayout; //layout super happy
    private ImageButton mHistoryButton;
    private MediaPlayer mPlaySuperHappySong; //MediaPlayer music super happy

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_super_happy);

        mSuperHappyLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout_super_happy);

        //loads song & plays it onCreate
        mPlaySuperHappySong = MediaPlayer.create(getApplicationContext(), R.raw.super_happy_song);
        mPlaySuperHappySong.start();

        mHistoryButton = (ImageButton) findViewById(R.id.mt_history_button);

        //set listener on mHistoryButton
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when clicked, start HistoryActivity
                Intent historyActivity = new Intent(SuperHappyActivity.this, HistoryActivity.class);
                startActivity(historyActivity);

            }

            });


        //set listener on screen (mHappyLayout) to touchSwipe (calls OnSwipeTouchListener class)
        mSuperHappyLayout.setOnTouchListener(new OnSwipeTouchListener(SuperHappyActivity.this) {

            public void onSwipeBottom() {
                Intent mainActivity = new Intent(SuperHappyActivity.this, MainActivity.class);
                startActivity(mainActivity);
            }

        });

    }
}