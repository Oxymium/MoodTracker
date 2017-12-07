package oxymium.moodtracker.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import oxymium.moodtracker.R;
import oxymium.moodtracker.model.Day;
import oxymium.moodtracker.model.DayAdapter;

public class HistoryActivity extends AppCompatActivity {

    ListView mHistoryListView;
    TextView mHistoryTextView;

    private String mYesterday, mTwoDaysAgo, mThreeDaysAgo, mFourDaysAgo, mFiveDaysAgo, mSixDaysAgo, mSevenDaysAgo;

    private int mActivityYesterdayColor, mActivity2DaysAgoColor, mActivity3DaysAgoColor, mActivity4DaysAgoColor, mActivity5DaysAgoColor, mActivity6DaysAgoColor, mActivity7DaysAgoColor;
    private String mActivityYesterdayComment, mActivity2DaysAgoComment, mActivity3DaysAgoComment, mActivity4DaysAgoComment, mActivity5DaysAgoComment, mActivity6DaysAgoComment, mActivity7DaysAgoComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mHistoryTextView = (TextView) findViewById(R.id.mt_empty_layout);

        mActivityYesterdayColor = getIntent().getIntExtra("YESTERDAY_COLOR", 0);
        mActivityYesterdayComment = getIntent().getStringExtra("YESTERDAY_COMMENT");

        mActivity2DaysAgoColor = getIntent().getIntExtra("TWO_DAYS_AGO_COLOR", 0);
        mActivity2DaysAgoComment = getIntent().getStringExtra("TWO_DAYS_AGO_COMMENT");

        mActivity3DaysAgoColor = getIntent().getIntExtra("THREE_DAYS_AGO_COLOR", 0);
        mActivity3DaysAgoComment = getIntent().getStringExtra("THREE_DAYS_AGO_COMMENT");

        mActivity4DaysAgoColor = getIntent().getIntExtra("FOUR_DAYS_AGO_COLOR", 0);
        mActivity4DaysAgoComment = getIntent().getStringExtra("FOUR_DAYS_AGO_COMMENT");

        mActivity5DaysAgoColor = getIntent().getIntExtra("FIVE_DAYS_AGO_COLOR", 0);
        mActivity5DaysAgoComment = getIntent().getStringExtra("FIVE_DAYS_AGO_COMMENT");

        mActivity6DaysAgoColor = getIntent().getIntExtra("SIX_DAYS_AGO_COLOR", 0);
        mActivity6DaysAgoComment = getIntent().getStringExtra("SIX_DAYS_AGO_COMMENT");

        mActivity7DaysAgoColor = getIntent().getIntExtra("SEVEN_DAYS_AGO_COLOR", 0);
        mActivity7DaysAgoComment = getIntent().getStringExtra("SEVEN_DAYS_AGO_COMMENT");

        mYesterday = "Yesterday";
        mTwoDaysAgo = "2 days ago";
        mThreeDaysAgo = "3 days ago";
        mFourDaysAgo = "4 days ago";
        mFiveDaysAgo = "5 days ago";
        mSixDaysAgo = "6 days ago";
        mSevenDaysAgo = "7 days ago";


        mHistoryListView = (ListView) findViewById(R.id.mt_listview_layout);

        if (mActivityYesterdayComment == null && mActivityYesterdayColor == 0) { /* Set default view when no mood to display */

            setContentView(R.layout.empty_layout);

        } else {

            ArrayList<Day> days = new ArrayList<>();
            Day sevenDaysAgo = new Day(mSevenDaysAgo, mActivity7DaysAgoColor, mActivity7DaysAgoComment);
            Day sixDaysAgo = new Day(mSixDaysAgo, mActivity6DaysAgoColor, mActivity6DaysAgoComment);
            Day fiveDaysAgo = new Day(mFiveDaysAgo, mActivity5DaysAgoColor, mActivity5DaysAgoComment);
            Day fourDaysAgo = new Day(mFourDaysAgo, mActivity4DaysAgoColor, mActivity4DaysAgoComment);
            Day threeDaysAgo = new Day(mThreeDaysAgo, mActivity3DaysAgoColor, mActivity3DaysAgoComment);
            Day twoDaysAgo = new Day(mTwoDaysAgo, mActivity2DaysAgoColor, mActivity2DaysAgoComment);
            Day yesterday = new Day(mYesterday, mActivityYesterdayColor, mActivityYesterdayComment);

        /* Init all views */

            if (mActivity7DaysAgoColor != 0)
            days.add(sevenDaysAgo);
            if (mActivity6DaysAgoColor != 0)
            days.add(sixDaysAgo);
            if (mActivity5DaysAgoColor != 0)
            days.add(fiveDaysAgo);
            if (mActivity4DaysAgoColor != 0)
            days.add(fourDaysAgo);
            if (mActivity3DaysAgoColor != 0)
            days.add(threeDaysAgo);
            if (mActivity2DaysAgoColor != 0) {
            days.add(twoDaysAgo); }

            days.add(yesterday);

        /* Set adapter to custom ListView */
            DayAdapter dayAdapter = new DayAdapter(getApplicationContext(), R.layout.view_day_layout, days);
            mHistoryListView.setAdapter(dayAdapter);
        }

    }

}











