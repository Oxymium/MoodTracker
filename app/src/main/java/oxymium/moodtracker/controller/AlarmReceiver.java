package oxymium.moodtracker.controller;

import android.app.AlarmManager;
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
        // Will be executed when Alarm is fired
        Toast.makeText(context, "HELLO TEST", Toast.LENGTH_SHORT).show();
    }
}

