package in.silive.scrolls16.Activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;

/**
 * Created by root on 5/9/17.
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class DetailsTransition extends TransitionSet {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DetailsTransition() {
        setOrdering(ORDERING_TOGETHER);
        addTransition(new ChangeBounds()).
                addTransition(new ChangeTransform()).
                addTransition(new ChangeImageTransform());
                addTransition(new CircularReveal());
                addTransition(new ChangeClipBounds());
    }
}