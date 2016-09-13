package in.silive.scrolls.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import in.silive.scrolls.Fragments.QueryUs;
import in.silive.scrolls.Fragments.Register;
import in.silive.scrolls.Fragments.UploadDoc;
import in.silive.scrolls.R;
import in.silive.scrolls.Util.Config;

public class SecondActivity extends AppCompatActivity {
Toolbar toolbar;
    Bundle bundle;
    String title;

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
                    case Config.KEY_REGISTER:
                        showFragment(Register.getInstance(), title);
                        break;
                    case Config.KEY_UPLOAD:
                        showFragment(new UploadDoc(), title);
                        break;
                    case Config.KEY_QUERY:
                        showFragment(new QueryUs(),title);
                        break;
                }
                getSupportActionBar().setTitle(title);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
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
