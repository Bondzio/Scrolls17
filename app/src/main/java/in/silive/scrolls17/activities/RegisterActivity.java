package in.silive.scrolls17.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.stepstone.stepper.StepperLayout;

import in.silive.scrolls17.adapters.MyStepperAdapter;
import in.silive.scrolls17.R;

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        finish();
    }

}
