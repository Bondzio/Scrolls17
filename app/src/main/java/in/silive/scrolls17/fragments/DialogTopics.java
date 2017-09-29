package in.silive.scrolls17.fragments;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.silive.scrolls17.adapters.TopicsAdapter;
import in.silive.scrolls17.R;


public class DialogTopics extends BottomSheetDialogFragment {
    RecyclerView rvTopics;
    String[] topics;
    String title;
    TextView tvTitle;
    ImageView ivClose;
    TopicsAdapter adapter;
    LinearLayout ll;
    LinearLayout llFullScreen;
    NestedScrollView scrollView;
    Integer picMembers[] = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight,
            R.drawable.nine, R.drawable.ten, R.drawable.eleven, R.drawable.tweleve};
    private Typeface typeface;

    public void setArgs(String title, String[] topics) {
        this.title = title;
        this.topics = topics;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        scrollView.scrollTo(0,0);
    }

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
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_topic, null, false);
        dialog.setContentView(view);
        scrollView=(NestedScrollView)view.findViewById(R.id.scroll);
        llFullScreen = (LinearLayout) view.findViewById(R.id.llFullscreen);
        rvTopics = (RecyclerView) view.findViewById(R.id.rvTopic);
        typeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/boldj.ttf");
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvTitle.setText(title);
        tvTitle.setTypeface(typeface);
        adapter = new TopicsAdapter(getActivity(), topics, picMembers);
        rvTopics.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvTopics.setAdapter(adapter);
        scrollView.smoothScrollTo(0,0);
        //scrollView.scrollTo(scrollView.getTop(),scrollView.getTop());
        ll = (LinearLayout) view.findViewById(R.id.ll);
        //ivClose = (ImageView) view.findViewById(R.id.ivClose);
        /*ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new TopicsFragment())/*.addToBackStack(fragment.getClass().getName());
                fragmentTransaction.commit();

            }
        });*/
    }


    }

