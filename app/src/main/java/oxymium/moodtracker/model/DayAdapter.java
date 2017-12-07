package oxymium.moodtracker.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import oxymium.moodtracker.R;
import oxymium.moodtracker.controller.SingleToast;
import oxymium.moodtracker.model.Day;


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

        final Day days = day.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_day_layout, parent, false);
        }

        /* Layout specifics */
        TextView dayNameText = (TextView) convertView.findViewById(R.id.mt_day_textview);
        ImageView backgroundColor = (ImageView) convertView.findViewById(R.id.mt_day_background);
        Button moodComment = (Button) convertView.findViewById(R.id.mt_day_button);

        dayNameText.setText(days.mDayName);
        backgroundColor.setBackgroundResource(days.mMoodColor);

        moodComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleToast.show(context, days.mMoodText, Toast.LENGTH_LONG);
            }
        });

        if (days.mMoodText == null || days.mMoodText.trim().isEmpty()) {
            moodComment.setVisibility(View.GONE);  /* If comment is empty, disable button */
        }

        return convertView;


    }



}



