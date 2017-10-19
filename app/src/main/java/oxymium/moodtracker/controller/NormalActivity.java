package oxymium.moodtracker.controller;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import oxymium.moodtracker.R;

public class NormalActivity extends AppCompatActivity {

    private ConstraintLayout mNormalLayout;

    private MediaPlayer mPlayNormalSong; // MediaPlayer to play normal_song.wave

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_normal);

        mNormalLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout_normal);

        //loads song & plays it onCreate
        mPlayNormalSong = MediaPlayer.create(getApplicationContext(), R.raw.normal_song);
        mPlayNormalSong.start();

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
