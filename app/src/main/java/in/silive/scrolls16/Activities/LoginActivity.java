package in.silive.scrolls16.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import in.silive.scrolls16.Fragments.TopicsFragment;
import in.silive.scrolls16.Fragments.UploadDoc;
import in.silive.scrolls16.R;

/**
 * Created by root on 21/9/17.
 */

public class LoginActivity extends AppCompatActivity {
    Toolbar toolbar;
    Bundle bundle;
    String title;
    WebView webView;
    private RelativeLayout imagehead;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        imagehead=(RelativeLayout)findViewById(R.id.imageHead);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        showFragment(new UploadDoc(), title);

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

        }
    }
}
