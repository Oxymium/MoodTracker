package oxymium.moodtracker.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Raspberyl on 16/11/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        // Will trigger the following when alarm fires off
        try {
            MainActivity.getInstance().updateTheCurrentPage(3);
            Toast.makeText(context, "HELLO TEST", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }
    }

    }



