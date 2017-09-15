package in.silive.scrolls16.Fragments;


import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import in.silive.scrolls16.Adapters.DomainsAdapter;
import in.silive.scrolls16.R;
import in.silive.scrolls16.Util.BitmapUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class About_Scrolls extends Fragment {
RecyclerView rvDomains;
    View rootView;
    GridLayoutManager layoutManager;
    String[]  domains;
    DomainsAdapter adapter;
    private WebView web_view;
    LinearLayout rl;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    static About_Scrolls fragment;
    private static final int FRAME_W = 250;
    private static final int FRAME_H = 250;
    private static final int NB_FRAMES = 16;
    private static final int COUNT_X = 4;
    private static final int COUNT_Y = 4;
    private static final int FRAME_DURATION = 400;

    public About_Scrolls() {
        // Required empty public constructor
    }

    public static About_Scrolls newInstance(){
        if (fragment==null){
            fragment = new About_Scrolls();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView==null) {
            rootView = inflater.inflate(R.layout.fragment_about_scrolls, container, false);
            web_view = (WebView) rootView.findViewById(R.id.about_scrolls_web_view);
            rl=(LinearLayout)rootView.findViewById(R.id.sprite);
            final WebSettings webSettings = web_view.getSettings();
            webSettings.setJavaScriptEnabled(true);
            web_view.setWebViewClient(new myWebClient());
            web_view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    //Blank listener to disable long click text selection
                    return true;
                }
            });
            web_view.setWebViewClient(new WebViewClient() {

                @Override
                public void onPageFinished(final WebView view, final String url) {
                    super.onPageFinished(view, url);
                    web_view.invalidate();
                }
            });
            startWaterAnimation();
        }
        return rootView;
    }
     private void startWaterAnimation() {
         BitmapUtils bitmapUtils=new BitmapUtils(getActivity());

            Bitmap waterbmp =bitmapUtils.getBitmapFromAssets("sprites.png");
            if (waterbmp != null) {
                Bitmap[] bitmaps = bitmapUtils.getBitmapsFromSprite(waterbmp, NB_FRAMES, COUNT_X, COUNT_Y, FRAME_H, FRAME_W);
                final AnimationDrawable animation = new AnimationDrawable();
                animation.setOneShot(false); // repeat animation

                for (int i = 0; i < NB_FRAMES; i++) {
                    animation.addFrame(new BitmapDrawable(getResources(), bitmaps[i]),
                            FRAME_DURATION);
                }
                rl.setBackground(animation);
                rl.post(new Runnable() {

                    @Override
                    public void run() {
                        animation.start();
                    }

                });
            }
        }
    @Override
    public void onResume() {
        super.onResume();
        web_view.loadUrl("file:///android_asset/about.html");
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }
    }

}
