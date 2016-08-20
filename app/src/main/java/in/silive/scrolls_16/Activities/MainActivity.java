package in.silive.scrolls_16.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import in.silive.scrolls_16.Fragments.NavigationDrawer;
import in.silive.scrolls_16.R;

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

            }
        });
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {

    }
}
