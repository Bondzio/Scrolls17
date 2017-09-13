package in.silive.scrolls16.Adapters;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;

import in.silive.scrolls16.Fragments.MemberRegister;
import in.silive.scrolls16.Fragments.TeamRegister;

/**
 * Created by root on 12/9/17.
 */

public class MyStepperAdapter extends AbstractFragmentStepAdapter {
    private Fragment fragment;

    public MyStepperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(@IntRange(from = 0L) int position) {
        switch (position)
        {
            case 0:fragment= new TeamRegister();
            break;
            case 1:fragment= new MemberRegister();
            break;
            case 2:fragment= new MemberRegister();
            break;
            case 3:fragment= new MemberRegister();
                break;
        }
        return (Step) fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
