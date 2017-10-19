package oxymium.moodtracker.controller;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import oxymium.moodtracker.R;

public class NormalActivity extends AppCompatActivity {

    //variables init
    private ConstraintLayout mNormalLayout;
    private ImageButton mHistoryButton;
    private MediaPlayer mPlayNormalSong; // MediaPlayer to play normal_song.wave

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_normal);

        mNormalLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout_normal);

        //loads song & plays it onCreate
        mPlayNormalSong = MediaPlayer.create(getApplicationContext(), R.raw.normal_song);
        mPlayNormalSong.start();

        mHistoryButton = (ImageButton) findViewById(R.id.mt_history_button);

        //set listener on mHistoryButton
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when clicked, start HistoryActivity
                Intent historyActivity = new Intent(NormalActivity.this, HistoryActivity.class);
                startActivity(historyActivity);

            }

        });

        //set listener on screen (mHappyLayout) to touchSwipe (calls OnSwipeTouchListener class)
        mNormalLayout.setOnTouchListener(new OnSwipeTouchListener(NormalActivity.this) {

            public void onSwipeBottom() {
                Intent disappointedActivity = new Intent(NormalActivity.this, DisappointedActivity.class);
                startActivity(disappointedActivity);
            }

            public void onSwipeTop() {
                Intent mainActivity = new Intent(NormalActivity.this, MainActivity.class);
                startActivity(mainActivity);
            }

        });
    }
}
