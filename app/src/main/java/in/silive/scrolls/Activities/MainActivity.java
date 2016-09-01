package in.silive.scrolls.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import org.json.JSONException;

import in.silive.scrolls.Fragments.About_Scrolls;
import in.silive.scrolls.Fragments.About_Us;
import in.silive.scrolls.Fragments.NavigationDrawer;
import in.silive.scrolls.Fragments.ScheduleFragment;
import in.silive.scrolls.Listeners.FetchDataListener;
import in.silive.scrolls.Network.FetchData;
import in.silive.scrolls.R;
import in.silive.scrolls.Util.Config;
import in.silive.scrolls.Util.Validator;

public class MainActivity extends AppCompatActivity implements NavigationDrawer.NavigationDrawerListener {
    NavigationDrawer navigationDrawer;
    Context main_act_context;
    FrameLayout fragmentContainer;
    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_act_context = getApplicationContext();
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentContainer = (FrameLayout) findViewById(R.id.container_body);
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
        Fragment fragment = null;
        switch (position) {
            case 2:
                if (!(getSupportFragmentManager().findFragmentById(R.id.container_body) instanceof ScheduleFragment))
                    fragment = new ScheduleFragment();
                break;
            case 5:
                showForgotIdDialog();
                break;
            case 0:
                fragment = new About_Scrolls();
                break;
            case 1:
                fragment = new About_Us();
                break;
            case 3:
                fragment = new About_Us();
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_body, fragment).addToBackStack("TAG").commit();
        }
    }

    private void showForgotIdDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Forgot Scrolls ID");
        View view = getLayoutInflater().inflate(R.layout.dialog_forgot_id, null);
        final EditText etEmailId = (EditText) view.findViewById(R.id.etEmailId);
        builder.setView(view);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, int i) {
                String email = etEmailId.getText().toString();
                if (Validator.isValidEmail(email)) {
                    etEmailId.setError(null);
                    FetchData fetchData = new FetchData();
                    fetchData.setArgs(Config.ID_BY_EMAIL, new FetchDataListener() {
                        @Override
                        public void preExecute() {

                        }

                        @Override
                        public void postExecute(String result, int id) throws JSONException {
                            if (result.equals("0")) {
                                dialogInterface.dismiss();
                                android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(MainActivity.this)
                                        .setTitle("Id")
                                        .setMessage("Your id is " + "")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })

                                        .setIcon(android.R.drawable.ic_dialog_alert);
                                dialog.show();

                            } else {
                                android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(MainActivity.this)
                                        .setTitle("Error")
                                        .setMessage("Your email does not exist in Database")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                               dialog.dismiss();
                                            }
                                        })

                                        .setIcon(android.R.drawable.ic_dialog_alert);
                                dialog.show();
                            }
                        }
                    });

                } else {
                    etEmailId.setError("Not valid Email");
                }
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {

    }
}
