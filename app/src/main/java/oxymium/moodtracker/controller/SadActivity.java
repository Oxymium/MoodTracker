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

public class SadActivity extends AppCompatActivity {

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

        //apply findViewById method to variables, cast type
        mSmiley = (ImageView) findViewById(R.id.mt_smiley);
        mMainLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout);

        mNoteButton = (ImageButton) findViewById(R.id.mt_note_button);
        mHistoryButton = (ImageButton) findViewById(R.id.mt_history_button);

        //set background color (sad = red)
        mMainLayout.setBackgroundResource(R.color.faded_red);
        //set smiley (mood = sad)
        mSmiley.setImageResource(R.drawable.smiley_sad);

        //loads song & plays it onCreate
        mPlayHappySong = MediaPlayer.create(getApplicationContext(), R.raw.sad_song);
        mPlayHappySong.start();

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
        mMainLayout.setOnTouchListener(new OnSwipeTouchListener(SadActivity.this) {

            public void onSwipeTop() {
                Intent disappointedActivity = new Intent(SadActivity.this, DisappointedActivity.class);
                startActivity(disappointedActivity);
            }

        });
    }
}
