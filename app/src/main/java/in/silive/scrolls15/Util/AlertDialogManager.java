package in.silive.scrolls15.Util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import in.silive.scrolls15.R;

/**
 * Created by akriti on 4/9/16.
 */
public class AlertDialogManager {

    public void showAlertDialog(Context context, String title, String message,
                                Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        if (status != null)
            alertDialog.setIcon((status) ? R.mipmap.ic_launcher : R.mipmap.ic_launcher);


        alertDialog.setButton(0, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });


        alertDialog.show();
    }
}
