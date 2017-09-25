package in.silive.scrolls17.util;

import android.content.Context;
import android.content.Intent;

/**
 * Created by akriti on 4/9/16.
 */
public class GCMUtil {
    static final String SERVER_URL = "http://10.0.2.2/gcm_server_php/register.php";

    // Google project id
    static final String SENDER_ID = "24957423969";


    static final String TAG = "GCM";

    static final String DISPLAY_MESSAGE_ACTION = "DISPLAY_MESSAGE";

    static final String EXTRA_MESSAGE = "message";


    static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }

}
