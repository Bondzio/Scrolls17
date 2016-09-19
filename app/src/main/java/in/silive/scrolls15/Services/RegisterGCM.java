package in.silive.scrolls15.Services;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import org.json.JSONException;

import in.silive.scrolls15.Listeners.FetchDataListener;
import in.silive.scrolls15.Network.FetchData;
import in.silive.scrolls15.R;
import in.silive.scrolls15.Util.Config;

/**
 * Created by akriti on 4/9/16.
 */
public class RegisterGCM extends IntentService {
    private static final String TAG = "RegisterGCMService";
   //public PrefManager prefManager;

    public RegisterGCM() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        InstanceID instanceID = InstanceID.getInstance(this);
        //prefManager = new PrefManager(this);
        Log.i(TAG, "GCM Registration Token: " + "started");
        try {
            String id = getString(R.string.gcm_defaultSenderId);
            String token = instanceID.getToken(id,
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            FetchData fetchData = new FetchData();
            fetchData.setArgs(Config.GCM_URL+token, new FetchDataListener() {
                @Override
                public void preExecute() {

                }

                @Override
                public void postExecute(String result, int id) throws JSONException {
                    SharedPreferences sharedPreferences = getSharedPreferences(Config.SP_KEY,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(Config.GCM,false);
                }
            });
            Log.i(TAG, "GCM Registration Token: " + token);
            //  prefManager.GCMTokenSent();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
