package in.silive.scrolls16.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.stepstone.stepper.StepperLayout;

import in.silive.scrolls16.Adapters.MyStepperAdapter;
import in.silive.scrolls16.R;

/**
 * Created by root on 12/9/17.
 */

public class RegisterActivity extends AppCompatActivity {
    private StepperLayout mStepperLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stepper);
        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mStepperLayout.setAdapter(new MyStepperAdapter(getSupportFragmentManager(), this));
    }
}
