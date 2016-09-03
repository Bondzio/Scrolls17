package in.silive.scrolls.Fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.utils.Utils;
import in.silive.scrolls.R;
import in.silive.scrolls.Util.Dialogs;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadDoc extends Fragment {
    Button uploadDoc;
    String filePath;
    public UploadDoc() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_upload_doc, container, false);
        uploadDoc = (Button)v.findViewById(R.id.upload_doc);
        uploadDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilePickerBuilder.getInstance().setMaxCount(1)
                        .setSelectedFiles(null)
                        .setActivityTheme(R.style.AppTheme)
                        .pickDocument(UploadDoc.this);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case FilePickerConst.REQUEST_CODE:
                if(resultCode== Activity.RESULT_OK && data!=null)
                {
                    if (data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS).size()>0) {
                        filePath = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS).get(0);
                        Log.d("File ;", filePath);
                        Dialogs.showUploadDialog(getContext(), filePath);
                    }else {
                        FilePickerBuilder.getInstance().setMaxCount(1)
                                .setSelectedFiles(null)
                                .setActivityTheme(R.style.AppTheme)
                                .pickDocument(UploadDoc.this);
                        Toast.makeText(getContext(),"File not selected",Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }

}
