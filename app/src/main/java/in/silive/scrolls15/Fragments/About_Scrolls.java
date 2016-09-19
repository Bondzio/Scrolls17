package in.silive.scrolls15.Fragments;


import android.graphics.Bitmap;
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

import in.silive.scrolls15.Adapters.DomainsAdapter;
import in.silive.scrolls15.R;

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
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    static About_Scrolls fragment;

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
        }
        return rootView;
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
