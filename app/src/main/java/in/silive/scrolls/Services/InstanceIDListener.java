package in.silive.scrolls.Services;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by akriti on 4/9/16.
 */
public class InstanceIDListener extends InstanceIDListenerService {


    @Override
    public void onTokenRefresh() {
        Intent i = new Intent(this, RegisterGCM.class);
        startService(i);
    }
}