package in.silive.scrolls16.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Color;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

import in.silive.scrolls16.Adapters.PagerAdapter;
import in.silive.scrolls16.Fragments.About_Scrolls;
import in.silive.scrolls16.Fragments.Register;
import in.silive.scrolls16.Fragments.ScrollsDevelopers;
import in.silive.scrolls16.Fragments.ScrollsTeam;
import in.silive.scrolls16.Fragments.UploadDoc;
import in.silive.scrolls16.Network.ApiClient;
import in.silive.scrolls16.Network.CheckConnectivity;
import in.silive.scrolls16.Network.RetrofitApiInterface;
import in.silive.scrolls16.R;
import in.silive.scrolls16.Util.Config;
import in.silive.scrolls16.Util.Keyboard;
import in.silive.scrolls16.application.Scrolls;
import in.silive.scrolls16.models.LoginSucess;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Context main_act_context;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    BroadcastReceiver mRegistrationBroadcastReceiver;
    boolean drawerVisible = false;
    LinearLayout ll;
    TabLayout tabLayout;
    String title;
    RelativeLayout rel;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    private Toolbar mtoolbar;
    private FragmentTransaction fragmentTransaction;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;
    private SharedPreferences sharedpreferences;
    private RetrofitApiInterface apiService;
    private Call<LoginSucess> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        main_act_context = getApplicationContext();
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        rel=(RelativeLayout)findViewById(R.id.relfab);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setSupportActionBar(mtoolbar);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            //    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedpreferences = Scrolls.getInstance().sharedPrefs;
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black);
        //  getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_up_indicator);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        View headerView = navigationView.inflateHeaderView(R.layout.parallax_header);
        
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);
        materialDesignFAM.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {

                if(opened)
                {
                    rel.setBackgroundColor(getResources().getColor(R.color.fabback));
                }
                else
                {
                    rel.setBackgroundColor(0);
                }
            }
        });
       floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              /*  fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                fragment = new Register();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
              Intent i=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);


            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                fragment = new UploadDoc();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              /*  Intent intent = new Intent(Intent.ACTION_VIEW);
                String url = Config.SAMPLE_DOC_URL;
                intent.setData(Uri.parse(url));
                startActivity(intent);*/
                CopyAssets();


            }
        });
        apiService =
                ApiClient.getClient().create(RetrofitApiInterface.class);

        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragment = new About_Scrolls();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        //tabLayout = (TabLayout) findViewById(R.id.tabLayout);
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
     //checkFcm();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //setUpViewPager();
    }

    private void setUpViewPager() {
        pagerAdapter = new in.silive.scrolls16.Adapters.PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(5);
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
        return false;
    }
  public void checkFcm()
  { String firebase_id_send_to_server_or_not = sharedpreferences.getString(Config.FIREBASE_ID_SENT, "");
      if(firebase_id_send_to_server_or_not.equals("0"))
      {  String Firebase_token = sharedpreferences.getString("regId", "");
      call = apiService.Fcm(Firebase_token);
      if (CheckConnectivity.isNetConnected(getApplicationContext())) {
          final ProgressDialog loading = ProgressDialog.show(getApplicationContext(), "Fetching Data", "Please wait...", false, false);
          call.enqueue(new Callback<LoginSucess>() {
              @Override
              public void onResponse(Call<LoginSucess> call, Response<LoginSucess> response) {
                  if (response.isSuccessful()) {


                      SharedPreferences.Editor editor = sharedpreferences.edit();
                      editor.putString("FirebaseIdSendToServer", "1");//1 means firebase id is registered
                      editor.apply();

                      loading.dismiss();

                      //Log.d("debugg",Integer.toString(topicsList1.size()));


                  }
              }

              @Override
              public void onFailure(Call<LoginSucess> call, Throwable t) {
                  loading.dismiss();
              }


          });
      }
      }

      mRegistrationBroadcastReceiver = new BroadcastReceiver() {
          @Override
          public void onReceive(Context context, Intent intent) {
              // checking for type intent filter
              if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                  // fcm successfully registered
                  // now subscribe to `global` topic to receive app wide notifications
                  FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
              } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                  // new push notification is received
                  String message = intent.getStringExtra("message");
                  Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
              }
          }
      };
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
        Intent in = new Intent(Intent.ACTION_VIEW);
        String url;

        if (menuItem.isChecked()) menuItem.setChecked(false);
        else menuItem.setChecked(true);
        drawerLayout.closeDrawers();
        Intent i = new Intent(this, SecondActivity.class);
        Calendar calendar = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        switch (menuItem.getItemId()) {

            case R.id.topics:
                cal2.set(2018,9,5,0,0);
                if (cal2.getTimeInMillis() > calendar.getTimeInMillis()) {
                    i.putExtra(Config.KEY_FRAGMENT, Config.KEY_Topics);
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
            /*case R.id.download:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String url = Config.SAMPLE_DOC_URL;
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
                */
            case R.id.rules:
                i.putExtra(Config.KEY_FRAGMENT,Config.KEY_SCROLLSRULE);
                startActivity(i);
                break;
            case R.id.reachus:
                i.putExtra(Config.KEY_FRAGMENT,Config.KEY_REACHUS);
                startActivity(i);
                break;
            case R.id.importantdates:
                i.putExtra(Config.KEY_FRAGMENT,Config.KEY_ImpDates);
                startActivity(i);
                break;
            case R.id.scrollsweb:
                url = Config.SCROLLS_WEBSITE;
                in.setData(Uri.parse(url));
                startActivity(in);
                return true;
            case R.id.scrollssi:
                url = Config.SILIVE_WEBSITE;
                in.setData(Uri.parse(url));
                startActivity(in);
                return true;


            case R.id.scrollTeam:
                i.putExtra(Config.KEY_FRAGMENT,Config.KEY_SCROLLSTEAM);
                startActivity(i);
                break;
            case R.id.scrollDeveloper:
                i.putExtra(Config.KEY_FRAGMENT,Config.KEY_SCROLLSDeveloper);
                startActivity(i);

            default:
        }


        return true;
    }
    //Check Permissions
    public void CopyAssets() {

        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;
        File file = new File(getFilesDir(), "synopsis.pdf");
        try {
            in = assetManager.open("synopsis.pdf");
            out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(
                Uri.parse("file://" + getFilesDir() + "/synopsis.pdf"),
                "application/pdf");

        startActivity(intent);
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
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

