package example.game.utils;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import example.game.model.Player;

/**
 * Created by frost on 26.11.2017.
 */

public class Settings {
    public static Integer Score=0;
    public static List<Player> playerList=new ArrayList<>();
    static final String APP_PREFERENCES = "ArithSettings";
    static final String APP_PREFERENCES_TIMER = "TimerDuration";
    static final String APP_PREFERENCES_POSITION_SPINNER = "PositionSpinner";
    static final String APP_PREFERENCES_SOUND = "Sound";
    static SharedPreferences preference = null;
    public static void SetTimerDuration(Context context,int duration){
        SharedPreferences preference =context.getSharedPreferences(Settings.APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(Settings.APP_PREFERENCES_TIMER,duration);
        editor.apply();
    }
    public static Integer GetTimerDuration(Context context){
        preference = context.getSharedPreferences(Settings.APP_PREFERENCES, Context.MODE_PRIVATE);
        if(preference.contains(Settings.APP_PREFERENCES_TIMER)) {
            return preference.getInt(APP_PREFERENCES_TIMER,30);
        }
        return 30;
    }

    public static void SetPositionSpinner(Context context,int position){
        preference =context.getSharedPreferences(Settings.APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(Settings.APP_PREFERENCES_POSITION_SPINNER,position);
        editor.apply();
    }
    public static Integer GetPositionSpinner(Context context){
        preference = context.getSharedPreferences(Settings.APP_PREFERENCES, Context.MODE_PRIVATE);
        if(preference.contains(Settings.APP_PREFERENCES_POSITION_SPINNER)) {
            return preference.getInt(APP_PREFERENCES_POSITION_SPINNER,2);
        }
        return 2;
    }

    public static void SetSound(Context context,int on){
        preference = context.getSharedPreferences(APP_PREFERENCES_SOUND,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(APP_PREFERENCES_SOUND,on);
        editor.apply();
    }
    public static Integer GetSound(Context context){
        preference = context.getSharedPreferences(APP_PREFERENCES_SOUND,Context.MODE_PRIVATE);
        if(preference.contains(APP_PREFERENCES_SOUND)) {
            return preference.getInt(APP_PREFERENCES_SOUND,0);
        }
        return 0;
    }
    public static void ClearResults(Context context) throws IOException {
        JsonHelper.Clear(context);
    }
}