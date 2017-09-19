package in.silive.scrolls16.Fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

import in.silive.scrolls16.Adapters.SpinnerAdapter;
import in.silive.scrolls16.Network.ApiClient;
import in.silive.scrolls16.Network.CheckConnectivity;
import in.silive.scrolls16.Network.RetrofitApiInterface;
import in.silive.scrolls16.R;
import in.silive.scrolls16.Util.Config;
import in.silive.scrolls16.Util.Keyboard;
import in.silive.scrolls16.application.*;
import in.silive.scrolls16.application.Scrolls;
import in.silive.scrolls16.models.CollegeModel;
import in.silive.scrolls16.models.Datum;
import in.silive.scrolls16.models.DomainModel;
import in.silive.scrolls16.models.SelfRegister;
import in.silive.scrolls16.models.TopicModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 12/9/17.
 */

public class TeamRegister extends Fragment implements BlockingStep,TextWatcher {
    //public static String student_name, student_college_name, student_id, student_mob_no, student_mail, student_course;
    //public static boolean student_accommodation = false;
    //public static String name_of_team, name_member_one, name_member_two, name_member_three = "", id_member_one, id_member_two, id_member_three = "";
    //public static String domain_of_team, topic_of_team, password_team;
   // public static int no_of_teammembers, leader_of_team;
   // public static int flag = 0, tflag = 0;
   // static ArrayList<Integer> collegeIds = new ArrayList<>();
    //static ArrayList<String> collegeNames = new ArrayList<>();
    LinearLayout reg_individual, reg_team, member_three;
    TabLayout team_individual_tab;
    EditText stud_name, stud_other_college, stud_id, stud_mob_no, stud_mail, team_password;
    EditText team_name, member_one_id, member_one_name, member_two_id, member_two_name, member_three_id, member_three_name;
    Button individual_submit, submit_team_reg;

