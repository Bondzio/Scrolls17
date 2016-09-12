package in.silive.scrolls.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import in.silive.scrolls.Fragments.About_Scrolls;
import in.silive.scrolls.Fragments.QueryUs;
import in.silive.scrolls.Fragments.Register;
import in.silive.scrolls.Fragments.Rules;
import in.silive.scrolls.Fragments.ScheduleFragment;
import in.silive.scrolls.Fragments.TopicsFragment;
import in.silive.scrolls.Fragments.UploadDoc;
import in.silive.scrolls.R;
import in.silive.scrolls.Util.Config;
import in.silive.scrolls.Util.Keyboard;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Context main_act_context;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    boolean drawerVisible = false;
    LinearLayout ll;
    TabLayout tabLayout;
    String title;
    private Toolbar mtoolbar;
    private FragmentTransaction fragmentTransaction;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        main_act_context = getApplicationContext();
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black);
        //  getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_up_indicator);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        // Initializing Drawer Layout and ActionBarToggle
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mtoolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        setUpTabs();
    }

    private void setUpTabs() {
        TabLayout.Tab first = tabLayout.newTab().setText("Rules").setIcon(R.drawable.rules);
        tabLayout.addTab(first, 0);

        tabLayout.addTab(tabLayout.newTab().setText("Dates").setIcon(R.drawable.dates_icon), 1);
        TabLayout.Tab about = tabLayout.newTab().setText("About").setIcon(R.drawable.about_us);
        tabLayout.addTab(about, 2);
        tabLayout.addTab(tabLayout.newTab().setText("Topic").setIcon(R.drawable.rules), 3);
        tabLayout.addTab(tabLayout.newTab().setText("Contact Us").setIcon(R.drawable.query), 4);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {

                    case 0:
                        if (!(fragment instanceof Rules))
                            fragment = new Rules();
                        title = "Rules";
                        break;
                    case 1:
                        if (!(fragment instanceof ScheduleFragment))
                            fragment = new ScheduleFragment();
                        title = "Important Dates";
                        break;
                    case 4:
                        if (!(fragment instanceof QueryUs))
                            fragment = new QueryUs();
                        title = "Contact Us";
                        break;
                    case 2:
                        if (!(fragment instanceof About_Scrolls))
                            fragment = new About_Scrolls();
                        title = "About";
                        break;
                    case 3:
                        if (!(fragment instanceof TopicsFragment))
                            fragment = new TopicsFragment();
                        title = "Topics";
                        break;
                }
                showFragment(fragment, title);


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {

                    case 0:
                        if (!(fragment instanceof Rules))
                            fragment = Rules.getInstance();
                        title = "Rules";
                        break;
                    case 1:
                        if (!(fragment instanceof ScheduleFragment))
                            fragment = ScheduleFragment.getInstance();
                        title = "Important Dates";
                        break;
                    case 4:
                        if (!(fragment instanceof QueryUs))
                            fragment = new QueryUs();
                        title = "Contact Us";
                        break;
                    case 2:
                        if (!(fragment instanceof About_Scrolls))
                            fragment = About_Scrolls.newInstance();
                        title = "About";
                        break;
                    case 3:
                        if (!(fragment instanceof TopicsFragment))
                            fragment = TopicsFragment.getInstance();
                        title = "Topics";
                        break;
                }
                showFragment(fragment, title);


            }
        });
        about.select();

    }


    public void showFragment(Fragment fragment, String title) {
        if (fragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment)/*.addToBackStack(fragment.getClass().getName())*/;
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Keyboard.close(this);
        Intent i = new Intent(Intent.ACTION_VIEW);
        String url;

        switch (item.getItemId()) {
            case R.id.website:
                url = Config.SCROLLS_WEBSITE;
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
            case R.id.scrollTeam:
                return true;
            case R.id.devTeam:
                return true;
            case R.id.silive:
                url = Config.SILIVE_WEBSITE;
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Keyboard.close(this);

        if (menuItem.isChecked()) menuItem.setChecked(false);
        else menuItem.setChecked(true);
        drawerLayout.closeDrawers();
        switch (menuItem.getItemId()) {
            case R.id.register:
                showFragment(Register.getInstance(), "Register");
                break;
            case R.id.upload:
                showFragment(new UploadDoc(), "Upload");
                break;
            default:
        }
        return true;
    }

}
