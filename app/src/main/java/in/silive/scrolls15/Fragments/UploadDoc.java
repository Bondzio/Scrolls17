package in.silive.scrolls15.Fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nononsenseapps.filepicker.FilePickerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;

import in.silive.scrolls15.Activities.MyPickerActivity;
import in.silive.scrolls15.Activities.SecondActivity;
import in.silive.scrolls15.Listeners.FetchDataListener;
import in.silive.scrolls15.Network.CheckConnectivity;
import in.silive.scrolls15.Network.FetchData;
import in.silive.scrolls15.R;
import in.silive.scrolls15.Util.Config;
import in.silive.scrolls15.Util.Dialogs;
import in.silive.scrolls15.Util.Keyboard;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadDoc extends Fragment {
    private static final int FILE_CODE = 1423;
    private static final int TEAM_DETAILS = 1323;
    public static String team_id;
    public static Context context;
    static int domainId;
    static int topicId;
    static String filePath;
    //UI elements
    LinearLayout upload;
    EditText login_team_id, login_password;
    Button user_login, user_register;
    View v;
    private Button btnSelect;
    private String topicName;
    private String domainName;
    TextView tvTopic,tvTeamID,tvDomain;

    public UploadDoc() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_upload_doc, container, false);
        context = getContext();
        upload = (LinearLayout) v.findViewById(R.id.upload);
        login_team_id = (EditText) v.findViewById(R.id.login_team_id);
        login_password = (EditText) v.findViewById(R.id.login_password);
        user_login = (Button) v.findViewById(R.id.user_login);
        user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Keyboard.close(getContext());
                getLoginData();
            }
        });
        user_register = (Button) v.findViewById(R.id.user_register);
        user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Keyboard.close(getContext());

                Intent i = new Intent(getContext(),SecondActivity.class);
                        i.putExtra(Config.KEY_FRAGMENT,Config.KEY_REGISTER);
                        startActivity(i);

            }
        });
        btnSelect = (Button) v.findViewById(R.id.btnSelect);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), MyPickerActivity.class);
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, false);
                i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);
                i.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());
                startActivityForResult(i, FILE_CODE);
            }
        });
        tvDomain = (TextView)v.findViewById(R.id.tvDomain);
        tvTopic = (TextView)v.findViewById(R.id.tvTopic);
        tvTeamID = (TextView)v.findViewById(R.id.tvTeamId);
        if (TextUtils.isEmpty(domainName)) {
            v.findViewById(R.id.llForm).setVisibility(View.VISIBLE);
            v.findViewById(R.id.llUpload).setVisibility(View.GONE);
        } else {
            v.findViewById(R.id.llForm).setVisibility(View.GONE);
            v.findViewById(R.id.llUpload).setVisibility(View.VISIBLE);
        }
        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILE_CODE && resultCode == Activity.RESULT_OK) {
            try {
                Log.d("Scrolls", "File " + data.getData().getPath());
                JSONObject jsonObject = new JSONObject();
                File file = new File(data.getData().getPath());
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = new byte[(int) file.length()];
                fileInputStream.read(bytes);
                fileInputStream.close();
                if (TextUtils.isEmpty(domainName)|| TextUtils.isEmpty(topicName)){
                    v.findViewById(R.id.llForm).setVisibility(View.VISIBLE);
                    v.findViewById(R.id.llUpload).setVisibility(View.GONE);
                    Snackbar.make(v,"Login first.",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                jsonObject.put("TeamId",Integer.parseInt(team_id));
                jsonObject.put("DomainName",domainName);
                jsonObject.put("TopicName",topicName);
                jsonObject.put("FileName",file.getName());
                jsonObject.put("FileArray", Base64.encodeToString(bytes,Base64.DEFAULT));
                if (CheckConnectivity.isNetConnected(getContext())) {
                    Dialogs.showUploadDialog(getContext(), jsonObject.toString(), file.getName(), new Dialogs.UploadListener() {
                        @Override
                        public void onUploadSuccessful() {
                            v.findViewById(R.id.tvSynopsis).setVisibility(View.VISIBLE);
                            v.findViewById(R.id.btnSelect).setVisibility(View.GONE);
                        }
                    });
                }
                else
                    Snackbar.make(v,"No internet connection.",Snackbar.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Snackbar.make(v, "Something went wrong.", Snackbar.LENGTH_SHORT).show();
            }
        }

    }

    public void getLoginData() {
        team_id = login_team_id.getText().toString();
        Log.d("Scrolls", team_id + " team_id from upload a doc login");
        if (team_id.length() <= 3) {
            login_team_id.setError("Invalid ID");
            /*DialogInvalidDetails dialogInvalidDetails = new DialogInvalidDetails();
            dialogInvalidDetails.show(getFragmentManager(), "Invalid id");*/
            Snackbar.make(v, "Team ID is incorrect.", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(login_password.getText().toString())) {
            login_password.setError("Empty");
            Snackbar.make(v, "Password is empty", Snackbar.LENGTH_SHORT).show();
        } else {
            if (!CheckConnectivity.isNetConnected(getContext()))
                Snackbar.make(v, "No internet connection.", Snackbar.LENGTH_SHORT).show();
            else {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("TeamId", team_id);
                    jsonObject.put("Password", login_password.getText().toString());
                } catch (Exception e) {

                }

                FetchData fetchData = new FetchData();
                fetchData.setArgs(Config.Team_LOGIN, jsonObject.toString(), new FetchDataListener() {
                    ProgressDialog progressDialog;

                    @Override
                    public void preExecute() {
                        progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage("Loading");
                        progressDialog.setCancelable(false);
                        if (!getActivity().isFinishing())
                            progressDialog.show();
                    }

                    @Override
                    public void postExecute(String result, int id) throws JSONException {
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        try {
                            JSONObject team = new JSONObject(result);
                            team_id = team.getString("TeamId");
                            topicName = team.getString("TopicName");
                            domainName = team.getString("DomainName");
                            tvTeamID.setText(team_id);
                            tvDomain.setText(domainName);
                            tvTopic.setText(topicName);
                            getSynopsisAvail();
                            v.findViewById(R.id.llForm).setVisibility(View.GONE);
                            v.findViewById(R.id.llUpload).setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            if (result.equalsIgnoreCase("null")) {
                                android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(context)
                                        .setTitle("Error")
                                        .setMessage("Invalid Credentials")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })

                                        .setIcon(android.R.drawable.ic_dialog_alert);
                                dialog.show();

                            } else {
                                Snackbar.make(v, "Something went wrong.", Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                fetchData.execute();
            }
        }
    }

    private void getSynopsisAvail() {
        FetchData fetchData = new FetchData();
        fetchData.setArgs(Config.GET_TEAM_DETAILS + team_id, new FetchDataListener() {
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
                try {
                    JSONObject team = new JSONObject(result);
                        boolean synopsisAvail = team.getBoolean("SynopsisAvailable");
                    v.findViewById(R.id.llForm).setVisibility(View.GONE);
                    v.findViewById(R.id.llUpload).setVisibility(View.VISIBLE);
                  if (!synopsisAvail) {
                      v.findViewById(R.id.tvSynopsis).setVisibility(View.GONE);
                      v.findViewById(R.id.btnSelect).setVisibility(View.VISIBLE);
                  }else {
                      v.findViewById(R.id.tvSynopsis).setVisibility(View.VISIBLE);
                      v.findViewById(R.id.btnSelect).setVisibility(View.GONE);
                  }
                } catch (Exception e) {
                    if (result.equalsIgnoreCase("null")) {
                        android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(context)
                                .setTitle("Error")
                                .setMessage("Invalid Credentials")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })

                                .setIcon(android.R.drawable.ic_dialog_alert);
                        dialog.show();

                    } else {
                        Snackbar.make(v, "Something went wrong.", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        });
        fetchData.execute();
    }

}
