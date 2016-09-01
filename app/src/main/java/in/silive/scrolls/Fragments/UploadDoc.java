package in.silive.scrolls.Fragments;


import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import in.silive.scrolls.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadDoc extends Fragment {
    Button upload_doc;
    int serverResponseCode = 0;
    ProgressDialog progressDialog = null;
    final String uploadFilePath = "/mnt/sdcard/";
    final String uploadFileName = "service_lifecycle.png";
    Uri upLoadServerUri;


    public UploadDoc() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_upload_doc, container, false);
        upload_doc = (Button)v.findViewById(R.id.upload_doc);
        //upLoadServerUri = "http://www.androidexample.com/media/UploadToServer.php";
        upload_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return v;
    }

}
