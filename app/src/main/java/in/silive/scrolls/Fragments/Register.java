package in.silive.scrolls.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import in.silive.scrolls.Listeners.FetchDataListener;
import in.silive.scrolls.Network.FetchData;
import in.silive.scrolls.R;
import in.silive.scrolls.Util.Config;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {
    public static String student_name, student_college_name, student_id, student_mob_no, student_mail, student_course, student_year;
    public static boolean student_accommodation = false;
    public static String name_of_team, name_member_one, name_member_two, name_member_three, id_member_one, id_member_two, id_member_three;
    public static String domain_of_team, topic_of_team, password_team;
    public static int no_of_teammembers, leader_of_team;
    ArrayList<String> list_of_colleges = new ArrayList<>();
    ArrayAdapter<String> collegeListAdapter;
    //UI-elements
    LinearLayout reg_individual, reg_team, member_three;
    TabLayout team_individual_tab;
    EditText stud_name, stud_other_college, stud_id, stud_mob_no, stud_mail, team_password;
    EditText team_name, member_one_id, member_one_name, member_two_id, member_two_name, member_three_id, member_three_name;
    Button individual_submit, submit_team_reg;
    Spinner stud_college, stud_course, stud_year, team_domain, team_topic;
    CheckBox stud_accommodation;
    RadioGroup team_leader, no_of_team_members;
    RadioButton leader_member_one, leader_member_two, leader_member_three, two_members, three_members;

    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        reg_individual = (LinearLayout) view.findViewById(R.id.reg_individual);
        reg_team = (LinearLayout) view.findViewById(R.id.reg_team);
        stud_name = (EditText) view.findViewById(R.id.stud_name);
        stud_other_college = (EditText) view.findViewById(R.id.stud_other_college);
        stud_other_college.setVisibility(View.GONE);
        stud_id = (EditText) view.findViewById(R.id.stud_id);
        stud_mob_no = (EditText) view.findViewById(R.id.stud_mob_no);
        stud_mail = (EditText) view.findViewById(R.id.stud_mail);
        team_password = (EditText) view.findViewById(R.id.team_password);
        member_one_id = (EditText) view.findViewById(R.id.member_one_id);
        member_one_name = (EditText) view.findViewById(R.id.member_one_name);
        member_two_id = (EditText) view.findViewById(R.id.member_two_id);
        member_two_name = (EditText) view.findViewById(R.id.member_two_name);
        member_three_id = (EditText) view.findViewById(R.id.member_three_id);
        member_three_name = (EditText) view.findViewById(R.id.member_three_name);
        team_name = (EditText) view.findViewById(R.id.team_name);
        stud_accommodation = (CheckBox) view.findViewById(R.id.stud_accommodation);
        team_leader = (RadioGroup) view.findViewById(R.id.team_leader);
        team_leader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int leader_id = team_leader.getCheckedRadioButtonId();
            }
        });
        member_three = (LinearLayout) view.findViewById(R.id.member_three);
        leader_member_one = (RadioButton) view.findViewById(R.id.leader_member_one);
        leader_member_two = (RadioButton) view.findViewById(R.id.leader_member_two);
        no_of_team_members = (RadioGroup) view.findViewById(R.id.no_of_team_members);
        no_of_team_members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = no_of_team_members.getCheckedRadioButtonId();
                if (id == 1) {
                    member_three.setVisibility(View.VISIBLE);
                }
            }
        });
        two_members = (RadioButton) view.findViewById(R.id.two_members);
        three_members = (RadioButton) view.findViewById(R.id.three_members);
        leader_member_three = (RadioButton) view.findViewById(R.id.leader_member_three);
        individual_submit = (Button) view.findViewById(R.id.individual_submit);
        individual_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStudData();
            }
        });
        submit_team_reg = (Button) view.findViewById(R.id.submit_team_reg);
        submit_team_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTeamData();
            }
        });
        stud_college = (Spinner) view.findViewById(R.id.stud_college);
        collegeListAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list_of_colleges);
        stud_college.setAdapter(collegeListAdapter);
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
        stud_course = (Spinner) view.findViewById(R.id.stud_course);
        stud_course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stud_year = (Spinner) view.findViewById(R.id.stud_year);
        stud_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        team_individual_tab = (TabLayout) view.findViewById(R.id.team_individual_tab);
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
        team_domain = (Spinner) view.findViewById(R.id.team_domain);
        team_topic = (Spinner) view.findViewById(R.id.team_topic);
        FetchData fetchData = new FetchData();
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
                    collegeListAdapter.notifyDataSetChanged();
                } catch (Exception e) {

                }

            }
        });
        return view;
    }

    public void getStudData() {
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

}