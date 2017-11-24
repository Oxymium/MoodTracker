package oxymium.moodtracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

import oxymium.moodtracker.R;

public class HistoryActivity extends AppCompatActivity {

    /* Temporary test */

    ListView mHistoryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

       // mYesterday = getIntent().getIntExtra("Yesterday", 0);
       //m2DaysAgo = getIntent().getIntExtra("TwoDaysAgo", 0);
       //m3DaysAgo = getIntent().getIntExtra("ThreeDaysAgo", 0);
       //m4DaysAgo = getIntent().getIntExtra("FourDaysAgo", 0);
       //m5DaysAgo = getIntent().getIntExtra("FiveDaysAgo", 0);
       //m6DaysAgo = getIntent().getIntExtra("SixDaysAgo", 0);
       //m7DaysAgo = getIntent().getIntExtra("SevenDaysAgo", 0);

        mHistoryListView = (ListView) findViewById(R.id.mt_listview_layout);

        ArrayList<Day> days = new ArrayList<Day>();
        Day sevenDaysAgo = new Day("7 days ago", R.color.cornflower_blue_65, 700, false);
        Day sixDaysAgo = new Day("6 days ago", R.color.warm_grey, 600, false);
        Day fiveDaysAgo = new Day("5 days ago", R.color.light_sage, 800, false);
        Day fourDaysAgo = new Day("4 days ago", R.color.banana_yellow, 900, false);
        Day threeDaysAgo = new Day("3 days ago", R.color.light_sage , 800, false);
        Day twoDaysAgo = new Day("2 days ago", R.color.faded_red, 500, true);
        Day yesterday = new Day("Yesterday", R.color.light_sage, 800, true);

        days.add(sevenDaysAgo);
        days.add(sixDaysAgo);
        days.add(fiveDaysAgo);
        days.add(fourDaysAgo);
        days.add(threeDaysAgo);
        days.add(twoDaysAgo);
        days.add(yesterday);

        DayAdapter dayAdapter = new DayAdapter(getApplicationContext(), R.layout.fragment_happy_layout, days);
        mHistoryListView.setAdapter(dayAdapter);




    }

}




