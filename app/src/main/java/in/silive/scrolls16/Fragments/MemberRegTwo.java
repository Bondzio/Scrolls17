package in.silive.scrolls16.Fragments;

/**
 * Created by root on 16/9/17.
 */

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;

import in.silive.scrolls16.Network.ApiClient;
import in.silive.scrolls16.Network.RetrofitApiInterface;
import in.silive.scrolls16.R;
import in.silive.scrolls16.Util.Config;
import in.silive.scrolls16.Util.Validator;
import in.silive.scrolls16.application.*;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

import in.silive.scrolls16.R;
import in.silive.scrolls16.Util.Config;
import in.silive.scrolls16.Util.Validator;
import in.silive.scrolls16.application.*;
import in.silive.scrolls16.application.Scrolls;
import in.silive.scrolls16.models.Member;
import in.silive.scrolls16.models.RegisterModel;
import in.silive.scrolls16.models.RegisterSucess;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 12/9/17.
 */

public class MemberRegTwo extends Fragment implements BlockingStep {

    public static String student_name, student_college_name, student_id, student_mob_no, student_mail, student_course;
    public static boolean student_accommodation = false;

    private ProgressDialog progressDialog;
    private int student_courseId;
    EditText stud_name, stud_other_college, stud_id, stud_mob_no, stud_mail, team_password;
    EditText team_name, member_one_id, member_one_name, member_two_id, member_two_name, member_three_id, member_three_name;
    Button individual_submit, submit_team_reg;
    Spinner stud_college, stud_course, stud_year, team_domain, team_topic;
    RadioGroup stud_accommodation;
    RadioGroup team_leader, no_of_team_members;
    RadioButton leader_member_one, leader_member_two, leader_member_three, two_members, three_members;
    ProgressBar progressBar;
    View reg_view;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String flagacc;
    private String stud_collegevalue,stud_coursevalue,stud_yearvalue;
    private RetrofitApiInterface apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        reg_view = inflater.inflate(R.layout.memreg, container, false);
        stud_name = (EditText) reg_view.findViewById(R.id.stud_name);
        //initialize your UI
        sharedPreferences= in.silive.scrolls16.application.Scrolls.getInstance().sharedPrefs;
        stud_other_college = (EditText) reg_view.findViewById(R.id.stud_other_college);
        stud_other_college.setVisibility(View.GONE);
        stud_id = (EditText) reg_view.findViewById(R.id.stud_id);
        stud_mob_no = (EditText) reg_view.findViewById(R.id.stud_mob_no);
        stud_mail = (EditText) reg_view.findViewById(R.id.stud_mail);
        stud_accommodation = (RadioGroup) reg_view.findViewById(R.id.acommodation);
        stud_accommodation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.yes) {
                    flagacc="1";


                } else {
                    flagacc="0";

                }
            }
        });
        stud_college=(Spinner)reg_view.findViewById(R.id.stud_college);
        stud_college.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!stud_college.getSelectedItem().equals("Ajay Kumar GARG ENGINEERING COLLEGE")) {
                    stud_other_college.setVisibility(View.VISIBLE);
                    stud_id.setVisibility(View.INVISIBLE);
                    stud_collegevalue=stud_other_college.getText().toString();

                }
                else
                {    stud_other_college.setVisibility(View.INVISIBLE);
                    stud_id.setVisibility(View.VISIBLE);
                    stud_collegevalue=stud_college.getSelectedItem().toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        stud_course = (Spinner) reg_view.findViewById(R.id.stud_course);
        stud_course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stud_year = (Spinner) reg_view.findViewById(R.id.stud_year);
        stud_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stud_coursevalue=stud_course.getSelectedItem().toString();
        stud_yearvalue=stud_year.getSelectedItem().toString();
        apiService =
                ApiClient.getClient().create(RetrofitApiInterface.class);
        return reg_view;
    }



    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {


    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        saveData();
        apiCall();

    }

    private void apiCall() {
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("team_name", "testingfff");

            paramObject.put("domain_id", "2");
            paramObject.put("topic_id","1");
            paramObject.put("password","sims");
            paramObject.put("noofmembers","2");
            JSONObject Member1 = new JSONObject();
            try {
                Member1.put("name","Natio");
                Member1.put("email", "mayur.patkks152@gmail.com");
                Member1.put("course","btech");
                Member1.put("year", "1");
                Member1.put("college_name","akgec");
                Member1.put("student_no", "1413568");
                Member1.put("contact_no","9569696969");
                Member1.put( "accomodation", "1");
                Member1.put( "teamlead","1");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            JSONObject Member2 = new JSONObject();
            try {
                Member2.put("name","Nationn");
                Member2.put("email", "mayurrr.pat3r3k152@gmail.com");
                Member2.put("course","btech");
                Member2.put("year", "1");
                Member2.put("college_name","akgec");
                Member2.put("student_no", "1411458");
                Member2.put("contact_no","9569696969");
                Member2.put("accomodation", "1");
                Member2.put("teamlead","0");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            JSONArray jsonArray = new JSONArray();

            jsonArray.put(Member1);
            jsonArray.put(Member2);


            paramObject.put("members", jsonArray);
            Member m=new Member("Sims","simsss@gmail.com","btech","2","akgec","1567256","8090631320","1","1");
            Member m1=new Member("Sissms","simssssss@gmail.com","btech","2","akgec","1576256","8090631320","1","0");

            List<Member> members=new ArrayList<>();

            members.add(m);
            members.add(m1);

           Log.d("debugg",paramObject.toString());
            final ProgressDialog loading = ProgressDialog.show(getContext(), "Fetching Data", "Please wait...", false, false);
            Call<RegisterSucess> userCall = apiService.register(new RegisterModel("tester","2","1","Sims","2",members));
           userCall.enqueue(new Callback<RegisterSucess>() {
               @Override
               public void onResponse(Call<RegisterSucess> call, Response<RegisterSucess> response) {
                   if(response.code()==200)
                   {
                       Toast.makeText(getActivity(),"Successfull",Toast.LENGTH_LONG).show();
                   }
                   else
                   {
                    Log.d("debugg",Integer.toString(response.code())+ new Gson().toJson(response.errorBody()));
                   }
                   loading.dismiss();

               }

               @Override
               public void onFailure(Call<RegisterSucess> call, Throwable t) {
                  Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_LONG).show();
                   loading.dismiss();
               }
           });
        }
        catch (JSONException e)
        {

        }
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();

    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
    int flag;
    int student_year, collegeId;
    public void getStudData() {
        Log.d("Scrolls", "getData called");
        flag = 0;
        student_name = stud_name.getText().toString();
        if (student_name.length() == 0) {
            flag = 1;
            stud_name.setError("Invalid name");
        }

        student_college_name = stud_other_college.getText().toString();
        if(stud_id.getVisibility()==View.VISIBLE) {
            student_id = stud_id.getText().toString();

            if (student_id.length() == 0 && stud_id.getVisibility() == View.VISIBLE && !Pattern.matches("^\\d{7}[Dd]{0,1}$", student_id)) {
                flag = 1;
                stud_id.setError("Invalid Id");
            }

        }
        student_mail = stud_mail.getText().toString();
        if (!Validator.isValidEmail(student_mail)) {
            flag = 1;
            stud_mail.setError("Invalid mail");
        }
        student_mob_no = stud_mob_no.getText().toString();
        if (student_mob_no.length() != 10) {
            flag = 1;
            stud_mob_no.setError("Invalid phone number");
        }
        student_courseId = stud_course.getSelectedItemPosition() + 1;
        student_year = Integer.parseInt(stud_year.getSelectedItem().toString());

    }

    private void saveData() {
        getStudData();
        editor=sharedPreferences.edit();

        editor.putString(Config.ACCOMODATION2, flagacc);

        editor.putString(Config.Member2Name,student_name);
        editor.putString(Config.STUDENTMAIL2,student_mail);
        editor.putString(Config.STUDENTMOBNO2,student_mob_no);
        editor.putString(Config.COURSE2, stud_coursevalue);
        editor.putString(Config.StudentYear2,  stud_yearvalue);
        editor.putString(Config.CoLLEGENAME2,stud_collegevalue);
        if(student_id!=null)
        {
            editor.putString(Config.STUDENTID2,student_id);
        }
        editor.commit();

    }

}
