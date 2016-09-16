package in.silive.scrolls.Fragments;

import android.animation.Animator;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.silive.scrolls.Adapters.TopicsAdapter;
import in.silive.scrolls.R;

/**
 * Created by AKG002 on 03-09-2016.
 */
public class DialogTopics extends BottomSheetDialogFragment {
    RecyclerView rvTopics;
    String[] topics;
    String title;
    TextView tvTitle;
    ImageView ivClose;
TopicsAdapter adapter;
    LinearLayout ll;
    LinearLayout llFullScreen;

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
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvTitle.setText(title);
        adapter = new TopicsAdapter(getActivity(), topics);
        rvTopics.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvTopics.setAdapter(adapter);
        ll = (LinearLayout) view.findViewById(R.id.ll);
        ivClose = (ImageView) view.findViewById(R.id.ivClose);
    }


}
