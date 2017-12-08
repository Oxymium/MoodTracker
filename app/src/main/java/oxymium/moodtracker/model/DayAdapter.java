package oxymium.moodtracker.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import oxymium.moodtracker.R;
import oxymium.moodtracker.controller.MainActivity;
import oxymium.moodtracker.controller.SharedPreferencesUtils;
import oxymium.moodtracker.controller.SingleToast;
import oxymium.moodtracker.model.Day;


public class DayAdapter extends ArrayAdapter<Day> {

    Context context;
    int resource;
    ArrayList<Day> day = null;
    SharedPreferences mSharedPreferences;


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
        //ImageView backgroundColor = (ImageView) convertView.findViewById(R.id.mt_day_background);
        Button moodComment = (Button) convertView.findViewById(R.id.mt_day_button);
        RelativeLayout backgroundColor = (RelativeLayout) convertView.findViewById(R.id.mt_view_relative_layout);
        LinearLayout mLinearLayout = (LinearLayout) convertView.findViewById(R.id.mt_history_linearlayout);

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

        if (days.mMoodText == null || days.mMoodText.trim().isEmpty()) {
            moodComment.setVisibility(View.GONE);  /* If comment is empty, disable button */
        }


        // ---------------------------------------------------- //

        final int mScreenWidth = SharedPreferencesUtils.loadInt(context, "SCREEN_WIDTH", 0);

        // System.out.println("VALEUR DE SCREEN : " + mScreenWidth);


        if (days.mMoodText == null || days.mMoodText.trim().isEmpty()) {
            moodComment.setVisibility(View.GONE);  /* If comment is empty, disable button */
        }

        if (days.mMoodColor == R.color.faded_red) {
            backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams((mScreenWidth * 20) / 100, dpToPx(80)));
        }

        if (days.mMoodColor == R.color.warm_grey) {
            backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams((mScreenWidth * 40) / 100, dpToPx(80)));

        }

        if (days.mMoodColor == R.color.cornflower_blue_65) {
            backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams((mScreenWidth * 60) / 100, dpToPx(80)));

        }

        if (days.mMoodColor == R.color.light_sage) {
            backgroundColor.setLayoutParams(new RelativeLayout.LayoutParams((mScreenWidth * 80) / 100, dpToPx(80)));


        }


        return convertView;
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

}




