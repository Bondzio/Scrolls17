package in.silive.scrolls.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import in.silive.scrolls.Adapters.SpinnerAdapter;
import in.silive.scrolls.Listeners.FetchDataListener;
import in.silive.scrolls.Network.FetchData;
import in.silive.scrolls.R;
import in.silive.scrolls.Util.Config;
import in.silive.scrolls.Util.Validator;

/**
 * Created by AKG002 on 06-09-2016.
 */
public class SingleRegistration extends android.support.v4.app.Fragment {
View rootView;
    EditText stud_name, stud_other_college, stud_id, stud_mob_no, stud_mail;
    Spinner stud_college, stud_course, stud_year;
    CheckBox stud_accommodation;
    private Button individual_submit;
    ProgressDialog progressDialog;
    HashMap<String,Integer> collegeList = new HashMap<>();

    public SingleRegistration() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView==null){
            rootView = inflater.inflate(R.layout.fragment_single_registration,container,false);
            stud_name = (EditText) rootView.findViewById(R.id.stud_name);
            stud_other_college = (EditText) rootView.findViewById(R.id.stud_other_college);
            stud_other_college.setVisibility(View.GONE);
            stud_id = (EditText) rootView.findViewById(R.id.stud_id);
            stud_mob_no = (EditText) rootView.findViewById(R.id.stud_mob_no);
            stud_mail = (EditText) rootView.findViewById(R.id.stud_mail);
            stud_accommodation = (CheckBox) rootView.findViewById(R.id.stud_accommodation);
            individual_submit = (Button) rootView.findViewById(R.id.individual_submit);
            individual_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //
                }
            });
            stud_college = (Spinner) rootView.findViewById(R.id.stud_college);
            stud_college.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (stud_college.getSelectedItem().equals("Other")) {
                        stud_other_college.setVisibility(View.VISIBLE);
                    }
                    if (collegeList.get(stud_college.getSelectedItem().toString()) == 4){
                        stud_id.setVisibility(View.VISIBLE);
                    }
                    else
                        stud_id.setVisibility(View.GONE);

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            stud_course = (Spinner) rootView.findViewById(R.id.stud_course);
            stud_course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            stud_year = (Spinner) rootView.findViewById(R.id.stud_year);
           stud_mail.addTextChangedListener(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

               }

               @Override
               public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

               }

               @Override
               public void afterTextChanged(Editable editable) {
                    if (!Validator.isValidEmail(editable.toString())){
                        stud_mail.setError("Not Valid Email");
                    }else
                        stud_mail.setError(null);
               }
           });
            stud_mob_no.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (!Validator.isValidPhone(editable.toString())){
                        stud_mob_no.setError("Not valid mobile number.");
                    }else
                        stud_mob_no.setError(null);
                }
            });
            stud_course.setAdapter(new SpinnerAdapter(getContext(),new ArrayList<String>(
                    Arrays.asList(getResources().getStringArray(R.array.Courses)))));
            stud_year.setAdapter(new SpinnerAdapter(getContext(),new ArrayList<String>(
                    Arrays.asList(getResources().getStringArray(R.array.Year)))));
            getCollegeList();
        }

        return rootView;
    }

    public void checkPhoneNumber(){
        if (stud_mob_no.getError()!=null){
            FetchData checkNum = new FetchData();
            checkNum.setArgs(Config.CHECK_IS_PHONE_NUMBER_REGISTERED, new FetchDataListener() {
                @Override
                public void preExecute() {
                    progressDialog = new ProgressDialog(getContext());
                    progressDialog.setMessage("Checking phone number.");
                    progressDialog.show();
                }

                @Override
                public void postExecute(String result, int id) throws JSONException {

                }
            });
        }
    }


    public void getCollegeList(){
        final FetchData getCollege = new FetchData();
        getCollege.setArgs(Config.GET_COLLEGES, new FetchDataListener() {
            @Override
            public void preExecute() {
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Loading Collges list.");
                progressDialog.show();
            }

            @Override
            public void postExecute(String result, int id) throws JSONException {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                try {
                    JSONArray array = new JSONArray(result);
                    for (int i=0;i<array.length();i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        collegeList.put(jsonObject.getString("CollegeName"),jsonObject.getInt("CollegeId"));
                    }
                    stud_college.setAdapter(new SpinnerAdapter(getContext(),new ArrayList<String>(collegeList.keySet())));

                }catch (Exception e){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                    dialog.setTitle("Error Occurred.");
                    dialog.setMessage("Could not load College list.");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getCollegeList();
                            dialogInterface.dismiss();
                        }
                    });
                    dialog.show();
                }
            }
        });
        getCollege.execute();
    }
}

