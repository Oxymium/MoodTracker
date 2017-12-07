package oxymium.moodtracker.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import oxymium.moodtracker.R;


public class SuperHappyFragment extends Fragment {

    // Store instance variables
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static SuperHappyFragment newInstance(int page) {
        SuperHappyFragment fragmentFirst = new SuperHappyFragment();
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
        View vSuperHappySmileyLayout = inflater.inflate(R.layout.fragment_super_happy_layout, container, false);

        return vSuperHappySmileyLayout;
    }

}
