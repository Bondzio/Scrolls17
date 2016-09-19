package in.silive.scrolls16.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.nononsenseapps.filepicker.AbstractFilePickerActivity;
import com.nononsenseapps.filepicker.AbstractFilePickerFragment;

import java.io.File;

import in.silive.scrolls16.Fragments.FilteredFilePickerFragment;

/**
 * Created by AKG002 on 08-09-2016.
 */
public class MyPickerActivity extends  AbstractFilePickerActivity<File> {
    public final static long MAX_FILE_SIZE = 2726297;

    public MyPickerActivity() {
        super();
    }

    @Override
    protected AbstractFilePickerFragment<File> getFragment(@Nullable String startPath, int mode, boolean allowMultiple, boolean allowCreateDir, boolean allowExistingFile, boolean singleClick) {
        FilteredFilePickerFragment fragment = new FilteredFilePickerFragment();

        return fragment;
    }
    @Override
    public void onFilePicked(@NonNull final Uri file) {
        try {
            File file1 = new File(file.getPath());
            if (file1.isFile()&& file1.length()<=MAX_FILE_SIZE){
                Intent i = new Intent();
                i.setData(file);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
            else
                Toast.makeText(this,"File should not be greater than 2.5 MB.",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this,"Something went wrong.",Toast.LENGTH_LONG).show();
        }

    }


}