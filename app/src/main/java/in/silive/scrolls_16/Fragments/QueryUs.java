package in.silive.scrolls_16.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.silive.scrolls_16.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QueryUs extends Fragment {


    public QueryUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_query_us, container, false);
    }

}
