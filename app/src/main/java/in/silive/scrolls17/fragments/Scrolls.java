package in.silive.scrolls17.fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import in.silive.scrolls17.network.CheckConnectivity;
import in.silive.scrolls17.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Scrolls extends Fragment {
    WebView web_view;
    public static boolean isNetConnectionAvailable;


    public Scrolls() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scrolls, container, false);
        CheckConnectivity checkConnectivity = new CheckConnectivity();
        isNetConnectionAvailable = checkConnectivity.isNetConnected(getContext());
        web_view = (WebView) view.findViewById(R.id.web_view);
        WebSettings webSettings = web_view.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web_view.setWebViewClient(new myWebClient());
        if (isNetConnectionAvailable)
        web_view.loadUrl("scrolls.silive.in");
        else {
            DialogNoNetConnection dialogNoNetConnection = new DialogNoNetConnection();
            dialogNoNetConnection.show(getChildFragmentManager(),"No net connection");
        }
        return view;
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
