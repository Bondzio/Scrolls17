package in.silive.scrolls.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import in.silive.scrolls.Adapters.SpinnerAdapter;
import in.silive.scrolls.Listeners.FetchDataListener;
import in.silive.scrolls.Network.CheckConnectivity;
import in.silive.scrolls.Network.FetchData;
import in.silive.scrolls.R;
import in.silive.scrolls.Util.Config;
import in.silive.scrolls.Util.Keyboard;
import in.silive.scrolls.Util.Validator;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment implements FetchDataListener {
    private static final int GET_COLLEGE = 11;
    private static final int STUDENT_REG = 12;
    private static final int CREATE_COLLEGE = 13;
    private static final int LOAD_TOPICS = 14;
    private static final int TEAM_REGISTER = 15;
    public static String student_name, student_college_name, student_id, student_mob_no, student_mail, student_course;
    public static boolean student_accommodation = false;
    public static String name_of_team, name_member_one, name_member_two, name_member_three = "", id_member_one, id_member_two, id_member_three = "";
    public static String domain_of_team, topic_of_team, password_team;
    public static int no_of_teammembers, leader_of_team;
    public static int flag = 0, tflag = 0;
    static ArrayList<Integer> collegeIds = new ArrayList<>();
    static ArrayList<String> collegeNames = new ArrayList<>();
    static Register fragment;
    public int id_domain = 1;
    int student_year, collegeId;
    in.silive.scrolls.Adapters.SpinnerAdapter collegeListAdapter;
    int memberCount = 2;
    int leader_number = 0;
    ArrayList<String> topicsList = new ArrayList<>();
    ArrayList<Integer> topicsIDList = new ArrayList<>();
    String[] domainArray = new String[5], topicArray = new String[13];
    in.silive.scrolls.Adapters.SpinnerAdapter topicsAdapter, domainAdapter;
    ArrayList<String> searchList = new ArrayList<>();
    JSONObject jsonObject;
    //UI-elements
    boolean isNetConnectionAvailable;
    View reg_view;
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
    private ProgressDialog progressDialog;
    private int student_courseId;
    private int topic_id;
    private String mode = "";

    public Register() {
        // Required empty public constructor
    }

    public static Register getInstance() {
        if (fragment == null)
            fragment = new Register();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        isNetConnectionAvailable = CheckConnectivity.isNetConnected(getContext());
        if (reg_view == null) {
            reg_view = inflater.inflate(R.layout.fragment_registration, null, false);
            reg_individual = (LinearLayout) reg_view.findViewById(R.id.reg_individual);
            reg_team = (LinearLayout) reg_view.findViewById(R.id.reg_team);
            stud_name = (EditText) reg_view.findViewById(R.id.stud_name);
            stud_other_college = (EditText) reg_view.findViewById(R.id.stud_other_college);
            stud_other_college.setVisibility(View.GONE);
            stud_id = (EditText) reg_view.findViewById(R.id.stud_id);
            stud_mob_no = (EditText) reg_view.findViewById(R.id.stud_mob_no);
            stud_mail = (EditText) reg_view.findViewById(R.id.stud_mail);
            team_password = (EditText) reg_view.findViewById(R.id.team_password);
            member_one_id = (EditText) reg_view.findViewById(R.id.member_one_id);
            member_one_name = (EditText) reg_view.findViewById(R.id.member_one_name);
            member_two_id = (EditText) reg_view.findViewById(R.id.member_two_id);
            member_two_name = (EditText) reg_view.findViewById(R.id.member_two_name);
            member_three_id = (EditText) reg_view.findViewById(R.id.member_three_id);
            member_three_name = (EditText) reg_view.findViewById(R.id.member_three_name);
            team_name = (EditText) reg_view.findViewById(R.id.team_name);
            stud_accommodation = (CheckBox) reg_view.findViewById(R.id.stud_accommodation);
            member_three = (LinearLayout) reg_view.findViewById(R.id.member_three);
            two_members = (RadioButton) reg_view.findViewById(R.id.two_members);
            three_members = (RadioButton) reg_view.findViewById(R.id.three_members);
            no_of_team_members = (RadioGroup) reg_view.findViewById(R.id.no_of_team_members);
            no_of_team_members.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    if (i == R.id.three_members) {
                        member_three.setVisibility(View.VISIBLE);
                        leader_member_three.setVisibility(View.VISIBLE);
                        memberCount = 3;
                    } else {
                        member_three.setVisibility(View.GONE);
                        leader_member_three.setVisibility(View.GONE);
                        memberCount = 2;
                    }
                }
            });
            team_leader = (RadioGroup) reg_view.findViewById(R.id.team_leader);
            leader_member_one = (RadioButton) reg_view.findViewById(R.id.leader_member_one);
            leader_member_two = (RadioButton) reg_view.findViewById(R.id.leader_member_two);
            leader_member_three = (RadioButton) reg_view.findViewById(R.id.leader_member_three);
            team_leader.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (i == R.id.leader_member_one) {
                        leader_number = 0;
                    } else if (i == R.id.leader_member_two) {
                        leader_number = 1;
                    } else if (i == R.id.leader_member_three) {
                        leader_number = 2;
                    }
                }
            });
            individual_submit = (Button) reg_view.findViewById(R.id.individual_submit);
            individual_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Keyboard.close(getContext());
                    getStudData();
                }
            });
            submit_team_reg = (Button) reg_view.findViewById(R.id.submit_team_reg);
            submit_team_reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Keyboard.close(getContext());
                    getTeamData();
                }
            });
            stud_college = (Spinner) reg_view.findViewById(R.id.stud_college);
            //collegeListAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list_of_colleges);
            //stud_college.setAdapter(collegeListAdapter);
            stud_college.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (stud_college.getSelectedItem().equals("Other")) {
                        stud_other_college.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
       stud_college.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View view, MotionEvent motionEvent) {
               if (collegeIds.size()==0 && motionEvent.getAction() == MotionEvent.ACTION_UP)
                   loadCollegeList();
               return false;
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
            team_individual_tab = (TabLayout) reg_view.findViewById(R.id.team_individual_tab);
            TabLayout.Tab ind = team_individual_tab.newTab().setText("Individual");
            TabLayout.Tab team = team_individual_tab.newTab().setText("Team");
            team_individual_tab.addTab(ind, 0);
            team_individual_tab.addTab(team, 1);
            team_individual_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    int i = tab.getPosition();
                    switch (i) {
                        case 0:
                            reg_individual.setVisibility(View.VISIBLE);
                            reg_team.setVisibility(View.GONE);
                            break;
                        case 1:
                            reg_individual.setVisibility(View.GONE);
                            reg_team.setVisibility(View.VISIBLE);
                            if (topicsIDList.size() == 0)
                                loadTopics(id_domain);
                            break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            ind.select();
            team_domain = (Spinner) reg_view.findViewById(R.id.team_domain);
            team_domain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    id_domain = i + 1;
                    loadTopics(id_domain);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            team_topic = (Spinner) reg_view.findViewById(R.id.team_topic);
            team_topic.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (topicsIDList.size() == 0 && motionEvent.getAction() == MotionEvent.ACTION_UP
                            && id_domain!=0) {
                       loadTopics(id_domain);
                    }

                    return false;
                }
            });
        }
        return reg_view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (collegeIds.size() == 0) {
            loadCollegeList();
        }

    }

    public void loadCollegeList() {
        if (!CheckConnectivity.isNetConnected(getContext()))
            Snackbar.make(reg_view, "No internet connection.", Snackbar.LENGTH_SHORT).show();
        else {
            FetchData loadColleges = new FetchData();
            loadColleges.setArgs(Config.GET_COLLEGES, this, GET_COLLEGE);
            loadColleges.execute();
        }
    }

    public void getStudData() {
        Log.d("Scrolls", "getData called");
        flag = 0;
        student_name = stud_name.getText().toString();
        if (student_name.length() == 0) {
            flag = 1;
            stud_name.setError("Invalid name");
        }
        if (collegeIds.size() == 0) {
            loadCollegeList();
            Toast.makeText(getContext(), "Select College", Toast.LENGTH_LONG);
        }
        student_college_name = stud_other_college.getText().toString();
        student_id = stud_id.getText().toString();
        if (student_id.length() == 0 && stud_id.getVisibility() == View.VISIBLE) {
            flag = 1;
            stud_id.setError("Invalid Id");
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
        if (stud_accommodation.isChecked()) {
            student_accommodation = true;
        } else
            student_accommodation = false;
        if (flag == 0) {
            if (stud_other_college.getVisibility() != View.VISIBLE) {
                selfRegister();
            } else {
                createOtherCollege();
            }
        } else {
          /*  DialogInvalidDetails dialogInvalidDetails = new DialogInvalidDetails();
            dialogInvalidDetails.show(getFragmentManager(), "Invalid details");*/
            Snackbar.make(reg_view, "Incomlete Details.", Snackbar.LENGTH_SHORT).show();
        }
    }

    private void createOtherCollege() {
        if (!CheckConnectivity.isNetConnected(getContext()))
            Snackbar.make(reg_view, "No internet connection.", Snackbar.LENGTH_SHORT).show();
        else {
            try {
                JSONObject college = new JSONObject();
                college.put("CollegeName", stud_other_college.getText().toString());
                FetchData createCollege = new FetchData();
                createCollege.setArgs(Config.CREATE_COLLEGE, college.toString(), this, CREATE_COLLEGE);
                createCollege.execute();
            } catch (Exception e) {
                Snackbar.make(reg_view, "Something went wrong.", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    public void getTeamData() {
        tflag = 0;
        name_of_team = team_name.getText().toString();
        if (name_of_team.length() <= 3) {
            tflag = 1;
            team_name.setError("Invalid Team name");
        }
        name_member_one = member_one_name.getText().toString();
        if (name_member_one.length() <= 3) {
            tflag = 1;
            member_one_name.setError("Invalid name");
        }
        id_member_one = member_one_id.getText().toString();
        //check scrolls id valid
        name_member_two = member_two_name.getText().toString();
        if (name_member_two.length() <= 3) {
            tflag = 1;
            member_two_name.setError("Invalid name");
        }
        id_member_two = member_two_id.getText().toString();
        //check scrolls id valid
        name_member_three = member_three_name.getText().toString();
        if (name_member_three.length() <= 3 && memberCount == 3) {
            tflag = 1;
            member_three_name.setError("Invalid name");
        }
        id_member_three = member_three_id.getText().toString();
        //check scrolls id valid
        no_of_teammembers = no_of_team_members.getCheckedRadioButtonId();
        leader_of_team = team_leader.getCheckedRadioButtonId();

        domain_of_team = team_domain.getSelectedItem().toString();
        if (topicsList.size() > 0)
            topic_of_team = team_topic.getSelectedItem().toString();
        else {
            if (!CheckConnectivity.isNetConnected(getContext()))
                Snackbar.make(reg_view, "No internet connection.", Snackbar.LENGTH_SHORT).show();
            else
                loadTopics(id_domain);
            return;
        }

        password_team = team_password.getText().toString();
        if (password_team.length() < 8) {
            tflag = 1;
            team_password.setError("Password must have atleast 8 characters");
        }
        if (tflag == 0) {
            if (!CheckConnectivity.isNetConnected(getContext()))
                Snackbar.make(reg_view, "No internet connection.", Snackbar.LENGTH_SHORT).show();
            else
                teamRegister();
        } else {
            Snackbar.make(reg_view, "Incomplete Details.", Snackbar.LENGTH_SHORT).show();
        }
    }

    private void teamRegister() {
        if (!CheckConnectivity.isNetConnected(getContext()))
            Snackbar.make(reg_view, "No internet connection.", Snackbar.LENGTH_SHORT).show();
        else {
            jsonObject = new JSONObject();
            try {
                jsonObject.put("TeamName", ((EditText) reg_view.findViewById(R.id.team_name)).getText().toString());
                jsonObject.put("TotalMembers", memberCount);
                jsonObject.put("Member1RegId", Integer.parseInt(((EditText) reg_view.findViewById(R.id.member_one_id)).getText().toString()));
                jsonObject.put("Member2RegId", Integer.parseInt(((EditText) reg_view.findViewById(R.id.member_two_id)).getText().toString()));
                if (memberCount == 3)
                    jsonObject.put("Member3RegId", Integer.parseInt(((EditText) reg_view.findViewById(R.id.member_three_id)).getText().toString()));
                jsonObject.put("DomainId", id_domain);
                jsonObject.put("TopicId", topic_id);
                jsonObject.put("Password", ((EditText) reg_view.findViewById(R.id.team_password)).getText().toString());
                if (leader_number == 0)
                    jsonObject.put("TeamLeader", Integer.parseInt(((EditText) reg_view.findViewById(R.id.member_one_id)).getText().toString()));
                else if (leader_number == 1)
                    jsonObject.put("TeamLeader", Integer.parseInt(((EditText) reg_view.findViewById(R.id.member_two_id)).getText().toString()));

                else if (leader_number == 2)
                    jsonObject.put("TeamLeader", Integer.parseInt(((EditText) reg_view.findViewById(R.id.member_three_id)).getText().toString()));
                jsonObject.put("SynopsisName", "");
                Log.d("JSONOBject", jsonObject.toString());
                jsonObject.put("Source", "Android");

                FetchData fetchData = new FetchData();
                fetchData.setArgs(Config.TEAM_REGISTRATION, jsonObject.toString(), this, TEAM_REGISTER);
                fetchData.execute();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void selfRegister() {
        if (!CheckConnectivity.isNetConnected(getContext()))
            Snackbar.make(reg_view, "No internet connection.", Snackbar.LENGTH_SHORT).show();
        else {
            JSONObject user_reg_data = new JSONObject();
            try {
                user_reg_data.put("Name", student_name);
                user_reg_data.put("StudentId", student_id);
                String col = stud_college.getSelectedItem().toString();
                user_reg_data.put("CollegeId", collegeId);
                user_reg_data.put("EmailId", student_mail);
                user_reg_data.put("MobileNo", student_mob_no);
                user_reg_data.put("CourseId", student_courseId);
                user_reg_data.put("Year", student_year);
                user_reg_data.put("Source", "Android");
                if (((CheckBox) reg_view.findViewById(R.id.stud_accommodation)).isChecked())
                    user_reg_data.put("AccomodationRequired", 1);
                else
                    user_reg_data.put("AccomodationRequired", 0);

                Log.d("Scrolls", "Json Data" + user_reg_data.toString());
            } catch (Exception e) {
                Log.d("Scrolls", "Error : getStudData() in Register");

            }
            Log.d("FETCH_DATA", ((EditText) reg_view.findViewById(R.id.stud_other_college)).getText().toString() + jsonObject);
            FetchData regParticipant = new FetchData();
            regParticipant.setArgs(Config.SELF_REGISTRATION, user_reg_data.toString(), this, STUDENT_REG);
            regParticipant.execute();
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
            if (team_individual_tab.getSelectedTabPosition() == 1) {
                FetchData fetchData = new FetchData();
                fetchData.setArgs(Config.GET_TOPICS + domainId, this, LOAD_TOPICS);
                fetchData.execute();
            }
        }
    }

    @Override
    public void preExecute() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    @Override
    public void postExecute(String result, int id) throws JSONException {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
        if (!TextUtils.isEmpty(result)) {
            try {
                JSONObject jsonObject;
                JSONArray jsonArray;
                switch (id) {
                    case GET_COLLEGE:
                        jsonArray = new JSONArray(result);
                        collegeIds.clear();
                        collegeNames.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);
                            collegeIds.add(jsonObject.getInt("CollegeId"));
                            collegeNames.add(jsonObject.getString("CollegeName"));
                        }
                        collegeIds.add(-1);
                        collegeNames.add("Others");
                        collegeListAdapter = new SpinnerAdapter(getContext(), collegeNames);
                        stud_college.setAdapter(collegeListAdapter);
                        stud_college.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if (collegeIds.get(i) == -1) {
                                    (reg_view.findViewById(R.id.stud_other_college)).setVisibility(View.VISIBLE);
                                } else {
                                    (reg_view.findViewById(R.id.stud_other_college)).setVisibility(View.GONE);
                                    collegeId = collegeIds.get(i);
                                }
                                if (collegeIds.get(i) == 4) {
                                    (reg_view.findViewById(R.id.stud_id)).setVisibility(View.VISIBLE);
                                } else {
                                    (reg_view.findViewById(R.id.stud_id)).setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        break;

                    case STUDENT_REG:
                        try {
                            jsonObject = new JSONObject(result);
                            final int regId = jsonObject.getInt("RegId");
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("Registration successful")
                                    .setMessage("Congratulations " + jsonObject.getString("Name") + " your registration is successful.\n" +
                                            "Your registration id is " + regId + " .\n" +
                                            "To continue click OK")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            SharedPreferences sharedpreferences = getActivity().getSharedPreferences(Config.PREFERENCES, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedpreferences.edit();
                                            editor.putString(Config.individual_id, "" + regId);
                                            editor.commit();
                                        }
                                    })
                                    .setCancelable(false)
                                    //       .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        } catch (Exception e) {
                            if (result.contains("Participant already registered")) {
                                Snackbar.make(reg_view, result, Snackbar.LENGTH_LONG).show();
                            } else {
                                Snackbar.make(reg_view, "Something went wrong.", Snackbar.LENGTH_LONG).show();
                            }
                        }

                        break;
                    case CREATE_COLLEGE:
                        jsonObject = new JSONObject(result);
                        collegeId = jsonObject.getInt("CollegeId");
                        selfRegister();
                        break;
                    case LOAD_TOPICS:
                        jsonArray = new JSONArray(result);
                        topicsList.clear();
                        topicsIDList.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);
                            topicsIDList.add(jsonObject.getInt("TopicId"));
                            topicsList.add(jsonObject.getString("TopicName"));
                        }
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
                        break;
                    case TEAM_REGISTER:
                        try {
                            jsonObject = new JSONObject(result);
                            if (jsonObject.has("TeamId")) {
                                final int team_id = jsonObject.getInt("TeamId");
                                new AlertDialog.Builder(getActivity())
                                        .setTitle("Registration successful")
                                        .setMessage("Team ID : " + jsonObject.getInt("TeamId") + "\n" +
                                                "Team name : " + jsonObject.getString("TeamName") +
                                                "\nMember IDs : \n" +
                                                "1. " + jsonObject.getString("Member1RegId") + ".\n" +
                                                "2. " + jsonObject.getString("Member2RegId") + ".\n" +
                                                (memberCount == 3 ? "3. " + jsonObject.getString("Member3RegId") : "") +
                                                "\nTo continue click OK")
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {

                                                SharedPreferences sharedpreferences = getActivity().getSharedPreferences(Config.PREFERENCES, Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                                editor.putString(Config.team_id, "" + team_id);
                                                editor.commit();
                                                if (mode.equalsIgnoreCase("upload")) {

                                                }
                                            }
                                        })
                                        .setCancelable(false)
                                        //       .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            } else if (jsonObject.has("Message")) {
                                Snackbar.make(reg_view, jsonObject.getString("Message"), Snackbar.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (result.contains("Participants are already registered in other team")) {
                                Snackbar.make(reg_view, result, Snackbar.LENGTH_LONG).show();
                            } else {
                                Snackbar.make(reg_view, "Something went wrong.", Snackbar.LENGTH_LONG).show();
                            }
                        }
                }
            } catch (Exception e) {
                Snackbar.make(reg_view, "Something went wrong.", Snackbar.LENGTH_SHORT).show();
            }

        } else {
            Snackbar.make(reg_view, R.string.could_not_connect, Snackbar.LENGTH_SHORT).show();
            android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(getContext())
                    .setTitle("Error")
                    .setMessage(R.string.could_not_connect)
                    .setCancelable(false)
                    .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert);

            if (collegeIds.size() == 0 && id == GET_COLLEGE) {
                dialog.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        loadCollegeList();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            } else if (topicsIDList.size() == 0 && id == LOAD_TOPICS) {
                dialog.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        loadTopics(id_domain);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        }

    }


    public void setMode(String mode) {
        this.mode = mode;
    }
}