package in.silive.scrolls16.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.schibsted.spain.parallaxlayerlayout.ParallaxLayerLayout;
import com.schibsted.spain.parallaxlayerlayout.SensorTranslationUpdater;

import in.silive.scrolls16.Fragments.About_Scrolls;
import in.silive.scrolls16.R;
import in.silive.scrolls16.Services.RegisterGCM;
import in.silive.scrolls16.Util.BitmapUtils;
import in.silive.scrolls16.Util.Config;
import io.fabric.sdk.android.Fabric;

/**
 * Created by akriti on 20/8/16.
 */

public class Splash extends AppCompatActivity {
    Context context;
    RelativeLayout splash;
    //ImageView image;
    TextView text;
    private ParallaxLayerLayout parallaxLayout;
    private SensorTranslationUpdater sensorTranslationUpdater;
    private WebView web_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.splashnew);
        splash = (RelativeLayout) findViewById(R.id.splash);
        context = getApplicationContext();
        //image = (ImageView) findViewById(R.id.image);
        text = (TextView) findViewById(R.id.text);
        WebView mWebView = null;
        web_view = (WebView) findViewById(R.id.web_view);
      /*  mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().getAllowFileAccessFromFileURLs();
        mWebView.getSettings().getAllowFileAccess();
        mWebView.clearCache(true);
        mWebView.setWebViewClient(new myWebClient());
        mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Blank listener to disable long click text selection
                return true;
            }
        });
        final WebView finalMWebView = mWebView;
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(final WebView view, final String url) {
                super.onPageFinished(view, url);
                finalMWebView.invalidate();
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.getSettings().getAllowUniversalAccessFromFileURLs();
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.loadUrl("file:///android_asset/landingpage.html");*/
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
     //   parallaxLayout = (ParallaxLayerLayout)findViewById(R.id.parallaxLayer);
       // sensorTranslationUpdater = new SensorTranslationUpdater(this);
        //parallaxLayout.setTranslationUpdater(sensorTranslationUpdater);
       // checkConnection();
        checkGCM();
    }

    private void checkGCM() {
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SP_KEY,MODE_PRIVATE);
        if (sharedPreferences.getBoolean(Config.GCM,true)){
            Intent i = new Intent(this, RegisterGCM.class);
            startService(i);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 6000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //sensorTranslationUpdater.unregisterSensorManager();
    }

    @Override
    protected void onResume() {
        super.onResume();

            web_view.loadUrl("file:///android_asset/landingpage.html");
           //sensorTranslationUpdater.registerSensorManager();
    }

    public void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null) {
            //   Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            //no_net_connection.setVisibility(View.VISIBLE);
            Snackbar snackbar = Snackbar
                    .make(splash, "No internet connection!", Snackbar.LENGTH_INDEFINITE);
// Changing message text color
            snackbar.setActionTextColor(Color.RED);
// Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }

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
