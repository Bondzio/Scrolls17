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
public class DialogInvalidMail extends DialogFragment {
    Button ok_query;


    public DialogInvalidMail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().setCancelable(false);
        getDialog().setTitle("My Dialog Title");

        View dialoview = inflater.inflate(R.layout.fragment_dialog_invalid_mail, container, false);
        ok_query = (Button)dialoview.findViewById(R.id.ok_query);
        ok_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return dialoview;
    }

}
