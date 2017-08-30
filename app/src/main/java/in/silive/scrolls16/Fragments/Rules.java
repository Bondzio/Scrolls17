package in.silive.scrolls16.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.schibsted.spain.parallaxlayerlayout.ParallaxLayerLayout;
import com.schibsted.spain.parallaxlayerlayout.SensorTranslationUpdater;

import in.silive.scrolls16.Adapters.RulesAdapter;
import in.silive.scrolls16.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Rules extends Fragment {
RecyclerView rv;
    RulesAdapter adapter;
  static   Rules fragment;
    private SensorTranslationUpdater sensorTranslationUpdater;
    private ParallaxLayerLayout parallaxLayout;

    public static Rules getInstance(){
        if (fragment == null)
            fragment = new Rules();
        return fragment;
    }

    public Rules() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rules, container, false);
        rv = (RecyclerView)view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        parallaxLayout = (ParallaxLayerLayout)view.findViewById(R.id.parallaxLayer);
        sensorTranslationUpdater = new SensorTranslationUpdater(getActivity());
        parallaxLayout.setTranslationUpdater(sensorTranslationUpdater);
        adapter = new RulesAdapter(getContext(),getResources().getString(R.string.rules).split("\n"));
        rv.setAdapter(adapter);
        return view;
    }
    @Override
    public void onStop() {
        super.onStop();
        sensorTranslationUpdater.unregisterSensorManager();
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorTranslationUpdater.registerSensorManager();
    }



}
