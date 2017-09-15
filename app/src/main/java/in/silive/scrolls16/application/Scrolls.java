package in.silive.scrolls16.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import in.silive.scrolls16.Util.Config;

/**
 * Created by root on 15/9/17.
 */

public class Scrolls extends Application {

    private static Scrolls singleton = null;
    public SharedPreferences sharedPrefs;


    public static Scrolls getInstance() {
        return singleton;
    }




    @Override
    public void onCreate() {
        singleton = this;
        super.onCreate();
        sharedPrefs = getSharedPreferences(Config.SHARED_PREFS, Context.MODE_PRIVATE);

    }
}