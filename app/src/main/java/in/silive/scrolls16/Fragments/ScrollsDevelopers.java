package in.silive.scrolls16.Fragments;


import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import in.silive.scrolls16.Adapters.AboutUsListAdapter;
import in.silive.scrolls16.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScrollsDevelopers extends BottomSheetDialogFragment {
    RecyclerView scrolls_dev;
    AboutUsListAdapter adapter;
    String namesOfMembers []={"Pranav Chaudhary","Ankit Yadav","Gaurav Arora","Sukankshi Jain","Siddhant Goral","Rajat Sharma","Kunal Chaudhary","Deepak Singh","Akash Kool","Abishek Kumar Gupta","Akriti Verma"};
    String desigOfMembers []={"Web Developer","Web Developer","Web Developer","Web Developer","Web Developer","Web Developer","Web Developer","Web Developer","Web Developer","App Developer","App Developer"};
    Integer picOfMembers []={R.drawable.dt1,R.drawable.dtankit,R.drawable.dt2,R.drawable.dt3,R.drawable.dt4,R.drawable.dt5,R.drawable.dt6,R.drawable.dt7,R.drawable.dt8,R.drawable.dt9,R.drawable.dt10};
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
        View contentView = View.inflate(getContext(), R.layout.fragment_scrolls_developers, null);
        dialog.setContentView(contentView);
        scrolls_dev = (RecyclerView) contentView.findViewById(R.id.scrolls_dev);
        //AboutUsListAdapter aboutUsListAdapter = new AboutUsListAdapter(getContext(),namesOfMembers,desigOfMembers,picOfMembers);
        adapter = new AboutUsListAdapter(getContext(),namesOfMembers,desigOfMembers,picOfMembers);
        scrolls_dev.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        scrolls_dev.setAdapter(adapter);

    }


    public ScrollsDevelopers() {
        }
        // Required empty public constructor



    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scrolls_developers, container, false);
    }*/

}
