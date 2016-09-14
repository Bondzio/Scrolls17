package in.silive.scrolls.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.silive.scrolls.Adapters.ScheduleAdapter;
import in.silive.scrolls.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment  {
    public static String TAG = "ScheduleFragment";
    RecyclerView rvSchedule;
    ScheduleAdapter adapter;
    String dates[], labels[],days[];
    LinearLayoutManager linearLayoutManager;


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
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        rvSchedule = (RecyclerView) view.findViewById(R.id.rvSchedule);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        dates = getContext().getResources().getStringArray(R.array.schedule_dates);
        labels = getContext().getResources().getStringArray(R.array.schedule_labels);
        days = getContext().getResources().getStringArray(R.array.schedule_days);
        adapter = new ScheduleAdapter(getContext(), dates, labels,days);
        rvSchedule.setLayoutManager(linearLayoutManager);
        rvSchedule.setAdapter(adapter);
        return view;
    }


}

