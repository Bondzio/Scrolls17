package in.silive.scrolls16.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
import in.silive.scrolls16.Util.Keyboard;
import in.silive.scrolls16.models.CollegeModel;
import in.silive.scrolls16.models.SelfRegister;
import in.silive.scrolls16.models.Topics;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 12/9/17.
 */

public class TeamRegister extends Fragment implements BlockingStep {
    public static String student_name, student_college_name, student_id, student_mob_no, student_mail, student_course;
    public static boolean student_accommodation = false;
    public static String name_of_team, name_member_one, name_member_two, name_member_three = "", id_member_one, id_member_two, id_member_three = "";
    public static String domain_of_team, topic_of_team, password_team;
    public static int no_of_teammembers, leader_of_team;
    public static int flag = 0, tflag = 0;
    static ArrayList<Integer> collegeIds = new ArrayList<>();
    static ArrayList<String> collegeNames = new ArrayList<>();
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
    Call<List<Topics>> call;
    Call<List<CollegeModel>> callCollege;
    private String mode = "";
    RetrofitApiInterface apiService;
    private Call<SelfRegister> callRegister;
    View reg_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        reg_view = inflater.inflate(R.layout.team_reg, container, false);
        reg_individual = (LinearLayout) reg_view.findViewById(R.id.reg_individual);
        reg_team = (LinearLayout) reg_view.findViewById(R.id.reg_team);
        team_password = (EditText) reg_view.findViewById(R.id.team_password);
        team_name = (EditText) reg_view.findViewById(R.id.team_name);
        no_of_team_members = (RadioGroup) reg_view.findViewById(R.id.no_of_team_members);
        no_of_team_members.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.three_members) {
                    member_three.setVisibility(View.VISIBLE);
                    leader_member_three.setVisibility(View.VISIBLE);
                    // memberCount = 3;
                } else {
                    member_three.setVisibility(View.GONE);
                    leader_member_three.setVisibility(View.GONE);
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
                        && id_domain != 0) {
                    loadTopics(id_domain);
                }

                return false;
            }
        });
        apiService =
                ApiClient.getClient().create(RetrofitApiInterface.class);
    
    //initialize your UI

        return reg_view;
    }

    public void loadCollegeList() {
        if (!CheckConnectivity.isNetConnected(getContext()))
            Snackbar.make(reg_view, "No internet connection.", Snackbar.LENGTH_SHORT).show();
        else {
         /*   FetchData loadColleges = new FetchData();
            loadColleges.setArgs(Config.GET_COLLEGES, this, GET_COLLEGE);
            loadColleges.execute();*/
            callCollege=apiService.getCollege();
            callCollege.enqueue(new Callback<List<CollegeModel>>() {
                @Override
                public void onResponse(Call<List<CollegeModel>> call, Response<List<CollegeModel>> response) {
                    List<CollegeModel> college = response.body();
                    /*case GET_COLLEGE:
                    jsonArray = new JSONArray(result);
                    collegeIds.clear();
                    collegeNames.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        collegeIds.add(jsonObject.getInt("CollegeId"));
                        collegeNames.add(jsonObject.getString("CollegeName"));
                    }*/
                    for(int i=0;i<college.size();i++)
                    {
                        collegeIds.add(college.get(i).getCollegeId());
                        collegeNames.add(college.get(i).getCollegeName());
                    }
                    collegeIds.add(-1);
                    collegeNames.add("Others");
                    SpinnerAdapter collegeListAdapter = new SpinnerAdapter(getContext(), collegeNames);
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
                /*FetchData fetchData = new FetchData();
                fetchData.setArgs(Config.GET_TOPICS + domainId, this, LOAD_TOPICS);
                fetchData.execute();*/
                            call = apiService.getTopics(domainId);
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
                            call.enqueue(new Callback<List<Topics>>() {
                                @Override
                                public void onResponse(Call<List<Topics>> call, Response<List<Topics>> response) {
                                    List<Topics> topicsList1=response.body();
                                    topicsList.clear();
                                    topicsIDList.clear();
                                    for (int i=0;i<topicsList1.size();i++) {
                                        topicsList.add(topicsList1.get(i).getTopicName());
                                        topicsIDList.add(topicsList1.get(i).getTopicId());
                                    }
                                    loading.dismiss();

                                    //Log.d("debugg",Integer.toString(topicsList1.size()));
                                    Toast.makeText(getContext(),Integer.toString(topicsList.size()),Toast.LENGTH_LONG).show();
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

                                @Override
                                public void onFailure(Call<List<Topics>> call, Throwable t) {

                                }


                            });

                        }
                    }
                }

                @Override
                public void onFailure(Call<List<CollegeModel>> call, Throwable t) {

                }
            });
        }
    }

        @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        callback.goToNextStep();
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
}
