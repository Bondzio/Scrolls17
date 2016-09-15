package in.silive.scrolls.Util;

/**
 * Created by AKG002 on 31-08-2016.
 */
public class Config {
    public static final String LOG = "Scrolls";
    public static final String SCROLLS_WEBSITE = "http://www.akgec-scrolls.com";
    public static final String SILIVE_WEBSITE = "http://www.silive.in";
    public static final String KEY_FRAGMENT = "fragment";
    public static final String KEY_REGISTER = "Register";
    public static final String KEY_UPLOAD = "Upload Doc";
    public static final String KEY_QUERY = "Query Us";
    //// TODO:correct sample doc link on deploy
    public static final String SAMPLE_DOC_URL = "http://akgec-scrolls.com/test/asset/Synopsis.pdf";
    public static final String QUERY_URL = "http://akgec-scrolls.com/rest/api/Queries/RegisterQuery";
    public static final String SP_KEY = "scrolls";
    public static final String GCM = "gcm";


    public static String GET = "GET";
    public static String POST = "POST";
    public static String BASE_URL = "http://akgec-scrolls.com/rest/";
    public static  String UPLOAD_DOC = BASE_URL+"api/Teams/UploadFile";
    public static String GET_COURSES = BASE_URL +"api/Domains/GetCourses";
    public static String CREATE_COLLEGE = BASE_URL +"api/Colleges/CreateCollege";
    public static String GET_COLLEGES = BASE_URL +"api/Colleges/GetColleges";
    public static String ID_BY_EMAIL =  BASE_URL +"api/Participants/GetScrollsIdByEmail?email=";
    public static String Team_LOGIN =  BASE_URL +"api/Teams/IsTeamValid";
    public static String GET_DOMAINS = BASE_URL +"api/Domains/GetDomains";
    public static String GET_TOPICS = BASE_URL +"api/Domains/GetTopics?domainId=";
    public static String CHECK_IS_PHONE_NUMBER_REGISTERED = "isPhoneNoAlreadyRegistered";
    public static String SELF_REGISTRATION =  BASE_URL +"api/Participants/CreateParticipant";
    public static String IS_EMAIL_ALREADY_REGISTERED =  BASE_URL +"api/Participants/IsEmailAlreadyRegistered?email=";
    public static String PREFERENCES = "PREFERENCES";
    public static String individual_id = "individual_id";
    public static String team_id = "team_id";
    public static String CHECK_TEAM_NAME_AVAILABLE = "IsTeamNameAvailable";
    public static String ISPERSONALREADYINTEAM =  BASE_URL +"api/Participants/IsParticipantAlreadyInATeam?scrollsId=";
    public static String TRUE = "true";
    public static String FALSE = "false";
    public static String TEAM_REGISTRATION =  BASE_URL +"api/Teams/CreateTeam";

    public static String IS_PHONE_NUMBER_REGISTERED = BASE_URL +"api/Participants/IsPhoneNoAlreadyRegistered?number=";
    public static final String GCM_URL = BASE_URL + "api/GCM/StoreGCMId=";

}
