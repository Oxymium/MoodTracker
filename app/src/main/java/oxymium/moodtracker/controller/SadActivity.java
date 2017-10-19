package oxymium.moodtracker.controller;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import oxymium.moodtracker.R;

public class SadActivity extends AppCompatActivity {

    //variables init
    private ConstraintLayout mSadLayout;
    private ImageButton mHistoryButton;
    private MediaPlayer mPlaySadSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sad);

        mSadLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout_sad);

        //loads song & plays it onCreate
        mPlaySadSong = MediaPlayer.create(getApplicationContext(), R.raw.sad_song);
        mPlaySadSong.start();

        mHistoryButton = (ImageButton) findViewById(R.id.mt_history_button);

        //set listener on mHistoryButton
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when clicked, start HistoryActivity
                Intent historyActivity = new Intent(SadActivity.this, HistoryActivity.class);
                startActivity(historyActivity);

            }

        });

        //set listener on screen (mHappyLayout) to touchSwipe (calls OnSwipeTouchListener class)
        mSadLayout.setOnTouchListener(new OnSwipeTouchListener(SadActivity.this) {

            public void onSwipeTop() {
                Intent disappointedActivity = new Intent(SadActivity.this, DisappointedActivity.class);
                startActivity(disappointedActivity);
            }

        });
    }
}
