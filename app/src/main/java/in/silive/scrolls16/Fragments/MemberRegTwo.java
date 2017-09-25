package in.silive.scrolls16.Fragments;

/**
 * Created by root on 16/9/17.
 */

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import in.silive.scrolls16.Activities.MainActivity;
import in.silive.scrolls16.Network.ApiClient;
import in.silive.scrolls16.Network.CheckConnectivity;
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
import in.silive.scrolls16.models.CheckStudentNoExsist;
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

    public static String student_name, student_college_name, student_id="", student_mob_no, student_mail, student_course;
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
    Call<CheckStudentNoExsist> call;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String flagacc="1";
    private String stud_collegevalue, stud_coursevalue, stud_yearvalue;
    boolean flags;
    private LinearLayout stud_other_collegel,stud_idl;
    private RetrofitApiInterface apiService;
    private boolean fladss;
   boolean m;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        reg_view = inflater.inflate(R.layout.memregis, container, false);
        stud_name = (EditText) reg_view.findViewById(R.id.stud_name);
        //initialize your UI
        sharedPreferences= in.silive.scrolls16.application.Scrolls.getInstance().sharedPrefs;
        stud_other_college = (EditText) reg_view.findViewById(R.id.stud_other_college);

        stud_other_collegel = (LinearLayout) reg_view.findViewById(R.id.stud_other_collegel);
        apiService =
                ApiClient.getClient().create(RetrofitApiInterface.class);
        stud_idl = (LinearLayout) reg_view.findViewById(R.id.stud_idl);
        stud_other_collegel.setVisibility(View.GONE);
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
        stud_college = (Spinner) reg_view.findViewById(R.id.stud_college);
        stud_college.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!stud_college.getSelectedItem().equals("Ajay Kumar GARG ENGINEERING COLLEGE")) {
                    stud_other_collegel.setVisibility(View.VISIBLE);
                    stud_idl.setVisibility(View.INVISIBLE);
                    m=true;

                } else {
                    stud_other_collegel.setVisibility(View.INVISIBLE);
                    stud_idl.setVisibility(View.VISIBLE);
                    stud_collegevalue = "akgec";
                    m=false;

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

        if(stud_id.getVisibility()==View.VISIBLE)
        {
            stud_id.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus) {
                        if (!CheckConnectivity.isNetConnected(getContext())) {
                            Snackbar.make(reg_view, "No internet connection.", Snackbar.LENGTH_SHORT).show();
                        }
                        else {
                            boolean studentNo = CheckStudentNo();
                        }
                    }
                }
            });
        }
        stud_mail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {if (!CheckConnectivity.isNetConnected(getContext())) {
                    Snackbar.make(reg_view, "No internet connection.", Snackbar.LENGTH_SHORT).show();
                }
                else {
                    boolean email = CheckEmailId();
                }
                }
            }
        });
        return reg_view;
    }



    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {


    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        saveData();
         boolean validation=checkValidation();
        if (!validation) {
            Snackbar.make(reg_view,"Check Details",Snackbar.LENGTH_SHORT).show();
            return;
        }
        else {
            if (!CheckConnectivity.isNetConnected(getContext())) {
                Snackbar.make(reg_view, "No internet connection.", Snackbar.LENGTH_SHORT).show();
            }
            else {
                apiCall();
            }

        }


    }

    private void apiCall() {
        String name1=sharedPreferences.getString(Config.Member1Name,"");
        String email1=sharedPreferences.getString(Config.STUDENTMAIL,"");
        String course1=sharedPreferences.getString(Config.COURSE,"");
        String year1=sharedPreferences.getString(Config.StudentYear,"");
        String college1=sharedPreferences.getString(Config.CoLLEGENAME,"");
        String studentno1=sharedPreferences.getString(Config.STUDENTID,"");
        String phone1=sharedPreferences.getString(Config.STUDENTMOBNO,"");
        String accomodation1=sharedPreferences.getString(Config.ACCOMODATION1,"");
        String name2=sharedPreferences.getString(Config.Member11Name,"");
        String email2=sharedPreferences.getString(Config.STUDENTMAIL11,"");
        String course2=sharedPreferences.getString(Config.COURSE11,"");
        String year2=sharedPreferences.getString(Config.StudentYear11,"");
        String college2=sharedPreferences.getString(Config.CoLLEGENAME11,"");
        String studentno2=sharedPreferences.getString(Config.STUDENTID11,"");
        String phone2=sharedPreferences.getString(Config.STUDENTMOBNO11,"");
        String accomodation2=sharedPreferences.getString(Config.ACCOMODATION11,"");
        String name3=sharedPreferences.getString(Config.Member2Name,"");
        String email3=sharedPreferences.getString(Config.STUDENTMAIL2,"");
        String course3=sharedPreferences.getString(Config.COURSE2,"");
        String year3=sharedPreferences.getString(Config.StudentYear2,"");
        String college3=sharedPreferences.getString(Config.CoLLEGENAME2,"");
        String studentno3=sharedPreferences.getString(Config.STUDENTID2,"");
        String phone3=sharedPreferences.getString(Config.STUDENTMOBNO2,"");
        String accomodation3=sharedPreferences.getString(Config.ACCOMODATION2,"");
        String teamname=sharedPreferences.getString(Config.TEAMNAME,"");
        String domainid=sharedPreferences.getString(Config.domain_id,"");
        String topicid=sharedPreferences.getString(Config.topicid,"");
        String pass=sharedPreferences.getString(Config.password,"");
        String noofmem=sharedPreferences.getString(Config.NO_OF_MEMBERS,"");

            Member m=new Member(name1,email1,course1,year1,college1,studentno1,phone1,accomodation1,"1");
        Member m1=new Member(name2,email2,course2,year2,college2,studentno2,phone2,accomodation2,"0");
        Member m2=new Member(name3,email3,course3,year3,college3,studentno3,phone3,accomodation3,"0");

            List<Member> members=new ArrayList<>();

            members.add(m);
            members.add(m1);
            members.add(m2);
        RegisterModel registerModel=new RegisterModel(teamname,domainid,topicid,pass,noofmem,members);

        final ProgressDialog loading = ProgressDialog.show(getContext(), "Fetching Data", "Please wait...", false, false);


        Call<RegisterSucess> userCall = apiService.register(registerModel);
        Log.d("debugg",members.get(0).getName());
        userCall.enqueue(new Callback<RegisterSucess>() {

            @Override
            public void onResponse(Call<RegisterSucess> call, Response<RegisterSucess> response) {
                if(response.code()==200)
                {
                    //Toast.makeText(getActivity(),"Successfull",Toast.LENGTH_LONG).show();
                    showDialog(response.body().getData());
                }
                else
                {//Toast.makeText(getActivity(),Integer.toString(response.code()),Toast.LENGTH_LONG).show();
                    Log.d("debugg",response.toString());
                    Log.d("debugg",call.request().body().toString());
                    Snackbar.make(reg_view,"some error occured",Snackbar.LENGTH_SHORT).show();
                }
                loading.dismiss();

            }

            @Override
            public void onFailure(Call<RegisterSucess> call, Throwable t) {
                //Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_LONG).show();
                Snackbar.make(reg_view,"some error occured",Snackbar.LENGTH_SHORT).show();
                Log.d("debugg",t.toString());
                loading.dismiss();
            }
        });
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
        if (student_name.length() == 0 && !Pattern.matches("^[A-Z][a-z]$", student_name)) {
            flag = 1;
            stud_name.setError("Invalid name");
        }
        if(m==true) {
            stud_collegevalue = stud_other_college.getText().toString();
        }

        student_college_name = stud_other_college.getText().toString();
        if (stud_id.getVisibility() == View.VISIBLE) {
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
        if (!Pattern.matches("^([0]|\\+91)?\\d{10}", student_mob_no)) {
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

            editor.putString(Config.STUDENTID2,student_id);

        editor.commit();

    }
    public void showDialog(String message)
    {
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setTitle("Registration SuccessFull")
                .setMessage("Your SCROLLS REGISTRATION ID IS"+message)
                .setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        // continue with delete
                        Intent i=new Intent(getActivity(), MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                        getActivity().finish();
                    }
                })

                .setIcon(R.drawable.scrollslogo)
                .show();
    }
    public boolean checkValidation() {
        flags = true;
        student_name = stud_name.getText().toString();
        if (student_name.length() == 0 || !Pattern.matches("^[a-zA-Z\\s]*$", student_name)) {

            stud_name.setError("Invalid name");
            flags = false;
        }
        if (stud_idl.getVisibility() == View.VISIBLE) {
            student_id = stud_id.getText().toString();

            if (student_id.length() == 0 && stud_id.getVisibility() == View.VISIBLE && !Pattern.matches("^\\d{7}[Dd]{0,1}$", student_id)) {

                stud_id.setError("Invalid Id");
                flags = false;
            }

        }
        if (stud_other_collegel.getVisibility() == View.VISIBLE) {
            String collegel = stud_other_college.getText().toString();

            if (collegel.length() == 0  ) {

                stud_other_college.setError("Invalid College Name");
                flags = false;
            }

        }
        student_mail = stud_mail.getText().toString();
        if (!Validator.isValidEmail(student_mail)) {

            stud_mail.setError("Invalid mail");
            flags = false;
        }
        if(stud_mail.getError()!=null)
        {
            flags=false;
        }
        if(stud_id.getError()!=null&&stud_idl.getVisibility()==View.VISIBLE)
        {
            flags=false;
        }
        student_mob_no = stud_mob_no.getText().toString();
        if (!Pattern.matches("^([0]|\\+91)?\\d{10}", student_mob_no)) {

            stud_mob_no.setError("Invalid phone number");
            flags = false;
        }

        return flags;

    }
    boolean flads;
    public boolean CheckStudentNo() {
        flads=true;
        if (stud_id.getVisibility() == View.VISIBLE) {
            student_id=stud_id.getText().toString();
            call = apiService.checkStudentNo(student_id);
            final ProgressDialog loading = ProgressDialog.show(getContext(), "Fetching Data", "Please wait...", false, false);
            call.enqueue(new Callback<CheckStudentNoExsist>() {
                @Override
                public void onResponse(Call<CheckStudentNoExsist> call, Response<CheckStudentNoExsist> response) {
                    if (response.code() == 422) {
                        stud_id.setError("Student Already exsist");
                        flads=false;
                    }
                    else if(response.code()==200)
                    {//Log.d("debugg",response.body().getError());
                        flads=true;
                    }
                    loading.dismiss();

                }

                @Override
                public void onFailure(Call<CheckStudentNoExsist> call, Throwable t) {
                    Snackbar.make(reg_view,"some error occured",Snackbar.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            });
        }
        else

        {
            flads=true;
        }
        return flads;
    }
    public boolean CheckEmailId() {

        fladss=true;
        student_mail=stud_mail.getText().toString();
        call = apiService.checkEamilId(student_mail);
        final ProgressDialog loading = ProgressDialog.show(getContext(), "Fetching Data", "Please wait...", false, false);
        call.enqueue(new Callback<CheckStudentNoExsist>() {
            @Override
            public void onResponse(Call<CheckStudentNoExsist> call, Response<CheckStudentNoExsist> response) {
                if (response.code() == 422) {
                    stud_mail.setError("Student Email Already exsist");
                    fladss=false;
                }
                else if(response.code()==200)
                {//Log.d("debugg",response.body().getError());
                    fladss=true;
                }
                loading.dismiss();

            }

            @Override
            public void onFailure(Call<CheckStudentNoExsist> call, Throwable t) {
                Snackbar.make(reg_view,"some error occured",Snackbar.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });

        return fladss;
    }
}
