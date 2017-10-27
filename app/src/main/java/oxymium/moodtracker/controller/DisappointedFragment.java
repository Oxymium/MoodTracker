package oxymium.moodtracker.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import oxymium.moodtracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisappointedFragment extends Fragment {

    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static DisappointedFragment newInstance(int page, String title) {
        DisappointedFragment fragmentFirst = new DisappointedFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ImageView mDisappointedSmileyView = new ImageView(getActivity());

        // Draw [smiley_disappointed] & background color to [warm_grey]

        //mDisappointedSmileyView.setImageResource(R.drawable.smiley_disappointed);
        //mDisappointedSmileyView.setBackgroundResource(R.color.warm_grey);
        return mDisappointedSmileyView;
    }

}
