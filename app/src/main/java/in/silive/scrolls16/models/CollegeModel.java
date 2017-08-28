package in.silive.scrolls16.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CollegeModel {

    @SerializedName("CollegeId")
    @Expose
    private Integer collegeId;
    @SerializedName("CollegeName")
    @Expose
    private String collegeName;

    /**
     * No args constructor for use in serialization
     *
     */
    public CollegeModel() {
    }

    /**
     *
     * @param collegeId
     * @param collegeName
     */
    public CollegeModel(Integer collegeId, String collegeName) {
        super();
        this.collegeId = collegeId;
        this.collegeName = collegeName;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

}
