package in.silive.scrolls.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import in.silive.scrolls.Fragments.About_Scrolls;
import in.silive.scrolls.Fragments.About_Us;
import in.silive.scrolls.Fragments.ForgotID;
import in.silive.scrolls.Fragments.NavigationDrawer;
import in.silive.scrolls.Fragments.QueryUs;
import in.silive.scrolls.Fragments.ReachUs;
import in.silive.scrolls.Fragments.Register;
import in.silive.scrolls.Fragments.Rules;
import in.silive.scrolls.Fragments.Schedule;
import in.silive.scrolls.Fragments.UploadDoc;
import in.silive.scrolls.R;

public class MainActivity extends AppCompatActivity implements NavigationDrawer.NavigationDrawerListener {
    private Toolbar mtoolbar;
    NavigationDrawer navigationDrawer;
    Context main_act_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_act_context = getApplicationContext();
        mtoolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationDrawer = (NavigationDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        navigationDrawer.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mtoolbar);
        navigationDrawer.setDrawerListener(new NavigationDrawer.NavigationDrawerListener() {
            @Override
            public void onDrawerItemSelected(View view, int position) {
                displayView(position);


            }
        });
    }
    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new About_Scrolls();
                title = "Scrolls'16";
                break;
            case 1:
                fragment = new Rules();
                title = "Scrolls'16";
                break;
            case 2:
                fragment = new Schedule();
                title = "Scrolls'16";
                break;
            case 3:
                fragment = new Register();
                title = "Scrolls'16";
                break;
            case 4:
                fragment = new UploadDoc();
                title = "Scrolls'16";
                break;
            case 5:
                fragment = new QueryUs();
                title = "Scrolls'16";
                break;
            case 6:
                fragment = new ReachUs();
                title = "Scrolls'16";
                break;
            case 7:
                fragment = new ForgotID();
                title = "Scrolls'16";
                break;
            case 8:
                fragment = new About_Us();
                title = "Scrolls'16";
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {

    }
}
