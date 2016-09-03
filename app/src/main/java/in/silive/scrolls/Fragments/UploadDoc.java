package in.silive.scrolls.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONException;

import in.silive.scrolls.Listeners.FetchDataListener;
import in.silive.scrolls.Network.FetchData;
import in.silive.scrolls.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadDoc extends Fragment {
    //UI elements
    LinearLayout upload;
    EditText login_username;
    Button user_login,user_register;

    public static String username;
    public static Context context;
    public UploadDoc() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_upload_doc, container, false);
        context = getContext();
        upload = (LinearLayout)v.findViewById(R.id.upload);
        login_username = (EditText) v.findViewById(R.id.login_username);
        user_login = (Button)v.findViewById(R.id.user_login);
        user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLoginData();
            }
        });
        user_register = (Button)v.findViewById(R.id.user_register);
        user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Register();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.commit();
            }
        });

        return v;
    }
    public void getLoginData(){
        username = login_username.getText().toString();
        Log.d("Scrolls",username+" username from upload a doc login");
        FetchData fetchData = new FetchData();
        fetchData.setArgs(in.silive.scrolls.Util.Config.ID_BY_LOGIN, new FetchDataListener() {
            ProgressDialog progressDialog;
            @Override
            public void preExecute() {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Loading");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

            @Override
            public void postExecute(String result, int id) throws JSONException {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                if (result.equalsIgnoreCase("Success")){
                    Toast.makeText(context,"Login successful",Toast.LENGTH_SHORT).show();
                    upload.setVisibility(View.VISIBLE);
                }
                else {
                    android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(context)
                            .setTitle("Error")
                            .setMessage("Your email does not exist in Database")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })

                            .setIcon(android.R.drawable.ic_dialog_alert);
                    dialog.show();
                }

            }
        });

    }

}
