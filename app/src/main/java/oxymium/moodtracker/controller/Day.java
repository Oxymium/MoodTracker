package oxymium.moodtracker.controller;

/**
 * Created by Raspberyl on 24/11/2017.
 */

public class Day {

    public String mDayName;
    public int mMoodColor;
    // LATER public int mMoodSize;
    public boolean mHasMoodText;
    // LATER public boolean mIsClickable;

    public Day (String mDayName, int mMoodColor, boolean hasMoodText) {
        this.mDayName = mDayName;
        this.mMoodColor = mMoodColor;
        // this.mMoodSize = mMoodSize;
        this.mHasMoodText = hasMoodText;
    }

}
