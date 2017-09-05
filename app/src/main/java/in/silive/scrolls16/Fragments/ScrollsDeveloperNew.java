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
public class ScrollsDeveloperNew extends Fragment implements View.OnClickListener {
    View view;
    private RecyclerView rvDomains;
    private GridLayoutManager layoutManager;
    private String[] domains;
    private DomainsAdapter adapter;

    static   TopicsFragment fragment;
    private String[] imagesArray;
    String namesOfMembers []={"Pranav Chaudhary","Ankit Yadav","Gaurav Arora","Sukankshi Jain","Siddhant Goral","Rajat Sharma","Kunal Chaudhary","Deepak Singh","Akash Kool","Abishek Kumar Gupta","Akriti Verma"};
    String desigOfMembers []={"Web Developer","Web Developer","Web Developer","Web Developer","Web Developer","Web Developer","Web Developer","Web Developer","Web Developer","App Developer","App Developer"};
    Integer picOfMembers []={R.drawable.dt1,R.drawable.dtankit,R.drawable.dt2,R.drawable.dt3,R.drawable.dt4,R.drawable.dt5,R.drawable.dt6,R.drawable.dt7,R.drawable.dt8,R.drawable.dt9,R.drawable.dt10};
    private RecyclerView scrolls_team;


    public ScrollsDeveloperNew() {
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
        AboutUsListAdapterNew aboutUsListAdapter = new AboutUsListAdapterNew(getContext(),namesOfMembers,desigOfMembers,picOfMembers);
        scrolls_team.setAdapter(aboutUsListAdapter);
        return view;
    }


    public void openBottomSheet(View v){

    }

    @Override
    public void onClick(View view) {

    }
}
