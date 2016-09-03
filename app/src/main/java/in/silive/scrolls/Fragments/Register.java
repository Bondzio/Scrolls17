package in.silive.scrolls.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import org.json.JSONObject;

import in.silive.scrolls.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {
    public static String student_name, student_college_name, student_id, student_mob_no, student_mail, student_course, student_year;
    //UI-elements
    LinearLayout reg_individual, reg_team;
    TabLayout team_individual_tab;
    EditText stud_name, stud_other_college, stud_id, stud_mob_no, stud_mail;
    Button individual_submit;
    Spinner stud_college, stud_course, stud_year;

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
        individual_submit = (Button) view.findViewById(R.id.individual_submit);
        individual_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStudData();
            }
        });
        stud_college = (Spinner) view.findViewById(R.id.stud_college);
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
        JSONObject user_reg_data = new JSONObject();
        try {
            user_reg_data.put("STUD_NAME", student_name);
            user_reg_data.put("STUD_ID", student_id);
            user_reg_data.put("STUD_COLLEGE", student_college_name);
            user_reg_data.put("STUD_MAIL", student_mail);
            user_reg_data.put("STUD_PHONE", student_mob_no);
            user_reg_data.put("STUD_COURSE", student_course);
            user_reg_data.put("STUD_YEAR", student_year);


        } catch (Exception e) {
            Log.d("Scrolls", "Error : getStudData() in Register");

        }


    }

}