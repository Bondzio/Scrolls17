package in.silive.scrolls16.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.silive.scrolls16.Adapters.TopicsAdapter;
import in.silive.scrolls16.R;


public class DialogTopics extends BottomSheetDialogFragment {
    RecyclerView rvTopics;
    String[] topics;
    String title;
    TextView tvTitle;
    ImageView ivClose;
    TopicsAdapter adapter;
    LinearLayout ll;
    LinearLayout llFullScreen;
    Integer picMembers[] = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight,
            R.drawable.nine, R.drawable.ten, R.drawable.eleven, R.drawable.tweleve};
    private Typeface typeface;

    public void setArgs(String title, String[] topics) {
        this.title = title;
        this.topics = topics;

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

        llFullScreen = (LinearLayout) view.findViewById(R.id.llFullscreen);
        rvTopics = (RecyclerView) view.findViewById(R.id.rvTopic);
        typeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/boldj.ttf");
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvTitle.setText(title);
        tvTitle.setTypeface(typeface);
        adapter = new TopicsAdapter(getActivity(), topics, picMembers);
        rvTopics.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvTopics.setAdapter(adapter);
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

