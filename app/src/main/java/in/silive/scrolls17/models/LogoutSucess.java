package in.silive.scrolls17.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogoutSucess {

    @SerializedName("success")
    @Expose
    private Boolean success;

    /**
     * No args constructor for use in serialization
     *
     */
    public LogoutSucess() {
    }

    /**
     *
     * @param success
     */
    public LogoutSucess(Boolean success) {
        super();
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}

