package in.silive.scrolls.Fragments;


import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import in.silive.scrolls.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUs extends Fragment {
    private BottomSheetBehavior bottomSheetBehavior;
    View bottomSheet;
    Button one,two,three,four,five,six,seven;
    TextView heading,text_left,text_right;


    public AboutUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us2, container, false);
        bottomSheet = (View)view.findViewById(R.id.bottom_sheet);
        heading = (TextView)view.findViewById(R.id.heading);
        text_left = (TextView)view.findViewById(R.id.text_left);
        text_right = (TextView)view.findViewById(R.id.text_right);
        /*one = (Button)view.findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                heading.setText("PATRON");
                text_left.setText("R.K. Agarwal");
                text_right.setText("Director,AKGEC");
            }
        });
        two = (Button)view.findViewById(R.id.two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                heading.setText("ADVISORY HEAD");
                text_left.setText("R.K. Agarwal");
                text_right.setText("Director,AKGEC");
            }
        });
        three = (Button)view.findViewById(R.id.three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                heading.setText("ORGANISING HEAD");
                text_left.setText("V.K. Parashar");
                text_right.setText("DSW,AKGEC");
            }
        });
        four = (Button)view.findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                heading.setText("TECHNICAL COMMITTEE");
                text_left.setText(R.string.Tech_committee);
                text_right.setText(R.string.tech_comm_desg);
            }
        });
        five = (Button)view.findViewById(R.id.five);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                heading.setText("STUDENT COORDINATORS");
                text_right.setText(R.string.stud_corr_phn);
                text_left.setText(R.string.stud_coor);
            }
        });
        six = (Button)view.findViewById(R.id.six);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                heading.setText("WEB DEVELOPERS");
                text_left.setText(R.string.web_d);
                text_right.setVisibility(View.GONE);
            }
        });
        seven = (Button)view.findViewById(R.id.seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                heading.setText("APP DEVELOPERS");
                text_left.setText(R.string.app_dev);
                text_right.setVisibility(View.GONE);
            }
        });
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState==BottomSheetBehavior.STATE_COLLAPSED)
                    bottomSheetBehavior.setPeekHeight(0);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });*/

        return view;
    }

}
