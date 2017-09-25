package in.silive.scrolls17.fragments;

import android.support.annotation.NonNull;

import com.nononsenseapps.filepicker.FilePickerFragment;

import java.io.File;

/**
 * Created by AKG002 on 08-09-2016.
 */

public class FilteredFilePickerFragment extends FilePickerFragment {

    // File extension to filter on
    private static final String EXTENSION = ".pdf";

    /**
     * @param file
     * @return The file extension. If file has no extension, it returns null.
     */
    private String getExtension(@NonNull File file) {
        String path = file.getPath();
        int i = path.lastIndexOf(".");
        if (i < 0) {
            return null;
        } else {
            return path.substring(i);
        }
    }

    @Override
    protected boolean isItemVisible(final File file) {
        boolean ret = super.isItemVisible(file);
        if (ret && !isDir(file) && (mode == MODE_FILE || mode == MODE_FILE_AND_DIR)) {
            String ext = getExtension(file);
          //  Log.d("Scrolls","Ext "+ext);
            return ext != null && EXTENSION.equalsIgnoreCase(ext);
        }
        return ret;
    }
}
