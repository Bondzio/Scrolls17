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

import in.silive.scrolls.Fragments.About_Scrolls;
import in.silive.scrolls.Fragments.About_Us;
import in.silive.scrolls.Fragments.NavigationDrawer;
import in.silive.scrolls.Fragments.QueryUs;
import in.silive.scrolls.Fragments.ReachUs;
import in.silive.scrolls.Fragments.Register;
import in.silive.scrolls.Fragments.Rules;
import in.silive.scrolls.Fragments.ScheduleFragment;
import in.silive.scrolls.Fragments.UploadDoc;
import in.silive.scrolls.R;
import in.silive.scrolls.Util.Dialogs;
import io.codetail.animation.ViewAnimationUtils;

public class MainActivity extends AppCompatActivity implements NavigationDrawer.NavigationDrawerListener {
    NavigationDrawer navigationDrawer;
    Context main_act_context;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    boolean drawerVisible = false;
    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        main_act_context = getApplicationContext();
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
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
                title = "Scrolls'16";
                break;
            case 2:
                if (!(fragment instanceof ScheduleFragment))
                    fragment = new ScheduleFragment();
                title = "Scrolls'16";
                break;
            case 3:
                if (!(fragment instanceof Register))
                    fragment = new Register();
                title = "Scrolls'16";
                break;
            case 4:
                if (!(fragment instanceof UploadDoc))
                    fragment = new UploadDoc();
                title = "Scrolls'16";
                break;
            case 5:
                if (!(fragment instanceof QueryUs))
                    fragment = new QueryUs();
                title = "Scrolls'16";
                break;
            case 6:
              /*  if(! (fragment instanceof  ReachUs))*/
                fragment = ReachUs.getInstance();
                title = "Scrolls'16";
                break;
            case 7:
                Dialogs.showForgotIdDialog(this);
                break;
            case 8:
                if (!(fragment instanceof About_Us))
                    fragment = new About_Us();
                title = "Scrolls'16";
                break;

            default:
                break;
        }
        if (fragment.isAdded()) {
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
        final View myView = findViewById(R.id.drawerLL);
        // get the center for the clipping circle
        int cx = 0;
        int cy = 0;

        // get the final radius for the clipping circle
        int dx = Math.max(cx, myView.getWidth());
        int dy = Math.max(cy, myView.getHeight());
        float initialRadius = (float) Math.hypot(dx, dy);

        // Android native animator
        Animator animator =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(650);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                myView.setVisibility(View.GONE);
                drawerVisible=false;
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
        View myView = findViewById(R.id.drawerLL);
        // get the center for the clipping circle
        int cx = 0;
        int cy = 0;

        // get the final radius for the clipping circle
        int dx = Math.max(cx, myView.getWidth() - cx);
        int dy = Math.max(cy, myView.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        // Android native animator
        Animator animator =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(650);
        myView.setVisibility(View.VISIBLE);
        animator.start();
        drawerVisible=true;
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {

    }
}
