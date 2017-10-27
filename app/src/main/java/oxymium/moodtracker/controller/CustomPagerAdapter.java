package oxymium.moodtracker.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Raspberyl on 26/10/2017.
 */

//CustomPagerAdapter hérite de FragmentPagerAdapter
public class CustomPagerAdapter extends FragmentPagerAdapter {
    //number equals to number of smiley states (5)
    private static int NUM_SMILEYS = 5;

    public CustomPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages/smileys
    @Override
    public int getCount() {
        return NUM_SMILEYS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return SadFragment.newInstance(4, "Page # 3");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return DisappointedFragment.newInstance(3, "Page # 3");
            case 2: // Fragment # 1 - This will show SecondFragment
                return NormalFragment.newInstance(2, "Page # 3");
            case 3: // Fragment # 1 - This will show SecondFragment
                return HappyFragment.newInstance(1, "Page # 2");
            case 4: // Fragment # 1 - This will show SecondFragment
                return SuperHappyFragment.newInstance(0, "Page # 1");

            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}