    Spinner stud_college, stud_course, stud_year, team_domain, team_topic;
    CheckBox stud_accommodation;
    RadioGroup team_leader, no_of_team_members;
    RadioButton leader_member_one, leader_member_two, leader_member_three, two_members, three_members;
    ProgressBar progressBar;
    int student_year, collegeId;
    ArrayList<String> topicsList = new ArrayList<String>();
    ArrayList<String> domainList = new ArrayList<String>();
    //ArrayList<Integer> topicsIDList = new ArrayList<Integer>();
    String[] domainArray = new String[5], topicArray = new String[13];
    in.silive.scrolls16.Adapters.SpinnerAdapter topicsAdapter, domainAdapter;
    ArrayList<String> searchList = new ArrayList<>();
    private ProgressDialog progressDialog;
    private int student_courseId;
    private int topic_id;
    public int id_domain = 1;
    Call<TopicModel> call;
    ArrayList<Integer> topicsIDList = new ArrayList<Integer>();
    ArrayList<Integer> domainsIDList = new ArrayList<Integer>();
    Call<List<CollegeModel>> callCollege;
    private String mode = "";
    RetrofitApiInterface apiService;
    private Call<SelfRegister> callRegister;
    View reg_view;
    List<Datum> domainlist;
    private Call<DomainModel> callDomain;
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;
    String noOfMembers;
    private EditText team_confirmPassword;
    private boolean flag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        reg_view = inflater.inflate(R.layout.team_reg, container, false);
        reg_individual = (LinearLayout) reg_view.findViewById(R.id.reg_individual);
         setRetainInstance(true);
        sharedpreferences = Scrolls.getInstance().sharedPrefs;
        reg_team = (LinearLayout) reg_view.findViewById(R.id.reg_team);
        team_password = (EditText) reg_view.findViewById(R.id.team_password);
        team_confirmPassword=(EditText)reg_view.findViewById(R.id.team_password_confirm);
        team_name = (EditText) reg_view.findViewById(R.id.team_name);
        no_of_team_members = (RadioGroup) reg_view.findViewById(R.id.no_of_team_members);
        no_of_team_members.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.three_members) {
                    noOfMembers="3";
                    editor = sharedpreferences.edit();
                    editor.putString(Config.NO_OF_MEMBERS, "3");
                    editor.commit();
                 //   member_three.setVisibility(View.VISIBLE);
                  //  leader_member_three.setVisibility(View.VISIBLE);
                    // memberCount = 3;
                } else {
                    noOfMembers="2";
                    editor = sharedpreferences.edit();
                    editor.putString(Config.NO_OF_MEMBERS, "2");
                    editor.commit();

                    //member_three.setVisibility(View.GONE);
                    //leader_member_three.setVisibility(View.GONE);
                    //memberCount = 2;
                }
            }
        });
        submit_team_reg = (Button) reg_view.findViewById(R.id.submit_team_reg);
        submit_team_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Keyboard.close(getContext());
                //getTeamData();
            }
        });
        apiService =
                ApiClient.getClient().create(RetrofitApiInterface.class);
        team_domain = (Spinner) reg_view.findViewById(R.id.team_domain);
        loadDomainList();
        team_domain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (domainsIDList.size() == 0 && motionEvent.getAction() == MotionEvent.ACTION_UP
                        ) {
                    loadDomainList();
                }

                return false;
            }
        });

        team_topic = (Spinner) reg_view.findViewById(R.id.team_topic);
        team_topic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (topicsIDList.size() == 0 && motionEvent.getAction() == MotionEvent.ACTION_UP
                        && id_domain != 0) {
                  loadTopics(id_domain);
                }

                return false;
            }
        });

    team_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(!hasFocus)
            {
                if(team_password.getText().toString().length()==0)
                {
                    team_password.setError("Fill password");

                }
            }
        }
    });
        team_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    if(team_name.getText().toString().length()==0)
                    {
                        team_name.setError("TeamName is Compulsory");

                    }
                }
            }
        });
        team_confirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    if(team_confirmPassword.getText().toString().length()==0)
                    {
                        team_confirmPassword.setError("Fill confirm password");

                    }
                }

            }
        });
    //initialize your UI

        return reg_view;
    }
    public void storeTeamData()
    {
        String teamName=team_name.getText().toString();
        String  domain_id=Integer.toString(id_domain);
        String  topicid=Integer.toString(topic_id);
        String password=team_password.getText().toString();
        String no_of_member=noOfMembers;
        editor = sharedpreferences.edit();
        editor.putString(Config.TEAMNAME,teamName);
        editor.putString(Config.domain_id,domain_id);
        editor.putString(Config.topicid,topicid);
        editor.putString(Config.password,password);
        editor.commit();

    }

    private void loadDomainList() {
        if (!CheckConnectivity.isNetConnected(getContext()))
            Snackbar.make(reg_view, "No internet connection.", Snackbar.LENGTH_SHORT).show();
        else {
            callDomain = apiService.getDomians();
            final ProgressDialog loading = ProgressDialog.show(getContext(), "Fetching Data", "Please wait...", false, false);
            callDomain.enqueue(new Callback<DomainModel>() {
                @Override
                public void onResponse(Call<DomainModel> call, Response<DomainModel> response) {
                    DomainModel domain = response.body();
                     domainlist = domain.getData();
//                   Toast.makeText(getActivity(),domainlist.size(),Toast.LENGTH_LONG).show();
                    for (int i = 0; i<domainlist.size(); i++) {
                        domainList.add(domainlist.get(i).getDomainName());
                        domainsIDList.add(domainlist.get(i).getId());
                    }

                    loading.dismiss();
                    domainAdapter = new SpinnerAdapter(getContext(), domainList);
                    team_domain.setAdapter(domainAdapter);
                    team_domain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            id_domain= domainsIDList.get(i);
                            loadTopics(id_domain);
                   //         Toast.makeText(getActivity(), Integer.toString(id_domain), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }





                @Override
                public void onFailure(Call<DomainModel> call, Throwable t) {
                    Log.d("debugg", t.toString());
                    Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
                    loading.dismiss();
                }
            });
        }
    }


    public void loadTopics(int domainId) {
        topicsIDList.clear();
        topicsList.clear();
        if (topicsAdapter != null)
            topicsAdapter.notifyDataSetChanged();
        if (!CheckConnectivity.isNetConnected(getContext()))
            Snackbar.make(reg_view, "No internet connection.", Snackbar.LENGTH_SHORT).show();
        else {

                /*FetchData fetchData = new FetchData();
                fetchData.setArgs(Config.GET_TOPICS + domainId, this, LOAD_TOPICS);
                fetchData.execute();*/
                call = apiService.getTopics(Integer.toString(domainId));
                //  case LOAD_TOPICS:
                        /*jsonArray = new JSONArray(result);
                        topicsList.clear();
                        topicsIDList.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);
                            topicsIDList.add(jsonObject.getInt("TopicId"));
                            topicsList.add(jsonObject.getString("TopicName"));
                        }*/
                //List<Topics>  topicsList;
                final ProgressDialog loading = ProgressDialog.show(getContext(), "Fetching Data", "Please wait...", false, false);
                call.enqueue(new Callback<TopicModel>() {
                    @Override
                    public void onResponse(Call<TopicModel> call, Response<TopicModel> response) {
                        if(response.isSuccessful()) {
                            TopicModel topic = response.body();
                            Log.d("Success", Integer.toString(response.code()));
                            Log.d("Success", new Gson().toJson(response.body()));
//                            Log.d("Success", response.body().getTopicdata().toString());
                            List<in.silive.scrolls16.Models.Datum> topicsList1 = topic.getData();
                            // Toast.makeText(getActivity(),topicsList1.get(0).getTopicName(),Toast.LENGTH_LONG).show();
                            topicsList.clear();
                            topicsIDList.clear();
                            for (int i = 0; i < topicsList1.size(); i++) {
                                topicsList.add(topicsList1.get(i).getTopicName());
                                topicsIDList.add(topicsList1.get(i).getId());
                            }
                            loading.dismiss();

                            //Log.d("debugg",Integer.toString(topicsList1.size()));
                            //Toast.makeText(getContext(), Integer.toString(topicsList.size()), Toast.LENGTH_LONG).show();
                            topicsAdapter = new SpinnerAdapter(getContext(), topicsList);
                            team_topic.setAdapter(topicsAdapter);
                            team_topic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    topic_id = topicsIDList.get(i);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });
                        }
                        else
                        {
                            Log.d("unSuccess", new Gson().toJson(response.errorBody()));
                        }
                    }

                    @Override
                    public void onFailure(Call<TopicModel> call, Throwable t) {
                    loading.dismiss();
                    }


                });

            }
        }


        @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
            storeTeamData();
            if(checkValidation()) {
                callback.goToNextStep();
            }
            else
            {
                 Snackbar.make(reg_view,"Please Fill the Details Corectly",Snackbar.LENGTH_SHORT).show();
            }

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

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
    public boolean checkValidation()
    {flag=true;
     if(team_name.getText().toString().length()==0)
     {
         team_name.setError("TeamName is Compulsory");
         flag=false;
     }
     if(!team_password.getText().toString().equals(team_confirmPassword.getText().toString()))
     {
         team_confirmPassword.setError("Password Do not match");
         flag=false;
     }
     if(team_password.getText().toString().length()==0&&team_password.getText().toString().length()>=6)
     {
         team_password.setError("Invalid Password");
         flag=false;
     }
     return flag;
    }

    @Override
    public void onResume() {
        super.onResume();
        setRetainInstance(true);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(team_name.getText().toString().length()==0)
        {
            team_name.setError("TeamName is Compulsory");

        }
        if(!team_password.getText().toString().equals(team_confirmPassword.getText().toString()))
        {
            team_confirmPassword.setError("Password Do not match");

        }
        if(team_password.getText().toString().length()==0)
        {
            team_password.setError("Fill password");

        }

    }
}
