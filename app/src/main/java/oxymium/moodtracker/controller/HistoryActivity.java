package oxymium.moodtracker.controller;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import oxymium.moodtracker.R;

public class HistoryActivity extends AppCompatActivity {

    /* Temporary test */

    Button mYesterdayButton,
            mTwoDaysAgoButton,
            mThreeDaysAgoButton,
            mFourDaysAgoButton,
            mFiveDaysAgoButton,
            mSixDaysAgoButton,
            mAWeekAgoButton;

    private static final String daysOfTheWeek[] = {"Yesterday", "Two days ago", "Three days ago", "Four days ago", "Five days ago", "Six days ago", "A week ago"};


    public SharedPreferences mPreferences;
    String DayTestSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        int sTestSuper = getIntent().getIntExtra("CurrentDayTest", 0);
        String mMoodTextTest = getIntent().getStringExtra("MoodTextTest");

        mYesterdayButton = (Button) findViewById(R.id.mt_history_button_yesterday);
        mYesterdayButton.setText(daysOfTheWeek[0]);
        mYesterdayButton.setText("Day is : " + sTestSuper);

        mTwoDaysAgoButton = (Button) findViewById(R.id.mt_history_button_2d_ago);
        mTwoDaysAgoButton.setText(daysOfTheWeek[1]);
        mTwoDaysAgoButton.setText(mMoodTextTest);

        mThreeDaysAgoButton = (Button) findViewById(R.id.mt_history_button_3d_ago);
        mThreeDaysAgoButton.setText(daysOfTheWeek[2]);

        mFourDaysAgoButton = (Button) findViewById(R.id.mt_history_button_4d_ago);
        mFourDaysAgoButton.setText(daysOfTheWeek[3]);

        mFiveDaysAgoButton = (Button) findViewById(R.id.mt_history_button_5d_ago);
        mFiveDaysAgoButton.setText(daysOfTheWeek[4]);

        mSixDaysAgoButton = (Button) findViewById(R.id.mt_history_button_6d_ago);
        mSixDaysAgoButton.setText(daysOfTheWeek[5]);

        mAWeekAgoButton = (Button) findViewById(R.id.mt_history_button_7d_ago);
        mAWeekAgoButton.setText(daysOfTheWeek[6]);



    }

}






