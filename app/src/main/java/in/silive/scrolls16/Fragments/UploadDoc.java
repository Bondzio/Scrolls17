package in.silive.scrolls16.Fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
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

import in.silive.scrolls16.Activities.MyPickerActivity;
import in.silive.scrolls16.Activities.SecondActivity;
import in.silive.scrolls16.Activities.UploadActivity;
import in.silive.scrolls16.Listeners.FetchDataListener;
import in.silive.scrolls16.Network.ApiClient;
import in.silive.scrolls16.Network.CheckConnectivity;
import in.silive.scrolls16.Network.FetchData;
import in.silive.scrolls16.Network.RetrofitApiInterface;
import in.silive.scrolls16.Network.ServiceGenerator;
import in.silive.scrolls16.R;
import in.silive.scrolls16.Util.Config;
import in.silive.scrolls16.Util.Keyboard;
import in.silive.scrolls16.models.LoginModelF;
import in.silive.scrolls16.models.LoginSucess;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private RetrofitApiInterface apiService,service;
    private SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;
    private String token;

    public UploadDoc() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_login, container, false);
        context = getContext();
      //  upload = (LinearLayout) v.findViewById(R.id.upload);
        login_team_id = (EditText) v.findViewById(R.id.login_team_id);
        login_password = (EditText) v.findViewById(R.id.login_password);
        user_login = (Button) v.findViewById(R.id.user_login);
        sharedPreferences = in.silive.scrolls16.application.Scrolls.getInstance().sharedPrefs;
        apiService =
                ApiClient.getClient().create(RetrofitApiInterface.class);
         service =
                ServiceGenerator.createService(RetrofitApiInterface.class);
        user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Keyboard.close(getContext());
                getLoginData();
            }
        });
        /*user_register = (Button) v.findViewById(R.id.user_register);
        user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Keyboard.close(getContext());

                Intent i = new Intent(getContext(),SecondActivity.class);
                        i.putExtra(Config.KEY_FRAGMENT,Config.KEY_REGISTER);
                        startActivity(i);

            }
        });*/
        /*btnSelect = (Button) v.findViewById(R.id.btnSelect);
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
        });*/
        //tvDomain = (TextView)v.findViewById(R.id.tvDomain);
        //tvTopic = (TextView)v.findViewById(R.id.tvTopic);
       // tvTeamID = (TextView)v.findViewById(R.id.tvTeamId);
      /*  if (TextUtils.isEmpty(domainName)) {
            v.findViewById(R.id.llForm).setVisibility(View.VISIBLE);
            v.findViewById(R.id.llUpload).setVisibility(View.GONE);
        } else {
            v.findViewById(R.id.llForm).setVisibility(View.GONE);
            v.findViewById(R.id.llUpload).setVisibility(View.VISIBLE);
        }*/
        return v;
    }


    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
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
                String tokenf=sharedPreferences.getString(Config.Token,"");
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
                    Call<ResponseBody> call = apiService.upload(tokenf, multipartBody);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call,
                                               Response<ResponseBody> response) {

                            loading.dismiss();
                           if(response.code()==200) {
                               Log.d("Upload", "success");
                           }
                           else
                           {
                               Log.d("Upload",Integer.toString(response.code()));
                           }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("Upload error:", t.getMessage());
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
                Call<LoginModelF> call = apiService.Login(login_team_id.getText().toString(), login_password.getText().toString());
                final ProgressDialog loading = ProgressDialog.show(getContext(), "Fetching Data", "Please wait...", false, false);
                call.enqueue(new Callback<LoginModelF>() {
                    @Override
                    public void onResponse(Call<LoginModelF> call, Response<LoginModelF> response) {
                        if (response.code() == 401) {
                            login_team_id.setError("Invalid ID");
                            login_password.setError("Invalid password");
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
                        } else if (response.code() == 200) {
                             //tvTeamID.setText(team_id);
                             token = response.body().getData().getToken();
                            String TeamName=response.body().getData().getTeamname();
                            String Member1Name=response.body().getData().getMember1name();
                            String Member2Name=response.body().getData().getMember2name();
                            String Member3Name=response.body().getData().getMember3name();
                            Log.d("debugg",token);
                            editor = sharedPreferences.edit();
                            editor.putString(Config.Token, token);
                            editor.putString(Config.LOGINM1, token);
                            editor.putString(Config.LOGINM2, token);
                            editor.putString(Config.LOGINM3, token);
                            editor.putString(Config.LOGINT3, token);

                            editor.commit();
                            Intent i=new Intent(getActivity(),UploadActivity.class);
                            getActivity().startActivity(i);

                         //   getSynopsisAvail();
                         //     v.findViewById(R.id.llForm).setVisibility(View.GONE);
                           // v.findViewById(R.id.llUpload).setVisibility(View.VISIBLE);

                        }
                        loading.dismiss();

                    }

                    @Override
                    public void onFailure(Call<LoginModelF> call, Throwable t) {
                        loading.dismiss();
                    }
                });


            }
        }

    }
    public void showFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment)/*.addToBackStack(fragment.getClass().getName())*/;
            fragmentTransaction.commit();

        }
    }



}
