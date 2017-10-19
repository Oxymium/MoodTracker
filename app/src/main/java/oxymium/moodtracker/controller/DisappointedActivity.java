package oxymium.moodtracker.controller;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import oxymium.moodtracker.R;

public class DisappointedActivity extends AppCompatActivity {

    //variables init
    private ConstraintLayout mDisappointedLayout;
    private ImageButton mHistoryButton;
    private MediaPlayer mPlayDisappointedSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_disappointed);

        mDisappointedLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout_disappointed);

        //loads song & plays it onCreate
        mPlayDisappointedSong = MediaPlayer.create(getApplicationContext(), R.raw.disappointed_song);
        mPlayDisappointedSong.start();

        mHistoryButton = (ImageButton) findViewById(R.id.mt_history_button);

        //set listener on mHistoryButton
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when clicked, start HistoryActivity
                Intent historyActivity = new Intent(DisappointedActivity.this, HistoryActivity.class);
                startActivity(historyActivity);

            }

        });

        //set listener on screen (mHappyLayout) to touchSwipe (calls OnSwipeTouchListener class)
        mDisappointedLayout.setOnTouchListener(new OnSwipeTouchListener(DisappointedActivity.this) {

            public void onSwipeBottom() {
                Intent disappointedActivity = new Intent(DisappointedActivity.this, SadActivity.class);
                startActivity(disappointedActivity);
            }

            public void onSwipeTop() {
                Intent NormalActivity = new Intent(DisappointedActivity.this, NormalActivity.class);
                startActivity(NormalActivity);
            }

        });
    }
}