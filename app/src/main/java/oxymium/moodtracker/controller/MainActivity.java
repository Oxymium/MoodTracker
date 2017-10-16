package oxymium.moodtracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import oxymium.moodtracker.R;

public class MainActivity extends AppCompatActivity {

    //Variable init
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

        //set listener on mHistoryButton
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when clicked, start HistoryActivity
                Intent gameActivity = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(gameActivity);

            }
        });


    }
}
