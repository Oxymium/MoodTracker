package oxymium.moodtracker.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesUtils {

    private static final String PREFS_NAME = "PRES_NAME";

    public static SharedPreferences getSharedPreferences(@NonNull Context context) {
        return context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }

    public static void saveString(@NonNull Context context, @NonNull String key, String string) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, string);
        editor.apply();
    }

    public static String loadString(@NonNull Context context, @NonNull String key, String string) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getString(key, string);
    }


    public static void saveInt(@NonNull Context context, @NonNull String key, int intValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, intValue);
        editor.apply();
    }

    public static int loadInt(@NonNull Context context, @NonNull String key, int intDefaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getInt(key, intDefaultValue);
    }


}