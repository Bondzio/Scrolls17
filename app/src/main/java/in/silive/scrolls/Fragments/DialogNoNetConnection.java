package in.silive.scrolls.Fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import in.silive.scrolls.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogNoNetConnection extends DialogFragment {
    Button ok_net_connection;


    public DialogNoNetConnection() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().setCancelable(false);
        getDialog().setTitle("No net connection");
        View dialog_net_view = inflater.inflate(R.layout.fragment_dialog_no_net_connection, container, false);
        ok_net_connection = (Button)dialog_net_view.findViewById(R.id.ok_net_connection);
        ok_net_connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return dialog_net_view;
    }

}
