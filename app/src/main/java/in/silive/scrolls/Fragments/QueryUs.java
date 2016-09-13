package in.silive.scrolls.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

import in.silive.scrolls.Listeners.FetchDataListener;
import in.silive.scrolls.Network.CheckConnectivity;
import in.silive.scrolls.Network.FetchData;
import in.silive.scrolls.R;
import in.silive.scrolls.Util.Config;
import in.silive.scrolls.Util.Validator;

/**
 * A simple {@link Fragment} subclass.
 */
public class QueryUs extends Fragment {
    //UI-Elements
    EditText email_query, message_query;
    Button submit_query;

    String usr_mail, usr_msg;
    View query_view;
    ProgressDialog progressDialog;

    public QueryUs() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        query_view = inflater.inflate(R.layout.fragment_query_us, container, false);
        email_query = (EditText) query_view.findViewById(R.id.email_query);
        message_query = (EditText) query_view.findViewById(R.id.message_query);
        submit_query = (Button) query_view.findViewById(R.id.submit_query);
        submit_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserQuery();

            }
        });
        return query_view;
    }

    public void getUserQuery() {
        usr_mail = email_query.getText().toString();
        if (!Validator.isValidEmail(usr_mail)) {
            //To do.. dialog for invalid mail
           /* DialogInvalidMail dialogInvalidMail = new DialogInvalidMail();
            dialogInvalidMail.show(getChildFragmentManager(), "Invalid Mail");*/
            Snackbar.make(query_view, "Empty Query", Snackbar.LENGTH_SHORT).show();
            return;
        }


        usr_msg = message_query.getText().toString();
        if (usr_msg.length() == 0) {
          /*  DialogEmptyQuery dialogEmptyQuery = new DialogEmptyQuery();
            dialogEmptyQuery.show(getChildFragmentManager(), "Empty query");*/
            Snackbar.make(query_view, "Invalid Mail", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (CheckConnectivity.isNetConnected(getContext())) {

            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Email",usr_mail);
                jsonObject.put("Body",usr_msg);
                FetchData fetchData = new FetchData();
                fetchData.setArgs(Config.QUERY_URL, new FetchDataListener() {
                    @Override
                    public void preExecute() {
                        progressDialog = new ProgressDialog(getContext());
                        progressDialog.setMessage("Loading");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }

                    @Override
                    public void postExecute(String result, int id) throws JSONException {
if (progressDialog.isShowing())
    progressDialog.dismiss();
                        if (result.contains("201")){
                            Snackbar.make(query_view, "Query registered", Snackbar.LENGTH_SHORT).show();
                        }
                        else {
                            Snackbar.make(query_view, "Registering query failed", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
                fetchData.execute();
            } catch (Exception e) {
                e.printStackTrace();
                Snackbar.make(query_view, "Invalid Mail", Snackbar.LENGTH_SHORT).show();
            }
        }
        else
            Snackbar.make(query_view, "No internet connection", Snackbar.LENGTH_SHORT).show();

    }

}