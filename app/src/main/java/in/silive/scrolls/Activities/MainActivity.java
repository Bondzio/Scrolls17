package in.silive.scrolls.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import in.silive.scrolls.Fragments.NavigationDrawer;
import in.silive.scrolls.Fragments.ScheduleFragment;
import in.silive.scrolls.R;

public class MainActivity extends AppCompatActivity implements NavigationDrawer.NavigationDrawerListener {
    private Toolbar mtoolbar;
    NavigationDrawer navigationDrawer;
    Context main_act_context;
    FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_act_context = getApplicationContext();
        mtoolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentContainer = (FrameLayout)findViewById(R.id.container_body);
        navigationDrawer = (NavigationDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        navigationDrawer.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mtoolbar);
        navigationDrawer.setDrawerListener(new NavigationDrawer.NavigationDrawerListener() {
            @Override
            public void onDrawerItemSelected(View view, int position) {
                setFragment(position);
            }
        });

        setFragment(0);
    }

    private void setFragment(int position) {
        Fragment newFragment=null;
        switch(position){
            case 0:
                if (!(getSupportFragmentManager().findFragmentById(R.id.container_body) instanceof ScheduleFragment))
                newFragment = new ScheduleFragment();
                break;
            case 1: showForgotIdDialog();
                break;
        }
        if (newFragment!=null ){
            getSupportFragmentManager().beginTransaction().add(R.id.container_body,newFragment).addToBackStack("TAG").commit();
        }
    }

    private void showForgotIdDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Forgot Scrolls ID");
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {

    }
}
