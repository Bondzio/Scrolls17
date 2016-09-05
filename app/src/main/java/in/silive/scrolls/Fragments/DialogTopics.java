package in.silive.scrolls.Fragments;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.silive.scrolls.Adapters.TopicsAdapter;
import in.silive.scrolls.R;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by AKG002 on 03-09-2016.
 */
public class DialogTopics extends DialogFragment {
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


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(STYLE_NO_FRAME);
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_topic,null,false);
        llFullScreen = (LinearLayout)view.findViewById(R.id.llFullscreen);
        rvTopics = (RecyclerView)view.findViewById(R.id.rvTopic);
        tvTitle = (TextView)view.findViewById(R.id.tvTitle);
        tvTitle.setText(title);
        adapter = new TopicsAdapter(getActivity(),topics);
        rvTopics.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rvTopics.setAdapter(adapter);
        ll = (LinearLayout)view.findViewById(R.id.ll);
        ivClose = (ImageView)view.findViewById(R.id.ivClose);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateDialogClose();
            }
        });
        dialog.setContentView(view);
        ll.setVisibility(View.INVISIBLE);
        ll.post(new Runnable() {
            @Override
            public void run() {
                animateDialogReveal();
            }
        });
        dialog.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                  animateDialogClose();
                    dialog.setOnKeyListener(null);
                }
                return true;
            }
        });
        llFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateDialogClose();
            }
        });
        dialog.setCancelable(false);
        return dialog;
    }

    private void animateDialogReveal() {
        int cx =ll.getWidth()/2,cy =ll.getHeight()/2;
        int dx = Math.max(cx, ll.getWidth());
        int dy = Math.max(cy, ll.getHeight());
        float initialRadius = (float) Math.hypot(dx, dy);

        // Android native animator
        Animator animator =
                ViewAnimationUtils.createCircularReveal(ll,cx,cy, 0,initialRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(650);
ll.setVisibility(View.VISIBLE);

        animator.start();
    }

    private void animateDialogClose() {
        int cx =ll.getWidth()/2,cy =ll.getHeight()/2;
        int dx = Math.max(cx, ll.getWidth());
        int dy = Math.max(cy, ll.getHeight());
        float initialRadius = (float) Math.hypot(dx, dy);

        // Android native animator
        Animator animator =
                ViewAnimationUtils.createCircularReveal(ll,cx,cy, initialRadius, 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(650);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ll.setVisibility(View.GONE);
                getDialog().dismiss();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        animator.start();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

}
