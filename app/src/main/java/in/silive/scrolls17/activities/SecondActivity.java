package in.silive.scrolls17.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import in.silive.scrolls17.fragments.About_Scrolls;
import in.silive.scrolls17.fragments.ReachUs;

import in.silive.scrolls17.fragments.Rules;
import in.silive.scrolls17.fragments.ScheduleFragment;
import in.silive.scrolls17.fragments.ScrollsDeveloperNew;
import in.silive.scrolls17.fragments.ScrollsTeamNew;
import in.silive.scrolls17.fragments.TopicsFragment;
import in.silive.scrolls17.R;
import in.silive.scrolls17.util.Config;

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
        Guideline guideLine = (Guideline) findViewById(R.id.guideline10);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) guideLine.getLayoutParams();

       // ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) imagehead.getLayoutParams();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if (getIntent().getExtras()!=null){
            try {
                title = getIntent().getExtras().getString(Config.KEY_FRAGMENT);
                switch (title){

                    case Config.KEY_Topics:
                        showFragment(new TopicsFragment(), title);
                        imagehead.setBackgroundResource(R.drawable.topics);
                        params.guidePercent = 0.4f; // 45% // range: 0 <-> 1
                        guideLine.setLayoutParams(params);
                         //params.leftMargin = 140;
                        break;
                    case Config.KEY_SCROLLSRULE:
                        showFragment(new Rules(),title);
                        imagehead.setBackgroundResource(R.drawable.rulesandregulations);
                        params.guidePercent = 0.15f; // 45% // range: 0 <-> 1
                        guideLine.setLayoutParams(params);
                        //params.leftMargin = 70;
                        break;
                    case Config.KEY_REACHUS:
                        showFragment(new ReachUs(),title);
                        imagehead.setBackgroundResource(R.drawable.reachus);
                        break;
                    case Config.KEY_ImpDates:
                        showFragment(new ScheduleFragment(),title);
                        params.guidePercent = 0.38f; // 45% // range: 0 <-> 1
                        guideLine.setLayoutParams(params);
                        imagehead.setBackgroundResource(R.drawable.schedule);
                        break;
                    case Config.KEY_QUERY:

                        showFragment(new About_Scrolls(),title);
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                        emailIntent.setData(Uri.parse("mailto:akgecscrolls17@gmail.com"));

                        try {
                            startActivityForResult(emailIntent,1);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {Intent i=new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            finish();

        }
    }
}
