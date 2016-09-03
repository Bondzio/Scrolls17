package in.silive.scrolls.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.silive.scrolls.Adapters.DomainsAdapter;
import in.silive.scrolls.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class About_Scrolls extends Fragment {
RecyclerView rvDomains;
    View rootView;
    GridLayoutManager layoutManager;
    String[]  domains;
    DomainsAdapter adapter;

    public About_Scrolls() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =inflater.inflate(R.layout.fragment_about_scrolls, container, false);
        rvDomains = (RecyclerView)rootView.findViewById(R.id.rvDomains);
        layoutManager = new GridLayoutManager(getContext(),2);
        domains = getActivity().getResources().getStringArray(R.array.domain_array);
        adapter = new DomainsAdapter(getContext(),domains);
        rvDomains.setLayoutManager(layoutManager);
        rvDomains.setAdapter(adapter);
        return rootView;
    }

}
