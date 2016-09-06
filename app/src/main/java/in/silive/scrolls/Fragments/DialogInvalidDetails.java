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
public class DialogInvalidDetails extends DialogFragment {
    Button ok_empty_query;


    public DialogInvalidDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().setTitle("Invalid details");
        getDialog().setCancelable(false);
       View view = inflater.inflate(R.layout.fragment_dialog_invalid_details, container, false);
        ok_empty_query = (Button)view.findViewById(R.id.ok);
        ok_empty_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }

}
