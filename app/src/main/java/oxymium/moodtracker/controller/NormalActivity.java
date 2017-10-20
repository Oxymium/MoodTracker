package oxymium.moodtracker.controller;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import oxymium.moodtracker.R;

public class NormalActivity extends AppCompatActivity {

    //variables init
    private ConstraintLayout mMainLayout; //Main layout frame

    private ImageView mSmiley; // Main Smiley (unused yet)
    private ImageButton mNoteButton; // Bottom left button (note)
    private ImageButton mHistoryButton; // Bottom right button (history)

    private MediaPlayer mPlayHappySong; // MediaPlayer to play happy_song.wave

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSmiley = (ImageView) findViewById(R.id.mt_smiley);
        mMainLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout);

        mNoteButton = (ImageButton) findViewById(R.id.mt_note_button);
        mHistoryButton = (ImageButton) findViewById(R.id.mt_history_button);

        //set background color (normal = blue)
        mMainLayout.setBackgroundResource(R.color.cornflower_blue_65);
        //set smiley (mood = normal)
        mSmiley.setImageResource(R.drawable.smiley_normal);

        //loads song & plays it onCreate
        mPlayHappySong = MediaPlayer.create(getApplicationContext(), R.raw.normal_song);
        mPlayHappySong.start();

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
        mMainLayout.setOnTouchListener(new OnSwipeTouchListener(NormalActivity.this) {

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
