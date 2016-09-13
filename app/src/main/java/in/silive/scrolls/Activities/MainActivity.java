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
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import in.silive.scrolls.Adapters.PagerAdapter;
import in.silive.scrolls.Fragments.About_Scrolls;
import in.silive.scrolls.Fragments.ReachUs;
import in.silive.scrolls.Fragments.Rules;
import in.silive.scrolls.Fragments.ScheduleFragment;
import in.silive.scrolls.Fragments.TopicsFragment;
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
    ViewPager viewPager;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        main_act_context = getApplicationContext();
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager)findViewById(R.id.viewPager) ;
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

        setUpViewPager();
    }

    private void setUpViewPager() {
        pagerAdapter = new in.silive.scrolls.Adapters.PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String title = " ";
                switch (position) {
                    case 0:
                        title = "Rules";
                        break;
                    case 1:
                        title = "Important Dates";
                        break;
                    case 2:
                        title = "About Scrolls";
                        break;
                    case 3:
                        title = "Topics";
                        break;
                    case 4:
                        title = "Reach Us";
                        break;
                }
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pagerAdapter.addIconsToTab(tabLayout);
        viewPager.setCurrentItem(2,true);
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
        Intent i = new Intent(this,SecondActivity.class);
        switch (menuItem.getItemId()) {
            case R.id.register:
               i.putExtra(Config.KEY_FRAGMENT,Config.KEY_REGISTER);
                startActivity(i);
                break;
            case R.id.upload:
                i.putExtra(Config.KEY_FRAGMENT,Config.KEY_UPLOAD);
                startActivity(i);
                break;
            case R.id.query :
                i.putExtra(Config.KEY_FRAGMENT,Config.KEY_QUERY);
                startActivity(i);
                break;
            case R.id.download:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String url = Config.SAMPLE_DOC_URL;
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                break;
            default:
        }


        return true;
    }

}
