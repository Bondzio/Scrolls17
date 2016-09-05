package in.silive.scrolls.Activities;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import in.silive.scrolls.Fragments.About_Scrolls;
import in.silive.scrolls.Fragments.About_Us;
import in.silive.scrolls.Fragments.DialogNoNetConnection;
import in.silive.scrolls.Fragments.NavigationDrawer;
import in.silive.scrolls.Fragments.QueryUs;
import in.silive.scrolls.Fragments.ReachUs;
import in.silive.scrolls.Fragments.Register;
import in.silive.scrolls.Fragments.Rules;
import in.silive.scrolls.Fragments.ScheduleFragment;
import in.silive.scrolls.Fragments.UploadDoc;
import in.silive.scrolls.Network.CheckConnectivity;
import in.silive.scrolls.R;
import in.silive.scrolls.Util.Dialogs;
import io.codetail.animation.ViewAnimationUtils;

public class MainActivity extends AppCompatActivity implements NavigationDrawer.NavigationDrawerListener {
    NavigationDrawer navigationDrawer;
    Context main_act_context;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    boolean drawerVisible = false;
    LinearLayout ll;
    private Toolbar mtoolbar;

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
        //  getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_up_indicator);

        navigationDrawer = (NavigationDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        //  navigationDrawer.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mtoolbar);
        navigationDrawer.setDrawerListener(new NavigationDrawer.NavigationDrawerListener() {
            @Override
            public void onDrawerItemSelected(View view, int position) {
                displayView(position);
                if (drawerVisible)
                    hideDrawer();
            }
        });
        ll = (LinearLayout) findViewById(R.id.ll);
        displayView(0);
    }

    private void displayView(int position) {
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                if (!(fragment instanceof About_Scrolls))
                    fragment = new About_Scrolls();
                title = "Scrolls'16";
                break;
            case 1:
                if (!(fragment instanceof Rules))
                    fragment = new Rules();
                title = "Rules";
                break;
            case 2:
                if (!(fragment instanceof ScheduleFragment))
                    fragment = new ScheduleFragment();
                title = "Schedule";
                break;
            case 3:
                if (!(fragment instanceof Register))

                    if (CheckConnectivity.isNetConnected(MainActivity.this)) {
                        fragment = new Register();
                        title = "Register";
                    } else {
                        DialogNoNetConnection dialogNoNetConnection = new DialogNoNetConnection();
                        dialogNoNetConnection.show(getSupportFragmentManager(), "No net connection");
                    }

                break;
            case 4:
                if (!(fragment instanceof UploadDoc))
                    fragment = new UploadDoc();
                title = "Upload";
                break;
            case 5:
                if (!(fragment instanceof QueryUs))
                    fragment = new QueryUs();
                title = "Query Us";
                break;
            case 6:
              /*  if(! (fragment instanceof  ReachUs))*/
                fragment = ReachUs.getInstance();
                title = "Reach Us";
                break;
            case 7:
                Dialogs.showForgotIdDialog(this);
                break;
            case 8:
                if (!(fragment instanceof About_Us))
                    fragment = new About_Us();
                title = "About Us";
                break;

            default:
                break;
        }
        if (fragment != null && fragment.isAdded()) {
            fragment = fragmentManager.findFragmentByTag(fragment.getClass().getName());
        }
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment).addToBackStack(fragment.getClass().getName());
            fragmentTransaction.commit();
            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            if (drawerVisible)
                hideDrawer();
            else
                showDrawer();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void hideDrawer() {
        // get the center for the clipping circle
        int cx = 0;
        int cy = 0;

        // get the final radius for the clipping circle
        int dx = Math.max(cx, ll.getWidth());
        int dy = Math.max(cy, ll.getHeight());
        float initialRadius = (float) Math.hypot(dx, dy);

        // Android native animator
        Animator animator =
                ViewAnimationUtils.createCircularReveal(ll, cx, cy, initialRadius, 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(650);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ll.setVisibility(View.GONE);
                drawerVisible = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        animator.start();
    }

    public void showDrawer() {
        ll.setVisibility(View.INVISIBLE);
        ll.post(new Runnable() {
            @Override
            public void run() {
                int cx = 0;
                int cy = 0;

                // get the final radius for the clipping circle
                int dx = Math.max(cx, ll.getWidth() - cx);
                int dy = Math.max(cy, ll.getHeight() - cy);
                float finalRadius = (float) Math.hypot(dx, dy);

                // Android native animator
                Animator animator =
                        ViewAnimationUtils.createCircularReveal(ll, cx, cy, 0, finalRadius);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(650);
                ll.setVisibility(View.VISIBLE);
                animator.start();
                drawerVisible = true;
            }
        });

    }

    @Override
    public void onDrawerItemSelected(View view, int position) {

    }
}
