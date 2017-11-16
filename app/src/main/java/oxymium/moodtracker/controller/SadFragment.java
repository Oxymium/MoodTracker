package oxymium.moodtracker.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import oxymium.moodtracker.R;


public class SadFragment extends Fragment {

    // Store instance variables
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static SadFragment newInstance(int page) {
        SadFragment fragmentFirst = new SadFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vSadSmileyLayout = inflater.inflate(R.layout.fragment_sad_layout, container, false);

        return vSadSmileyLayout;
    }

}
