package oxymium.moodtracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


import oxymium.moodtracker.R;

public class HistoryActivity extends AppCompatActivity {

    /* Temporary test */

    ListView mHistoryListView;
    TextView mYesterdayB, m2daysAgoB, m3daysAgoB, m4daysAgoB, m5daysAgoB, m6daysAgoB, m7daysAgoB;

    private int mYesterday, m2DaysAgo, m3DaysAgo, m4DaysAgo, m5DaysAgo, m6DaysAgo, m7DaysAgo;
    private int[] daysArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

       mYesterday = getIntent().getIntExtra("Yesterday", 0);
       m2DaysAgo = getIntent().getIntExtra("TwoDaysAgo", 0);
       m3DaysAgo = getIntent().getIntExtra("ThreeDaysAgo", 0);
       m4DaysAgo = getIntent().getIntExtra("FourDaysAgo", 0);
       m5DaysAgo = getIntent().getIntExtra("FiveDaysAgo", 0);
       m6DaysAgo = getIntent().getIntExtra("SixDaysAgo", 0);
       m7DaysAgo = getIntent().getIntExtra("SevenDaysAgo", 0);

        TextView b1 =(TextView) findViewById(R.id.mt_button_1);
        TextView b2 =(TextView) findViewById(R.id.mt_button_2);
        TextView b3 =(TextView) findViewById(R.id.mt_button_3);
        TextView b4 =(TextView) findViewById(R.id.mt_button_4);
        TextView b5 =(TextView) findViewById(R.id.mt_button_5);
        TextView b6 =(TextView) findViewById(R.id.mt_button_6);
        TextView b7 =(TextView) findViewById(R.id.mt_button_7);

        /* CHECK INDEX 0 IS 1 !! */
        b1.setText(String.valueOf(mYesterday));
        b2.setText(String.valueOf(m2DaysAgo));
        b3.setText(String.valueOf(m3DaysAgo));
        b4.setText(String.valueOf(m4DaysAgo));
        b5.setText(String.valueOf(m5DaysAgo));
        b6.setText(String.valueOf(m6DaysAgo));
        b7.setText(String.valueOf(m7DaysAgo));




    }

}




