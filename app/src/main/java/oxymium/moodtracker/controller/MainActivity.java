package oxymium.moodtracker.controller;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


import oxymium.moodtracker.R;


public class MainActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;

    //variables init
    private ConstraintLayout mMainLayout; //Main layout frame

    private ImageView mSmiley; // Main Smiley (unused yet)
    private ImageButton mNoteButton; // Bottom left button (note)
    private ImageButton mHistoryButton; // Bottom right button (history)

    private MediaPlayer mPlayHappySong; // MediaPlayer to play happy_song.wave

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout);
        mSmiley = (ImageView) findViewById(R.id.mt_smiley_image);

        VerticalViewPager vpPager = (VerticalViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new CustomPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        // onCreate, set vpPager.setCurrentItem(1) (Happy Smiley) by default

        vpPager.setCurrentItem(3);
        mMainLayout.setBackgroundResource(R.color.light_sage);

        // TEST

        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position == 0){
                    mMainLayout.setBackgroundResource(R.color.faded_red);
                    mSmiley.setImageResource(R.drawable.smiley_sad);
                }

                if(position == 1) {
                    mMainLayout.setBackgroundResource(R.color.warm_grey);
                    mSmiley.setImageResource(R.drawable.smiley_disappointed);

                }

                if(position == 2) {
                    mMainLayout.setBackgroundResource(R.color.cornflower_blue_65);
                    mSmiley.setImageResource(R.drawable.smiley_normal);

                }

                if(position == 3) {
                    mMainLayout.setBackgroundResource(R.color.light_sage);
                    mSmiley.setImageResource(R.drawable.smiley_happy);

                }

                if(position == 4) {
                    mMainLayout.setBackgroundResource(R.color.banana_yellow);
                    mSmiley.setImageResource(R.drawable.smiley_super_happy);

                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




        mMainLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout);






    }

}