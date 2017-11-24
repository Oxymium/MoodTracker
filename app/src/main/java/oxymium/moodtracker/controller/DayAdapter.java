package oxymium.moodtracker.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import oxymium.moodtracker.R;

/**
 * Created by Raspberyl on 24/11/2017.
 */

public class DayAdapter extends ArrayAdapter<Day> {

    Context context;
    int resource;
    ArrayList<Day> day = null;

    public DayAdapter(@NonNull Context context, int resource, ArrayList<Day> day) {
        super(context, resource, day);
        this.context = context;
        this.resource = resource;
        this.day = day;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Day days = day.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_day_layout, parent, false);
        }

        // Here to add layouts specifics

        Button dayNameText = (Button) convertView.findViewById(R.id.mt_button_happy_layout);
        Button backgroundColor = (Button) convertView.findViewById(R.id.mt_button_happy_layout);
        Button moodComment = (Button) convertView.findViewById(R.id.mt_button_happy_layout);

        dayNameText.setText(days.mDayName);
        backgroundColor.setBackgroundResource(days.mMoodColor);
        moodComment.setVisibility(View.VISIBLE);

        return convertView;


    }

}

