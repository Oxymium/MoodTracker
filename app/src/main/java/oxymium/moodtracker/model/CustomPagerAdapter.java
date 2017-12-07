package oxymium.moodtracker.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import oxymium.moodtracker.view.DisappointedFragment;
import oxymium.moodtracker.view.HappyFragment;
import oxymium.moodtracker.view.NormalFragment;
import oxymium.moodtracker.view.SadFragment;
import oxymium.moodtracker.view.SuperHappyFragment;


public class CustomPagerAdapter extends FragmentPagerAdapter {
    // Number equals to number of smiley states (5)
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
            case 0: // Fragment # 0 - This will show SadFragment
                return SadFragment.newInstance(0);
            case 1: // Fragment # 1 - This will show DisappointedFragment
                return DisappointedFragment.newInstance(1);
            case 2: // Fragment # 2 - This will show NormalFragment
                return NormalFragment.newInstance(2);
            case 3: // Fragment # 3 - This will show HappyFragment
                return HappyFragment.newInstance(3);
            case 4: // Fragment # 4 - This will show SuperHappyFragment
                return SuperHappyFragment.newInstance(4);

            default:
                return null;
        }
    }
}
