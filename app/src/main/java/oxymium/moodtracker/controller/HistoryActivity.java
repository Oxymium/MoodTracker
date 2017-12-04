package oxymium.moodtracker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;


import java.util.ArrayList;
import oxymium.moodtracker.R;

public class HistoryActivity extends AppCompatActivity {

    /* Temporary test */

    ListView mHistoryListView;

    private int mActivityYesterdayColor, mActivity2DaysAgoColor, mActivity3DaysAgoColor, mActivity4DaysAgoColor, mActivity5DaysAgoColor, mActivity6DaysAgoColor, mActivity7DaysAgoColor;
    private String mActivityYesterdayComment, mActivity2DaysAgoComment, mActivity3DaysAgoComment, mActivity4DaysAgoComment, mActivity5DaysAgoComment, mActivity6DaysAgoComment, mActivity7DaysAgoComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

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


        mHistoryListView = (ListView) findViewById(R.id.mt_listview_layout);

        String Test = "";

        ArrayList<Day> days = new ArrayList<>();
        Day sevenDaysAgo = new Day("7 days ago", R.color.cornflower_blue_65, "Test", false);
        Day sixDaysAgo = new Day("6 days ago", R.color.warm_grey, Test, true);
        Day fiveDaysAgo = new Day("5 days ago", R.color.light_sage, "test 3", false);
        Day fourDaysAgo = new Day("4 days ago", R.color.banana_yellow, "test4", false);
        Day threeDaysAgo = new Day("3 days ago", R.color.light_sage, "test1", false);
        Day twoDaysAgo = new Day("2 days ago", R.color.faded_red, "test0", false);
        Day yesterday = new Day("Yesterday", R.color.light_sage, "test1", true);

        /* Init all views */
        days.add(sevenDaysAgo);
        days.add(sixDaysAgo);
        days.add(fiveDaysAgo);
        days.add(fourDaysAgo);
        days.add(threeDaysAgo);
        days.add(twoDaysAgo);
        days.add(yesterday);

        /* Set adapter to custom ListView */
        DayAdapter dayAdapter = new DayAdapter(getApplicationContext(), R.layout.view_day_layout, days);
        mHistoryListView.setAdapter(dayAdapter);


    }

}




