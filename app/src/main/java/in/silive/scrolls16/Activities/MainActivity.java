package in.silive.scrolls16.Activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
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

import java.util.Calendar;

import in.silive.scrolls16.Adapters.PagerAdapter;
import in.silive.scrolls16.Fragments.ScrollsDevelopers;
import in.silive.scrolls16.Fragments.ScrollsTeam;
import in.silive.scrolls16.R;
import in.silive.scrolls16.Util.Config;
import in.silive.scrolls16.Util.Keyboard;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Context main_act_context;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    boolean drawerVisible = false;
    LinearLayout ll;
    TabLayout tabLayout;
    String title;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
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
        viewPager = (ViewPager) findViewById(R.id.viewPager);
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
        pagerAdapter = new in.silive.scrolls16.Adapters.PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.bg_dark_gray);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.white);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );
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
        viewPager.setCurrentItem(2, true);
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
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1234);
                }
                BottomSheetDialogFragment bottomSheetDialogFragmentTeam = new ScrollsTeam();
                bottomSheetDialogFragmentTeam.show(fragmentManager, "Team");
                return true;
            case R.id.devTeam:
                BottomSheetDialogFragment bottomSheetDialogFragment = new ScrollsDevelopers();
                bottomSheetDialogFragment.show(fragmentManager, "Developers");
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
        Intent i = new Intent(this, SecondActivity.class);
        Calendar calendar = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        switch (menuItem.getItemId()) {
            case R.id.register:
                cal2.set(2018,9,5,0,0);
                if (cal2.getTimeInMillis() > calendar.getTimeInMillis()) {
                    i.putExtra(Config.KEY_FRAGMENT, Config.KEY_REGISTER);
                    startActivity(i);
                } else showRegOverDialog();
                break;
            case R.id.upload:
                cal2.set(2018,9,5,0,0);
                if (cal2.getTimeInMillis() > calendar.getTimeInMillis()) {
                    i.putExtra(Config.KEY_FRAGMENT, Config.KEY_UPLOAD);
                    startActivity(i);
                } else showRegOverDialog();
                break;
            case R.id.query:
                cal2.set(2018,9,22,0,0);

                if (cal2.getTimeInMillis() > calendar.getTimeInMillis()) {
                    i.putExtra(Config.KEY_FRAGMENT, Config.KEY_QUERY);
                    startActivity(i);
                }else showQueryOverDialog();
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

    private void showQueryOverDialog() {
        android.support.v7.app.AlertDialog.Builder notifyDialog = new android.support.v7.app.AlertDialog.Builder(this);
        notifyDialog.setTitle("Scrolls 2016");

        notifyDialog.setMessage("Scrolls 2016 has been concluded.\n" +
                "Meet you next year.");
        notifyDialog.setPositiveButton( "Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        notifyDialog.show();
    }

    private void showRegOverDialog() {
        android.support.v7.app.AlertDialog.Builder notifyDialog = new android.support.v7.app.AlertDialog.Builder(this);
        notifyDialog.setTitle("Scrolls 2016");

            notifyDialog.setMessage("Last date for Registration and Synopsis upload is over");
        notifyDialog.setPositiveButton( "Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        notifyDialog.show();
    }

}

