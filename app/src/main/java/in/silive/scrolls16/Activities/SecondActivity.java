package in.silive.scrolls16.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if (getIntent().getExtras()!=null){
            try {
                title = getIntent().getExtras().getString(Config.KEY_FRAGMENT);
                switch (title){

                    case Config.KEY_Topics:
                        showFragment(new TopicsFragment(), title);
                        break;
                    case Config.KEY_SCROLLSRULE:
                        showFragment(new Rules(),title);
                        break;
                    case Config.KEY_REACHUS:
                        showFragment(new ReachUs(),title);
                        break;
                    case Config.KEY_ImpDates:
                        showFragment(new ScheduleFragment(),title);
                        break;
                    case Config.KEY_QUERY:
                        showFragment(new QueryUs(),title);
                        break;

                    case Config.KEY_SCROLLSTEAM:
                        showFragment(new ScrollsTeamNew(),title);
                        break;
                    case Config.KEY_SCROLLSDeveloper:
                        showFragment(new ScrollsDeveloperNew(),title);
                        break;

                }
                getSupportActionBar().setTitle(title);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
switch (item.getItemId()){
    case android.R.id.home: finish();
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

}
