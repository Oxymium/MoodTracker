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
public class SadFragment extends Fragment {

    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static SadFragment newInstance(int page, String title) {
        SadFragment fragmentFirst = new SadFragment();
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
        ImageView mSadSmileyView = new ImageView(getActivity());

        // Draw [smiley_sad] & background color to [faded_red]

        //mSadSmileyView.setImageResource(R.drawable.smiley_sad);
        //mSadSmileyView.setBackgroundResource(R.color.faded_red);

        return mSadSmileyView;
    }

}
