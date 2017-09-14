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
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.schibsted.spain.parallaxlayerlayout.ParallaxLayerLayout;
import com.schibsted.spain.parallaxlayerlayout.SensorTranslationUpdater;

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
        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.loadUrl("file:///android_asset/test.svg");
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
        }, 3000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //sensorTranslationUpdater.unregisterSensorManager();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
   /* private void startWaterAnimation() {

        Bitmap waterbmp = BitmapUtils.getBitmapFromAssets("splashh.png");
        if (waterbmp != null) {
            Bitmap[] bitmaps = BitmapUtils.getBitmapsFromSprite(waterbmp, NB_FRAMES, COUNT_X, COUNT_Y, FRAME_H, FRAME_W);
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
    }*/
}
