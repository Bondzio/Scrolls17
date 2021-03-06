package in.silive.scrolls17.fragments;


import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import in.silive.scrolls17.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogNoNetConnection extends DialogFragment {
    Button ok_net_connection;


    public DialogNoNetConnection() {
        // Required empty public constructor
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog  dialog = new Dialog(getContext());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStyle(STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog_Alert);
        }

        View view = ((LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.fragment_dialog_no_net_connection,null,false);
        dialog.setTitle("Invalid mail");
        dialog.setCancelable(false);
        ok_net_connection = (Button)view.findViewById(R.id.ok_net_connection);
        ok_net_connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        dialog.setContentView(view);
        return dialog;
    }




}
