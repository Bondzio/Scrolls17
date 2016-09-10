package in.silive.scrolls.Util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import in.silive.scrolls.Listeners.FetchDataListener;
import in.silive.scrolls.Listeners.UploaderListener;
import in.silive.scrolls.Network.CheckConnectivity;
import in.silive.scrolls.Network.FetchData;
import in.silive.scrolls.R;

/**
 * Created by AKG002 on 01-09-2016.
 */
public class Dialogs {
    public static void showForgotIdDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Forgot Scrolls ID");
        builder.setCancelable(false);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.dialog_forgot_id, null);
        builder.setView(view);
        final EditText etEmailId = (EditText)view.findViewById(R.id.etEmailId);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, int i) {
                //overridden after show() is called so that dialog is not closed after button is clicked
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = etEmailId.getText().toString();
                if (Validator.isValidEmail(email)) {
                    etEmailId.setError(null);
                    if (CheckConnectivity.isNetConnected(context)) {
                        FetchData fetchData = new FetchData();
                        fetchData.setArgs(Config.ID_BY_EMAIL, new FetchDataListener() {
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
                                if (result.equals("0")) {
                                    dialog.dismiss();
                                    android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(context)
                                            .setTitle("Id")
                                            .setMessage("Your id is " + "")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            })

                                            .setIcon(android.R.drawable.ic_dialog_alert);
                                    dialog.show();

                                } else {
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
                        fetchData.execute();
                    }else
                        Toast.makeText(context,"No Internet connection available.",Toast.LENGTH_SHORT).show();
                } else {
                    etEmailId.setError("Not valid Email");
                }
                }
        });
    }

    public static void showUploadDialog(final Context context,String json,String fileName) {
       final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Uploading Doc");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage(fileName);
        progressDialog.setProgress(0);

        final FetchData uploadDoc = new FetchData();
        try {

            uploadDoc.setArgs(Config.UPLOAD_DOC, json, new UploaderListener() {
                @Override
                public void setProgress(int progress) {
                   // progressDialog.setMessage(progress+"%");
                    progressDialog.setProgress(progress);
                }

                @Override
                public void preExecute() {
                    progressDialog.show();
                }

                @Override
                public void postExecute(String result, int id) throws JSONException {
                    android.support.v7.app.AlertDialog.Builder notifyDialog = new android.support.v7.app.AlertDialog.Builder(context);
                    notifyDialog.setTitle("Upload Doc");
                    if (!TextUtils.isEmpty(result))
                        notifyDialog.setMessage("File uploaded successfully");
                    else {
                        notifyDialog.setMessage("Upload failed");
                    }
                    notifyDialog.setPositiveButton( "Dismiss", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();
                            progressDialog.dismiss();
                        }
                    });
                    notifyDialog.show();
                }
            });

            progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Dismiss", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (!uploadDoc.isCancelled())
                        uploadDoc.cancel(true);
                    dialogInterface.dismiss();
                }
            });
            uploadDoc.execute();
            progressDialog.show();

        } catch (Exception e) {
            progressDialog.setMessage("Some error occured.");

        }

    }

}
