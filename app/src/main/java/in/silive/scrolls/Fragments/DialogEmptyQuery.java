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
public class DialogEmptyQuery extends DialogFragment {
    Button ok_empty_query;


    public DialogEmptyQuery() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().setCancelable(false);
        getDialog().setTitle("Empty query");
        View dialogemptyquery_view = inflater.inflate(R.layout.fragment_dialog_empty_query, container, false);
        ok_empty_query = (Button)dialogemptyquery_view.findViewById(R.id.ok_empty_query);
        ok_empty_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return dialogemptyquery_view;
    }

}
