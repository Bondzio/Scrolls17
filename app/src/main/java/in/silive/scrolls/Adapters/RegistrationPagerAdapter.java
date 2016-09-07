package in.silive.scrolls.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.silive.scrolls.Fragments.Register;
import in.silive.scrolls.Fragments.SingleRegistration;
import in.silive.scrolls.Fragments.TeamRegistration;

/**
 * Created by AKG002 on 06-09-2016.
 */
public class RegistrationPagerAdapter extends FragmentPagerAdapter {


    public RegistrationPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag=null;
        switch (position){
            case 0:
                frag=new SingleRegistration();
                break;
            case 1:
                frag=new TeamRegistration();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=" ";
        switch (position){
            case 0:
                title="Individual";
                break;
            case 1:
                title="Team";
                break;
        }

        return title;
    }
}
