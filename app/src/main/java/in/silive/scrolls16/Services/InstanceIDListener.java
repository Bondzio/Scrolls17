package in.silive.scrolls16.Services;

import android.content.Intent;
import android.content.SharedPreferences;

import com.google.android.gms.iid.InstanceIDListenerService;

import in.silive.scrolls16.Util.Config;

/**
 * Created by akriti on 4/9/16.
 */
public class InstanceIDListener extends InstanceIDListenerService {


    @Override
    public void onTokenRefresh() {
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SP_KEY,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Config.GCM,true);
        Intent i = new Intent(this, RegisterGCM.class);
        startService(i);
    }
}