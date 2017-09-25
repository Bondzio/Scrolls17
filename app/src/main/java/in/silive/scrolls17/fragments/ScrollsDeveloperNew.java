package in.silive.scrolls17.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.silive.scrolls17.adapters.AboutUsListAdapterNew;
import in.silive.scrolls17.adapters.DomainsAdapter;
import in.silive.scrolls17.R;

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
    String namesOfMembers []={"Mayur Pathak","Nakshatra Pradhan","Kanika Singhal","Ayush Singh","Simranpreet kaur"};
    String desigOfMembers []={"Web Developer","Web Developer","Web Developer","Web Developer","Web Developer","Web Developer","Web Developer","Web Developer","Web Developer","App Developer","App Developer"};
    Integer picOfMembers []={R.drawable.mayur,R.drawable.nakshatra,R.drawable.kanika,R.drawable.ayush,R.drawable.simran};
    private RecyclerView scrolls_team;


    public ScrollsDeveloperNew() {
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
