package models;

/**
 * Created by root on 27/8/17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamMember {

    @SerializedName("RegId")
    @Expose
    private Integer regId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("CourseId")
    @Expose
    private Integer courseId;
    @SerializedName("Year")
    @Expose
    private Integer year;
    @SerializedName("CollegeId")
    @Expose
    private Integer collegeId;
    @SerializedName("StudentId")
    @Expose
    private String studentId;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("EmailId")
    @Expose
    private String emailId;
    @SerializedName("AccomodationRequired")
    @Expose
    private Boolean accomodationRequired;
    @SerializedName("Source")
    @Expose
    private Object source;

    /**
     * No args constructor for use in serialization
     *
     */
    public TeamMember() {
    }

    /**
     *
     * @param collegeId
     * @param emailId
     * @param source
     * @param studentId
     * @param name
     * @param year
     * @param courseId
     * @param accomodationRequired
     * @param mobileNo
     * @param regId
     */
    public TeamMember(Integer regId, String name, Integer courseId, Integer year, Integer collegeId, String studentId, String mobileNo, String emailId, Boolean accomodationRequired, Object source) {
        super();
        this.regId = regId;
        this.name = name;
        this.courseId = courseId;
        this.year = year;
        this.collegeId = collegeId;
        this.studentId = studentId;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.accomodationRequired = accomodationRequired;
        this.source = source;
    }

    public Integer getRegId() {
        return regId;
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Boolean getAccomodationRequired() {
        return accomodationRequired;
    }

    public void setAccomodationRequired(Boolean accomodationRequired) {
        this.accomodationRequired = accomodationRequired;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

}

