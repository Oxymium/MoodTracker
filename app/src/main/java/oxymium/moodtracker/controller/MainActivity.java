package oxymium.moodtracker.controller;

import android.content.Intent;
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
    private ConstraintLayout mSuperHappyLayout; //layout super happy
    private ConstraintLayout mNormalLayout; //layout normal
    private ConstraintLayout mDisappointedLayout; //layout disappointed
    private ConstraintLayout mSadLayout; //layout sad


    private ImageView mSmiley; // Main Smiley
    private ImageButton mNoteButton; // Bottom left button (note)
    private ImageButton mHistoryButton; // Bottom right button (history)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //apply findViewById method to variables, cast type
        mSmiley = (ImageView) findViewById(R.id.mt_smiley);
        mNoteButton = (ImageButton) findViewById(R.id.mt_note_button);
        mHistoryButton = (ImageButton) findViewById(R.id.mt_history_button);

        mHappyLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout_happy);
        mSuperHappyLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout_super_happy);
        mNormalLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout_normal);
        mSadLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout_sad);
        mDisappointedLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout_disappointed);


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