package in.silive.scrolls16.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.silive.scrolls16.Activities.SecondActivity;
import in.silive.scrolls16.Adapters.ScheduleAdapter;
import in.silive.scrolls16.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment  {
    public static String TAG = "ScheduleFragment";
    RecyclerView rvSchedule;
    ScheduleAdapter adapter;
    String dates[], labels[],days[];
    LinearLayoutManager linearLayoutManager;
    SecondActivity secondActivity;

    static   ScheduleFragment fragment;

    public static ScheduleFragment getInstance(){
        if (fragment == null)
            fragment = new ScheduleFragment();
        return fragment;
    }

    public ScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.topicsnew, container, false);
        rvSchedule = (RecyclerView) view.findViewById(R.id.rvtopics);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        dates = getContext().getResources().getStringArray(R.array.schedule_dates);
        labels = getContext().getResources().getStringArray(R.array.schedule_labels);
        days = getContext().getResources().getStringArray(R.array.schedule_days);
        adapter = new ScheduleAdapter(getContext(), dates, labels,days,(SecondActivity)getActivity());
        rvSchedule.setLayoutManager(linearLayoutManager);
        rvSchedule.setAdapter(adapter);
        return view;
    }


}

