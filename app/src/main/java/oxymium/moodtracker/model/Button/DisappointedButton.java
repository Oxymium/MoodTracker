package oxymium.moodtracker.model.Button;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import oxymium.moodtracker.R;

/**
 * Created by Raspberyl on 13/11/2017.
 */

public class DisappointedButton extends MoodButton {

    String moodName;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vSuperHappySmileyLayout = inflater.inflate(R.layout.button_disappointed_layout, container, false);

        return vSuperHappySmileyLayout;
    }

}
