package in.silive.scrolls15.Fragments;


import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import in.silive.scrolls15.Adapters.AboutUsListAdapter;
import in.silive.scrolls15.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScrollsTeam extends BottomSheetDialogFragment {
    ListView scrolls_team;
    String teamMembers [] = {"Prof. V.K. Parashar","Sandeep Gupta","Mayank Bahadur","Suhani Singh"};
    String desigOfMembers [] = {"DSW, AKGEC","8802119708","8745982117",""};
    Integer picMembers [] = {R.drawable.t,R.drawable.st1,R.drawable.st2,R.drawable.st3};
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_scrolls_team, null);
        dialog.setContentView(contentView);
        scrolls_team = (ListView)contentView.findViewById(R.id.scrolls_team);
        AboutUsListAdapter aboutUsListAdapter = new AboutUsListAdapter(getContext(),teamMembers,desigOfMembers,picMembers);
        scrolls_team.setAdapter(aboutUsListAdapter);




    }


    public ScrollsTeam() {
        // Required empty public constructor
    }


    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scrolls_team, container, false);
    }*/

}
