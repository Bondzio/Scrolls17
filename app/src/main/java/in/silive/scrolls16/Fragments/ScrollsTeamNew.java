package in.silive.scrolls16.Fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.silive.scrolls16.Adapters.AboutUsListAdapter;
import in.silive.scrolls16.Adapters.AboutUsListAdapterNew;
import in.silive.scrolls16.Adapters.DomainsAdapter;
import in.silive.scrolls16.R;

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
    String teamMembers [] = {"Prof. V.K. Parashar","Sandeep Gupta","Mayank Bahadur","Suhani Singh"};
    String desigOfMembers [] = {"DSW, AKGEC","8802119708","8745982117","8908767865"};
    Integer picMembers [] = {R.drawable.t,R.drawable.st1,R.drawable.st2,R.drawable.st3};
    private RecyclerView scrolls_team;


    public ScrollsTeamNew() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.scrollsteam, container, false);



        //rvDomains.setLayoutManager(layoutManager);
        scrolls_team = (RecyclerView) view.findViewById(R.id.scrolls_team);
        scrolls_team.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(),2);
        scrolls_team.setLayoutManager(layoutManager);
        AboutUsListAdapterNew aboutUsListAdapter = new AboutUsListAdapterNew(getContext(),teamMembers,desigOfMembers,picMembers);
        scrolls_team.setAdapter(aboutUsListAdapter);
        return view;
    }


    public void openBottomSheet(View v){

    }

    @Override
    public void onClick(View view) {

    }
}
