package in.silive.scrolls16.Fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.silive.scrolls16.Adapters.DomainsAdapter;
import in.silive.scrolls16.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class TopicsFragment extends Fragment implements View.OnClickListener {
View view;
    private RecyclerView rvDomains;
    private GridLayoutManager layoutManager;
    private String[] domains;
    private DomainsAdapter adapter;

    static   TopicsFragment fragment;
    private String[] imagesArray;

    public static TopicsFragment getInstance(){
        if (fragment == null)
            fragment = new TopicsFragment();
        return fragment;
    }

    public TopicsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_topics, container, false);
        rvDomains = (RecyclerView)view.findViewById(R.id.rvDomains);
        layoutManager = new GridLayoutManager(getContext(),2);
        domains = getActivity().getResources().getStringArray(R.array.domain_array);
        imagesArray =  getActivity().getResources().getStringArray(R.array.topic_img_array);
        adapter = new DomainsAdapter(getContext(),domains,imagesArray);
        rvDomains.setLayoutManager(layoutManager);
        rvDomains.setAdapter(adapter);
        return view;
    }


    public void openBottomSheet(View v){

    }

    @Override
    public void onClick(View view) {

    }
}
