package in.silive.scrolls16.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import in.silive.scrolls16.Listeners.FetchDataListener;
import in.silive.scrolls16.Network.ApiClient;
import in.silive.scrolls16.Network.CheckConnectivity;
import in.silive.scrolls16.Network.FetchData;
import in.silive.scrolls16.Network.RetrofitApiInterface;
import in.silive.scrolls16.R;
import in.silive.scrolls16.Util.Config;
import in.silive.scrolls16.Util.Validator;
import in.silive.scrolls16.models.QueryModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

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
    private RetrofitApiInterface apiService;
    private Call<QueryModel> call;

    public QueryUs() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        query_view = inflater.inflate(R.layout.queryus, container, false);
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

           /* try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Email",usr_mail);
                jsonObject.put("Body",usr_msg);
                FetchData fetchData = new FetchData();
                fetchData.setArgs(Config.QUERY_URL,jsonObject.toString(), new FetchDataListener() {
                    @Override
                    public void preExecute() {
                        progressDialog = new ProgressDialog(getContext());
                        progressDialog.setMessage("Loading");
                        progressDialog.setCancelable(false);
                        if (!getActivity().isFinishing())
                         progressDialog.show();
                    }

                    @Override
                    public void postExecute(String result, int id) throws JSONException {
if (progressDialog.isShowing())
    progressDialog.dismiss();
                        Toast.makeText(getContext(),result,Toast.LENGTH_LONG).show();
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
        }*/
            final ProgressDialog loading = ProgressDialog.show(getContext(), "Fetching Data", "Please wait...", false, false);
            apiService =
                    ApiClient.getClient().create(RetrofitApiInterface.class);

            apiService.submitQuery(usr_mail,usr_msg).enqueue(new Callback<QueryModel>() {
              @Override
              public void onResponse(Call<QueryModel> call, Response<QueryModel> response) {
                  loading.dismiss();
                  int status_code=response.code();
                  if(status_code==201)
                  {
                      Snackbar.make(query_view, "Query registered", Snackbar.LENGTH_SHORT).show();
                  }
                  else
                  {
                      Snackbar.make(query_view, "Registering query failed", Snackbar.LENGTH_SHORT).show();
                  }

              }

              @Override
              public void onFailure(Call<QueryModel> call, Throwable t) {
                  loading.dismiss();
                  Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
                  Log.d("error",toString());

              }
          });


        }



        else
            Snackbar.make(query_view, "No internet connection", Snackbar.LENGTH_SHORT).show();

    }

}