package oxymium.moodtracker.controller;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import oxymium.moodtracker.R;

public class SuperHappyActivity extends AppCompatActivity {

    private ConstraintLayout mSuperHappyLayout; //layout super happy

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_super_happy);

        mSuperHappyLayout = (ConstraintLayout) findViewById(R.id.mt_full_layout_super_happy);

        mSuperHappyLayout.setOnTouchListener(new OnSwipeTouchListener(SuperHappyActivity.this) {

            public void onSwipeBottom() {
                Intent mainActivity = new Intent(SuperHappyActivity.this, MainActivity.class);
                startActivity(mainActivity);
            }

        });

    }
}