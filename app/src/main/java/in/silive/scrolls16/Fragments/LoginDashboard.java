package in.silive.scrolls16.Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nononsenseapps.filepicker.FilePickerActivity;
import com.squareup.okhttp.ResponseBody;

import java.io.File;

import in.silive.scrolls16.Activities.MainActivity;
import in.silive.scrolls16.Activities.MyPickerActivity;
import in.silive.scrolls16.Network.ApiClient;
import in.silive.scrolls16.Network.CheckConnectivity;
import in.silive.scrolls16.Network.RetrofitApiInterface;
import in.silive.scrolls16.R;
import in.silive.scrolls16.Util.Config;
import in.silive.scrolls16.application.*;
import in.silive.scrolls16.models.CheckStudentNoExsist;
import in.silive.scrolls16.models.LoginSucess;
import in.silive.scrolls16.models.LogoutSucess;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 22/9/17.
 */

public class LoginDashboard extends Fragment {
    View v;
    private RelativeLayout imagehead;
    private LinearLayout imagedash;
    private ImageView logout;
    TextView teamname,member1,member2,member3,filestatus;
    SharedPreferences sharedprefs;
    String teamName,Member1,Member2,Member3;
    String token;
    private RetrofitApiInterface apiService,service;
    public static final int PERMISSIONS_REQUEST_CODE = 0;
    private static final int FILE_CODE = 1423;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.logindashboard, container, false);
        //Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        //imagehead=(RelativeLayout) getActivity().findViewById(R.id.imageHead);
        logout=(ImageView) v.findViewById(R.id.image);
        sharedprefs= in.silive.scrolls16.application.Scrolls.getInstance().sharedPrefs;
        token=sharedprefs.getString(Config.Token,"");

        imagedash=(LinearLayout) getActivity().findViewById(R.id.linear);
        teamname=(TextView) v.findViewById(R.id.team_name);
        member1=(TextView) v.findViewById(R.id.member1);
        member2=(TextView) v.findViewById(R.id.member2);
        member3=(TextView) v.findViewById(R.id.member3);
        teamName=sharedprefs.getString(Config.LOGINT3,"");
        Member1=sharedprefs.getString(Config.LOGINM1,"");
        Member2=sharedprefs.getString(Config.LOGINM2,"");
        Member3=sharedprefs.getString(Config.LOGINM3,"");
        member1.setText(Member1);
        member2.setText(Member2);
        member3.setText(Member3);
        teamname.setText(teamName);
        filestatus=(TextView) v.findViewById(R.id.filestatus);

        apiService =
                ApiClient.getClient().create(RetrofitApiInterface.class);
        checkStatus(token);
        filestatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MyPickerActivity.class);
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, false);
                i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);
                i.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());

                startActivityForResult(i, FILE_CODE);
            }
        });
        //checkStatus(token);
        //imagehead.setVisibility(View.GONE);
       // imagedash.setVisibility(View.VISIBLE);

        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        //((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(false);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(token);

            }
        });

        return v;
    }

    private void checkStatus(String token) {
        final ProgressDialog loading = ProgressDialog.show(getContext(), "Fetching Data", "Please wait...", false, false);
        Call<CheckStudentNoExsist> call=apiService.fileUploadStatus(token);
        call.enqueue(new Callback<CheckStudentNoExsist>() {
            @Override
            public void onResponse(Call<CheckStudentNoExsist> call, Response<CheckStudentNoExsist> response) {
                if(response.code()==200)
                {filestatus.setEnabled(true);
                   // checkPermission();
                    loading.dismiss();
                }
                else if(response.code()==422)
                {loading.dismiss();
                    filestatus.setText("File Already Uploaded");
                    filestatus.setEnabled(false);
                    //Snackbar.make(v,"Check Your connection",Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CheckStudentNoExsist> call, Throwable t) {
               // Toast.makeText(getActivity(),"Error Ocuured Icon",Toast.LENGTH_LONG).show();
                loading.dismiss();
            }
        });
    }

    /*private void checkPermissionsAndOpenFilePicker() {
        String permission = Manifest.permission.READ_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
                showError();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, PERMISSIONS_REQUEST_CODE);
            }
        } else {
            Intent i = new Intent(getContext(), MyPickerActivity.class);
            i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
            i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, false);
            i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);
            i.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());
            startActivityForResult(i, FILE_CODE);
        }
    }*/
    private void showError() {
        Toast.makeText(getContext(), "Allow external storage reading", Toast.LENGTH_SHORT).show();
    }

   /* @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent i = new Intent(getContext(), MyPickerActivity.class);
                    i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
                    i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, false);
                    i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);
                    i.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());
                    startActivityForResult(i, FILE_CODE);
                } else {
                    showError();
                }
            }
        }
    }*/
    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data)
    {
        Log.d("debugg",Integer.toString(requestCode)+Integer.toString(requestCode));
        if (requestCode == FILE_CODE && resultCode == Activity.RESULT_OK) {
            try {
                Log.d("Scrolls", "File " + data.getData().getPath());

                /*JSONObject jsonObject = new JSONObject();
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

                jsonObject.put("file", Base64.encodeToString(bytes,Base64.DEFAULT));*/
                String tokenf=sharedprefs.getString(Config.Token,"");
                if (CheckConnectivity.isNetConnected(getContext())) {
                    File file = new File(data.getData().getPath());;

                    // create RequestBody instance from file
                   /* RequestBody requestFile =
                            RequestBody.create(
                                    MediaType.parse("text"),
                                    file
                            );*/

                    // MultipartBody.Part is used to send also the actual file name
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                    MultipartBody.Part multipartBody =MultipartBody.Part.createFormData("file",file.getName(),requestFile);

                    // add another part within the multipart request
                    String descriptionString = "file";
                    RequestBody description =
                            RequestBody.create(
                                    MultipartBody.FORM, descriptionString);

                    // finally, execute the request
                    final ProgressDialog loading = ProgressDialog.show(getContext(), "Fetching Data", "Please wait...", false, false);
                    Call<okhttp3.ResponseBody> call = apiService.upload(tokenf, multipartBody);
                    call.enqueue(new Callback<okhttp3.ResponseBody>() {
                        @Override
                        public void onResponse(Call<okhttp3.ResponseBody> call,
                                               Response<okhttp3.ResponseBody> response) {

                            loading.dismiss();
                            if(response.code()==200) {
                                filestatus.setText("File Uploaded Successfully");
                            }
                            else
                            {     Snackbar.make(v,"Check Your connection",Snackbar.LENGTH_SHORT).show();
                                Log.d("Upload",Integer.toString(response.code()));
                            }
                        }

                        @Override
                        public void onFailure(Call<okhttp3.ResponseBody> call, Throwable t) {
                            Log.d("Upload error:", t.getMessage());
                            Snackbar.make(v,"Check Your connection",Snackbar.LENGTH_SHORT).show();
                        }
                    });
                    /*Dialogs.showUploadDialog(getContext(), jsonObject.toString(), file.getName(),token, new Dialogs.UploadListener() {
                        @Override
                        public void onUploadSuccessful() {
                            v.findViewById(R.id.tvSynopsis).setVisibility(View.VISIBLE);
                            v.findViewById(R.id.btnSelect).setVisibility(View.GONE);
                        }
                    });*/
                }

                //     Snackbar.make(v,"No internet connection.",Snackbar.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
//                Snackbar.make(v, "Something went wrong.", Snackbar.LENGTH_SHORT).show();
            }
        }

    }
    private void logout(String token) {
        final ProgressDialog loading = ProgressDialog.show(getContext(), "Fetching Data", "Please wait...", false, false);
        Call<LogoutSucess> call=apiService.logout(token);
        call.enqueue(new Callback<LogoutSucess>() {
            @Override
            public void onResponse(Call<LogoutSucess> call, Response<LogoutSucess> response) {
                if(response.code()==200)
                {
                    showDialog();
                    loading.dismiss();
                }
                else
                {loading.dismiss();
                    Snackbar.make(v,"Check Your connection",Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogoutSucess> call, Throwable t) {
           loading.dismiss();
            }
        });
    }
    public void showDialog()
    {
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setTitle("Logout SuccessFully")
                .setMessage("Exit From Dashboard")
                .setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = sharedprefs.edit();
                        editor.remove(Config.Token);
                        editor.apply();
                        // continue with delete
                        Intent i=new Intent(getActivity(), MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                        getActivity().finish();
                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
