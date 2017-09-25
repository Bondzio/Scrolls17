package in.silive.scrolls17.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import in.silive.scrolls17.adapters.AboutUsListAdapterCNew;
import in.silive.scrolls17.adapters.DomainsAdapter;
import in.silive.scrolls17.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ScrollsTeamNew extends Fragment implements View.OnClickListener {
    View view;
    private RecyclerView rvDomains;
    private GridLayoutManager layoutManager;
    private String[] domains;
    private DomainsAdapter adapter;

    static   TopicsFragment fragment;
    private String[] imagesArray;
    String teamMembers [] = {"Prof. V.K. Parashar","Disha Tripathi","Sidhant Kandpal","Pratyush Sharma"};
    String desigOfMembers [] = {"DSW, AKGEC","CONVENOR","CONVENOR","CONVENOR"};
    Integer picMembers [] = {R.drawable.parashar,R.drawable.disha,R.drawable.sidhant,R.drawable.pratyush};
    private RecyclerView scrolls_team;
    WebView webView;

    public ScrollsTeamNew() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.scrollsdeveloper, container, false);



        //rvDomains.setLayoutManager(layoutManager);
        scrolls_team = (RecyclerView) view.findViewById(R.id.scrolls_team);
        scrolls_team.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(),2);
        scrolls_team.setLayoutManager(layoutManager);
        AboutUsListAdapterCNew aboutUsListAdapter = new AboutUsListAdapterCNew(getContext(),teamMembers,desigOfMembers,picMembers);
        scrolls_team.setAdapter(aboutUsListAdapter);
        return view;
    }


    public void openBottomSheet(View v){

    }

    @Override
    public void onClick(View view) {

    }
}
