package oxymium.moodtracker.model.Button;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import oxymium.moodtracker.R;

/**
 * Created by Raspberyl on 13/11/2017.
 */

public class SuperHappyButton extends MoodButton {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vSuperHappyButtonLayoutInflater = inflater.inflate(R.layout.button_super_happy_layout, container, false);

        return vSuperHappyButtonLayoutInflater;
    }
}
