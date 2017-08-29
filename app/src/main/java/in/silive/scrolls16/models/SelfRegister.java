package in.silive.scrolls16.models;

/**
 * Created by root on 29/8/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelfRegister {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("StudentId")
    @Expose
    private String studentId;
    @SerializedName("CollegeId")
    @Expose
    private int collegeId;
    @SerializedName("EmailId")
    @Expose
    private String emailId;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("CourseId")
    @Expose
    private int courseId;
    @SerializedName("Year")
    @Expose
    private int year;
    @SerializedName("Source")
    @Expose
    private String source;
    @SerializedName("AccomodationRequired")
    @Expose
    private int accomodationRequired;

    /**
     * No args constructor for use in serialization
     *
     */
    public SelfRegister() {
    }

    /**
     * @param name
     * @param studentId
     * @param collegeId
     * @param emailId
     * @param mobileNo
     * @param courseId
     * @param year
     * @param source
     * @param accomodationRequired
     */
    public SelfRegister(String name, String studentId, int collegeId, String emailId, String mobileNo, int courseId, int year, String source, int accomodationRequired) {
        super();
        this.name = name;
        this.studentId = studentId;
        this.collegeId = collegeId;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
        this.courseId = courseId;
        this.year = year;
        this.source = source;
        this.accomodationRequired = accomodationRequired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getAccomodationRequired() {
        return accomodationRequired;
    }

    public void setAccomodationRequired(int accomodationRequired) {
        this.accomodationRequired = accomodationRequired;
    }

}