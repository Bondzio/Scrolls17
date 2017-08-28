package in.silive.scrolls16.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmailById {

    @SerializedName("ID")
    @Expose
    private String iD;

    /**
     * No args constructor for use in serialization
     *
     */
    public EmailById() {
    }

    /**
     *
     * @param iD
     */
    public EmailById(String iD) {
        super();
        this.iD = iD;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

}
