package in.silive.scrolls.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.silive.scrolls.Adapters.TopicsAdapter;
import in.silive.scrolls.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Rules extends Fragment {
RecyclerView rv;
    TopicsAdapter adapter;

    public Rules() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rules, container, false);
        rv = (RecyclerView)view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new TopicsAdapter(getContext(),getResources().getString(R.string.rules).split("\n"));
        rv.setAdapter(adapter);
        return view;
    }

}
