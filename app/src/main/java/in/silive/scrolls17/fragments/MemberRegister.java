package in.silive.scrolls17.fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.regex.Pattern;

import in.silive.scrolls17.network.ApiClient;
import in.silive.scrolls17.network.CheckConnectivity;
import in.silive.scrolls17.network.RetrofitApiInterface;
import in.silive.scrolls17.R;
import in.silive.scrolls17.util.Config;
import in.silive.scrolls17.util.Validator;
import in.silive.scrolls17.application.Scrolls;
import in.silive.scrolls17.models.CheckStudentNoExsist;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 12/9/17.
 */

public class MemberRegister extends Fragment implements BlockingStep {

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

    private RetrofitApiInterface apiService;
    private boolean fladss;
    private LinearLayout stud_other_collegel;
    private LinearLayout stud_idl;
    boolean m;
    private Typeface typeface;
    private TextView teamlead;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        reg_view = inflater.inflate(R.layout.memreg, container, false);
        stud_name = (EditText) reg_view.findViewById(R.id.stud_name);
        //initialize your UI
        setRetainInstance(true);
      //  typeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/font.ttf");

        sharedPreferences = Scrolls.getInstance().sharedPrefs;
        stud_other_college = (EditText) reg_view.findViewById(R.id.stud_other_college);
        teamlead=(TextView) reg_view.findViewById(R.id.teamlead);
       // teamlead.setTypeface(typeface);
        stud_other_collegel = (LinearLayout) reg_view.findViewById(R.id.stud_other_collegel);
        stud_id = (EditText) reg_view.findViewById(R.id.stud_id);
        stud_idl = (LinearLayout) reg_view.findViewById(R.id.stud_idl);
        stud_mob_no = (EditText) reg_view.findViewById(R.id.stud_mob_no);
        stud_mail = (EditText) reg_view.findViewById(R.id.stud_mail);
        stud_accommodation = (RadioGroup) reg_view.findViewById(R.id.acommodation);
        stud_accommodation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.yes) {
                    flagacc = "1";


                } else {
                    flagacc = "0";

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

        stud_coursevalue = stud_course.getSelectedItem().toString();
        stud_yearvalue = stud_year.getSelectedItem().toString();
        apiService =
                ApiClient.getClient().create(RetrofitApiInterface.class);
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
    public void onNextClicked(final StepperLayout.OnNextClickedCallback callback) {
        saveData();

        boolean validation = checkValidation();

        if (!validation) {
            Snackbar.make(reg_view,"Check Details",Snackbar.LENGTH_SHORT).show();
          return;
        }
        else {
            callback.goToNextStep();
        }
    }






    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        Toast.makeText(getActivity(), "Congratulations You are Successfully registered", Toast.LENGTH_LONG).show();

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
        editor = sharedPreferences.edit();

        editor.putString(Config.ACCOMODATION1, flagacc);

        editor.putString(Config.Member1Name, student_name);
        editor.putString(Config.STUDENTMAIL, student_mail);
        editor.putString(Config.STUDENTMOBNO, student_mob_no);
        editor.putString(Config.COURSE, stud_coursevalue);
        editor.putString(Config.StudentYear, stud_yearvalue);
        editor.putString(Config.CoLLEGENAME, stud_collegevalue);

            editor.putString(Config.STUDENTID, student_id);

        editor.commit();

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
                    {Log.d("debugg",response.body().getError());
                        flads=true;
                    }
                    else
                    {
                        Snackbar.make(reg_view,"some error occured",Snackbar.LENGTH_SHORT).show();
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
