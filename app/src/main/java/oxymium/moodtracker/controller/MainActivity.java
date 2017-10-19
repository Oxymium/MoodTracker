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


public class MainActivity extends AppCompatActivity {

    //Variable init
    private ConstraintLayout mHappyLayout; //Main layout frame

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
        mNoteButton = (ImageButton) findViewById(R.id.mt_note_button);
        mHistoryButton = (ImageButton) findViewById(R.id.mt_history_button);

        mHappyLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout_happy);

        //play happy song onCreate
        mPlayHappySong = MediaPlayer.create(getApplicationContext(), R.raw.happy_song);
        mPlayHappySong.start();

        //set listener on mHistoryButton
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when clicked, start HistoryActivity
                Intent historyActivity = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivity);


            }

        });

        //set listener on screen (mHappyLayout) to touchSwipe (calls OnSwipeTouchListener class)
        mHappyLayout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {


            public void onSwipeTop() {
                Intent superHappyActivity = new Intent(MainActivity.this, SuperHappyActivity.class);
                startActivity(superHappyActivity);
            }

            public void onSwipeBottom() {
                Intent normalActivity = new Intent(MainActivity.this, NormalActivity.class);
                startActivity(normalActivity);

            }

        });
    }
}