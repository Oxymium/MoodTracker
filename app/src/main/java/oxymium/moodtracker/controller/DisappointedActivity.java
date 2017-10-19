package oxymium.moodtracker.controller;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import oxymium.moodtracker.R;

public class DisappointedActivity extends AppCompatActivity {

    private ConstraintLayout mDisappointedLayout;
    private MediaPlayer mPlayDisappointedSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_disappointed);

        mDisappointedLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout_disappointed);

        //loads song & plays it onCreate
        mPlayDisappointedSong = MediaPlayer.create(getApplicationContext(), R.raw.disappointed_song);
        mPlayDisappointedSong.start();

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