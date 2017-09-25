package in.silive.scrolls16.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import in.silive.scrolls16.Fragments.About_Scrolls;
import in.silive.scrolls16.Fragments.QueryUs;
import in.silive.scrolls16.Fragments.ReachUs;
import in.silive.scrolls16.Fragments.Register;
import in.silive.scrolls16.Fragments.Rules;
import in.silive.scrolls16.Fragments.ScheduleFragment;
import in.silive.scrolls16.Fragments.ScrollsDeveloperNew;
import in.silive.scrolls16.Fragments.ScrollsTeamNew;
import in.silive.scrolls16.Fragments.TopicsFragment;
import in.silive.scrolls16.Fragments.UploadDoc;
import in.silive.scrolls16.R;
import in.silive.scrolls16.Util.Config;

public class SecondActivity extends AppCompatActivity {
Toolbar toolbar;
    Bundle bundle;
    String title;
    WebView webView;
    private RelativeLayout imagehead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        imagehead=(RelativeLayout)findViewById(R.id.imageHead);
        setSupportActionBar(toolbar);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) imagehead.getLayoutParams();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if (getIntent().getExtras()!=null){
            try {
                title = getIntent().getExtras().getString(Config.KEY_FRAGMENT);
                switch (title){

                    case Config.KEY_Topics:
                        showFragment(new TopicsFragment(), title);
                        imagehead.setBackgroundResource(R.drawable.topics);

                         params.leftMargin = 140;
                        break;
                    case Config.KEY_SCROLLSRULE:
                        showFragment(new Rules(),title);
                        imagehead.setBackgroundResource(R.drawable.rulesandregulations);
                        params.leftMargin = 70;
                        break;
                    case Config.KEY_REACHUS:
                        showFragment(new ReachUs(),title);
                        imagehead.setBackgroundResource(R.drawable.reachus);
                        break;
                    case Config.KEY_ImpDates:
                        showFragment(new ScheduleFragment(),title);
                        imagehead.setBackgroundResource(R.drawable.schedule);
                        break;
                    case Config.KEY_QUERY:


                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                        emailIntent.setData(Uri.parse("mailto:akgec@gmail.com"));

                        try {
                            startActivity(emailIntent);
                        } catch (ActivityNotFoundException e) {

                        }

                        break;

                    case Config.KEY_SCROLLSTEAM:
                        showFragment(new ScrollsTeamNew(),title);
                        imagehead.setBackgroundResource(R.drawable.scrollsteam);
                        break;
                    case Config.KEY_SCROLLSDeveloper:
                        showFragment(new ScrollsDeveloperNew(),title);
                        imagehead.setBackgroundResource(R.drawable.developerteam);
                        break;

                }
                getSupportActionBar().setTitle("");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
switch (item.getItemId()){
    case android.R.id.home: onBackPressed();
        return true;
}
        return false;
    }
    public void showFragment(Fragment fragment, String title) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment)/*.addToBackStack(fragment.getClass().getName())*/;
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        finish();
    }
}
