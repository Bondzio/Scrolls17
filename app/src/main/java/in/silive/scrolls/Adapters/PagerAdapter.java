package in.silive.scrolls.Adapters;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import in.silive.scrolls.Fragments.About_Scrolls;
import in.silive.scrolls.Fragments.ReachUs;
import in.silive.scrolls.Fragments.Rules;
import in.silive.scrolls.Fragments.ScheduleFragment;
import in.silive.scrolls.Fragments.TopicsFragment;
import in.silive.scrolls.R;

/**
 * Created by AKG002 on 13-09-2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {

            case 0:
                fragment = new Rules();

                break;
            case 1:

                fragment = new ScheduleFragment();

                break;
            case 4:

                fragment = new ReachUs();

                break;
            case 2:
                fragment = new About_Scrolls();
                break;
            case 3:

                fragment = new TopicsFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = " ";
        switch (position) {
            case 0:
                title = "Rules";
                break;
            case 1:
                title = "Dates";
                break;
            case 2:
                title = "About";
                break;
            case 3:
                title = "Topics";
                break;
            case 4:
                title = "Reach Us";
                break;
        }

        return title;
    }

    public void addIconsToTab(TabLayout tabLayout){
        for (int i=0;i<getCount();i++){
            tabLayout.getTabAt(i).setIcon(getTabIcon(i));
        }
    }
    public int getTabIcon(int pos){
    switch (pos){
        case 0: return R.drawable.rules;
        case 1: return R.drawable.dates_icon;
        case 2: return R.drawable.about_us;
        case 3: return R.drawable.query;
        case 4: return R.drawable.reach_icon;
    }
        return 0;
    }
}
