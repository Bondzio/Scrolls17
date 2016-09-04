package in.silive.scrolls.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import in.silive.scrolls.Network.CheckConnectivity;
import in.silive.scrolls.Network.FetchDataForLists;
import in.silive.scrolls.Network.NetworkResponseListener;
import in.silive.scrolls.R;
import in.silive.scrolls.Util.Config;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment implements NetworkResponseListener{
    public static String student_name, student_college_name, student_id, student_mob_no, student_mail, student_course, student_year;
    public static boolean student_accommodation = false;
    public static String name_of_team, name_member_one, name_member_two, name_member_three, id_member_one, id_member_two, id_member_three;
    public static String domain_of_team, topic_of_team, password_team;
    public static int no_of_teammembers, leader_of_team;
    ArrayList<String> list_of_colleges = new ArrayList<>();
    ArrayList<String> collegeIDarray = new ArrayList<>();
    in.silive.scrolls.Adapters.SpinnerAdapter collegeListAdapter;
    String inProgress;
    ArrayList<String> searchList = new ArrayList<>();
    FetchDataForLists fetchdataforLists;
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
    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        isNetConnectionAvailable = CheckConnectivity.isNetConnected(getContext());

            reg_view = inflater.inflate(R.layout.fragment_registration, container, false);
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
        team_leader = (RadioGroup) reg_view.findViewById(R.id.team_leader);
        team_leader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int leader_id = team_leader.getCheckedRadioButtonId();
            }
        });
        member_three = (LinearLayout) reg_view.findViewById(R.id.member_three);
        leader_member_one = (RadioButton) reg_view.findViewById(R.id.leader_member_one);
        leader_member_two = (RadioButton) reg_view.findViewById(R.id.leader_member_two);
        no_of_team_members = (RadioGroup) reg_view.findViewById(R.id.no_of_team_members);
        no_of_team_members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = no_of_team_members.getCheckedRadioButtonId();
                if (id == 1) {
                    member_three.setVisibility(View.VISIBLE);
                }
            }
        });
        two_members = (RadioButton) reg_view.findViewById(R.id.two_members);
        three_members = (RadioButton) reg_view.findViewById(R.id.three_members);
        leader_member_three = (RadioButton) reg_view.findViewById(R.id.leader_member_three);
        individual_submit = (Button) reg_view.findViewById(R.id.individual_submit);
        individual_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStudData();
            }
        });
        submit_team_reg = (Button) reg_view.findViewById(R.id.submit_team_reg);
        submit_team_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        team_individual_tab.addTab(team_individual_tab.newTab().setText("Individual"), 0);
        team_individual_tab.addTab(team_individual_tab.newTab().setText("Team"), 1);
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
        team_domain = (Spinner) reg_view.findViewById(R.id.team_domain);
        team_topic = (Spinner) reg_view.findViewById(R.id.team_topic);
        fetchdataforLists = new FetchDataForLists();
        try {
            inProgress = "GETALLCOLLEGES";
            searchList = new ArrayList<>();
            searchList.add("CollegeId");
            searchList.add("CollegeName");
            fetchdataforLists.setNrl(this);
            fetchdataforLists.setURL(Config.GET_COLLEGES);
            fetchdataforLists.setSearchForIds(false);
            fetchdataforLists.setType_of_request(Config.GET);
            fetchdataforLists.setSearchList(searchList);
            fetchdataforLists.execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        /*FetchData fetchData = new FetchData();
        fetchData.setArgs(Config.LIST_OF_COLLEGE, new FetchDataListener() {
            @Override
            public void preExecute() {

            }

            @Override
            public void postExecute(String result, int id) throws JSONException {
                try {
                    JSONArray college_list = new JSONArray(result);
                    for (int i = 0; i < college_list.length(); i++) {
                        JSONObject college = college_list.getJSONObject(i);
                        list_of_colleges.add(college.getString("CollegeName"));

                    }
                    //collegeListAdapter.notifyDataSetChanged();
                } catch (Exception e) {

                }

            }
        });*/
        return reg_view;
    }

    public void getStudData() {
        Log.d("Scrolls","getData called");
        student_name = stud_name.getText().toString();
        student_college_name = stud_other_college.getText().toString();
        student_id = stud_id.getText().toString();
        student_mail = stud_mail.getText().toString();
        student_mob_no = stud_mob_no.getText().toString();
        student_course = stud_course.getSelectedItem().toString();
        student_year = stud_year.getSelectedItem().toString();
        if (stud_accommodation.isChecked()) {
            student_accommodation = true;
        }
        JSONObject user_reg_data = new JSONObject();
        try {
            user_reg_data.put("STUD_NAME", student_name);
            user_reg_data.put("STUD_ID", student_id);
            user_reg_data.put("STUD_COLLEGE", student_college_name);
            user_reg_data.put("STUD_MAIL", student_mail);
            user_reg_data.put("STUD_PHONE", student_mob_no);
            user_reg_data.put("STUD_COURSE", student_course);
            user_reg_data.put("STUD_YEAR", student_year);
            user_reg_data.put("STUD_ACCOMMODATION", student_accommodation);


        } catch (Exception e) {
            Log.d("Scrolls", "Error : getStudData() in Register");

        }


    }

    public void getTeamData() {
        name_of_team = team_name.getText().toString();
        name_member_one = member_one_name.getText().toString();
        id_member_one = member_one_id.getText().toString();
        name_member_two = member_two_name.getText().toString();
        id_member_two = member_two_id.getText().toString();
        name_member_three = member_three_name.getText().toString();
        id_member_three = member_three_id.getText().toString();
        no_of_teammembers = no_of_team_members.getCheckedRadioButtonId();
        leader_of_team = team_leader.getCheckedRadioButtonId();
        domain_of_team = team_domain.getSelectedItem().toString();
        topic_of_team = team_topic.getSelectedItem().toString();
        password_team = team_password.getText().toString();

        JSONObject team_reg_data = new JSONObject();
        try {
            team_reg_data.put("TEAM_NAME", name_of_team);
            team_reg_data.put("TEAM_MEMBER_ONE_NAME", name_member_one);
            team_reg_data.put("TEAM_MEMBER_ONE_ID", id_member_one);
            team_reg_data.put("TEAM_MEMBER_TWO_NAME", name_member_two);
            team_reg_data.put("TEAM_MEMBER_TWO_ID", id_member_two);
            team_reg_data.put("TEAM_MEMBER_THREE_NAME", name_member_three);
            team_reg_data.put("TEAM_MEMBER_THREE_ID", id_member_three);
            team_reg_data.put("TEAM_NO_OF_MEMBERS", no_of_teammembers);
            team_reg_data.put("TEAM_LEADER", leader_of_team);
            team_reg_data.put("TEAM_DOMAIN", domain_of_team);
            team_reg_data.put("TEAM_TOPIC", topic_of_team);
            team_reg_data.put("TEAM_PASSWORD", password_team);

        } catch (Exception e) {

        }
    }

    @Override
    public void beforeRequest() throws MalformedURLException {
        Log.d("Scrolls","before request called");
        progressBar = new ProgressBar(getContext());
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void postRequest(Object result) throws MalformedURLException {
        Log.d("Scrolls","post request called");
        progressBar.setVisibility(View.GONE);
        final ArrayList<HashMap<String, String>> resultList = (ArrayList<HashMap<String, String>>) result;
        //Toast.makeText(getActivity(), "Length " + resultList.size() + " " + inProgress+" "+resultList.get(0).get(searchlIST.get(0)), Toast.LENGTH_SHORT).show();
        if (inProgress.equals(Config.CHECK_IS_PHONE_NUMBER_REGISTERED)) {
            if (resultList.size() > 0) {
                if (resultList.get(0).get(searchList.get(0)).equalsIgnoreCase("false")) {
                    Toast.makeText(getActivity(), "Mobile Number is Not already Registered.", Toast.LENGTH_SHORT).show();
                    checkEmail();
                } else {
                    Toast.makeText(getActivity(), "Mobile Number is already registered.Please try other.", Toast.LENGTH_SHORT).show();
                }
            } else
                Toast.makeText(getActivity(), "Phone number already registered", Toast.LENGTH_SHORT).show();
        } else if (inProgress.equals("CHECK_EMAIL")) {
            if (resultList.size() > 0) {
                if ((resultList.get(0).get(searchList.get(0))).equals("false")) {
                    try {
                        if (((reg_view.findViewById(R.id.stud_other_college)).getVisibility()) == (View.VISIBLE))
                            checkOtherCollege();
                        else
                            selfRegister();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getActivity(), "Email already Registered", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (inProgress.equals("GETALLCOLLEGES")) {
            for (int i = 0; i < resultList.size(); i++) {
                Log.d("Adding Colleges", resultList.get(i).get(searchList.get(1)));
                list_of_colleges.add(resultList.get(i).get(searchList.get(1)));
                collegeIDarray.add(resultList.get(i).get(searchList.get(0)));
            }
            list_of_colleges.add("Others");
            collegeListAdapter = new in.silive.scrolls.Adapters.SpinnerAdapter(getActivity(),list_of_colleges);
            stud_college.setAdapter(collegeListAdapter);
            stud_college.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == (list_of_colleges.size() - 1)) {
                        (reg_view.findViewById(R.id.stud_other_college)).setVisibility(View.VISIBLE);
                    } else {
                        (reg_view.findViewById(R.id.stud_other_college)).setVisibility(View.GONE);

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        } else if (inProgress.equals("CREATE_NEW_COLLEGE")) {
            if (resultList.size() > 0) {
                Toast.makeText(getActivity(), "College Added", Toast.LENGTH_SHORT).show();
                list_of_colleges.set(list_of_colleges.size() - 1, resultList.get(0).get(searchList.get(0)));
                list_of_colleges.add("Others");

                collegeListAdapter.notifyDataSetChanged();

                try {
                    selfRegister();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (inProgress.equals("SELF_REGISTER")) {
            if (resultList.size() > 0) {
                final String id = resultList.get(0).get(searchList.get(0));
                new AlertDialog.Builder(getActivity())
                        .setTitle("SuccessFull Registration")
                        .setMessage("Congratulations" + resultList.get(0).get(searchList.get(1)) + " your registration is successful.\n Your" +
                                "registration id is " + resultList.get(0).get(searchList.get(0)) +
                                "To continue click OK")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                SharedPreferences sharedpreferences = getActivity().getSharedPreferences(Config.PREFERENCES, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString(Config.individual_id, id);
                                editor.commit();
                            }
                        })

                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else {
                Toast.makeText(getActivity(), "Request Unsuccessfu", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void checkNumber() {
        try {
            inProgress = Config.CHECK_IS_PHONE_NUMBER_REGISTERED;
            searchList = new ArrayList<>();
            searchList.add(Config.CHECK_IS_PHONE_NUMBER_REGISTERED);
            //Log.d("FETCH_DATA", ((EditText) rootView.findViewById(R.id.otherCollegeText)).getText().toString() + jsonObject);
            fetchdataforLists = null;
            fetchdataforLists = new FetchDataForLists();
            fetchdataforLists.setType_of_request(Config.GET);
            fetchdataforLists.setURL(Config.IS_PHONE_NUMBER_REGISTERED + ((EditText) reg_view.findViewById(R.id.stud_mob_no)).getText().toString()
            );
            fetchdataforLists.setNrl(this);
            fetchdataforLists.setSearchList(searchList);
            fetchdataforLists.execute();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void checkEmail() {
        try {
            inProgress = "CHECK_EMAIL";
            searchList.clear();
            searchList.add("IsEmailAlreadyRegistered");
            //Log.d("FETCH_DATA", ((EditText) rootView.findViewById(R.id.otherCollegeText)).getText().toString() + jsonObject);
            fetchdataforLists = null;
            fetchdataforLists = new FetchDataForLists();
            fetchdataforLists.setType_of_request(Config.GET);
            fetchdataforLists.setURL(Config.IS_EMAIL_ALREADY_REGISTERED + ((EditText) reg_view.findViewById(R.id.stud_mail)).getText().toString()
            );
            fetchdataforLists.setNrl(this);
            fetchdataforLists.setSearchList(searchList);
            fetchdataforLists.execute();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void checkOtherCollege() throws JSONException, MalformedURLException {
        searchList = new ArrayList<>();
        searchList.add("CollegeName");
        inProgress = "CREATE_NEW_COLLEGE";
        jsonObject = new JSONObject();
        jsonObject.put("CollegeId", "1");
        jsonObject.put("CollegeName", ((EditText) reg_view.findViewById(R.id.stud_other_college)).getText().toString());
        Log.d("FETCH_DATA", ((EditText) reg_view.findViewById(R.id.stud_other_college)).getText().toString() + jsonObject);
        fetchdataforLists = null;
        fetchdataforLists = new FetchDataForLists();
        fetchdataforLists.setType_of_request(Config.POST);
        fetchdataforLists.setURL(Config.CREATE_COLLEGE);
        fetchdataforLists.setNrl(this);
        fetchdataforLists.setJson(jsonObject);
        fetchdataforLists.setSearchList(searchList);
        fetchdataforLists.execute();

    }
    public void selfRegister() throws JSONException, MalformedURLException {
        inProgress = "SELF_REGISTER";
        searchList = new ArrayList<>();
        searchList.add("Name");
        JSONObject user_reg_data = new JSONObject();
        try {
            user_reg_data.put("STUD_NAME", student_name);
            user_reg_data.put("STUD_ID", student_id);
            user_reg_data.put("STUD_COLLEGE", student_college_name);
            user_reg_data.put("STUD_MAIL", student_mail);
            user_reg_data.put("STUD_PHONE", student_mob_no);
            user_reg_data.put("STUD_COURSE", student_course);
            user_reg_data.put("STUD_YEAR", student_year);
            if (((CheckBox) reg_view.findViewById(R.id.stud_accommodation)).isChecked())
                user_reg_data.put("AccomodationRequired", 1);
            else
                user_reg_data.put("AccomodationRequired", 0);


        } catch (Exception e) {
            Log.d("Scrolls", "Error : getStudData() in Register");

        }
        Log.d("FETCH_DATA", ((EditText) reg_view.findViewById(R.id.stud_other_college)).getText().toString() + jsonObject);
        fetchdataforLists = null;
        fetchdataforLists = new FetchDataForLists();
        fetchdataforLists.setType_of_request(Config.POST);
        fetchdataforLists.setURL(Config.SELF_REGISTRATION);
        fetchdataforLists.setNrl(this);
        fetchdataforLists.setJson(user_reg_data);
        fetchdataforLists.setSearchList(searchList);
        fetchdataforLists.execute();
    }

    public class TeamRegistration implements NetworkResponseListener{

        @Override
        public void beforeRequest() throws MalformedURLException {

        }

        @Override
        public void postRequest(Object result) throws MalformedURLException {

        }
    }

}