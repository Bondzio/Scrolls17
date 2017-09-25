package in.silive.scrolls17.models;

/**
 * Created by root on 16/9/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Member {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("course")
    @Expose
    private String course;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("college_name")
    @Expose
    private String collegeName;
    @SerializedName("student_no")
    @Expose
    private String studentNo;
    @SerializedName("contact_no")
    @Expose
    private String contactNo;
    @SerializedName("accomodation")
    @Expose
    private String accomodation;
    @SerializedName("teamlead")
    @Expose
    private String teamlead;

    /**
     * No args constructor for use in serialization
     *
     */
    public Member() {
    }

    /**
     *
     * @param course
     * @param accomodation
     * @param studentNo
     * @param email
     * @param name
     * @param teamlead
     * @param contactNo
     * @param year
     * @param collegeName
     */
    public Member(String name, String email, String course, String year, String collegeName, String studentNo, String contactNo, String accomodation, String teamlead) {
        super();
        this.name = name;
        this.email = email;
        this.course = course;
        this.year = year;
        this.collegeName = collegeName;
        this.studentNo = studentNo;
        this.contactNo = contactNo;
        this.accomodation = accomodation;
        this.teamlead = teamlead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAccomodation() {
        return accomodation;
    }

    public void setAccomodation(String accomodation) {
        this.accomodation = accomodation;
    }

    public String getTeamlead() {
        return teamlead;
    }

    public void setTeamlead(String teamlead) {
        this.teamlead = teamlead;
    }

}