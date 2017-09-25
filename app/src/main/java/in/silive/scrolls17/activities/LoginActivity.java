package in.silive.scrolls17.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import in.silive.scrolls17.fragments.UploadDoc;
import in.silive.scrolls17.R;

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
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
    public void setImagehead(Integer res)
    {
        imagehead.setBackgroundResource(res);
    }
}
