package in.silive.scrolls17.models;

/**
 * Created by root on 16/9/17.
 */



        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class RegisterSucess {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private String data;

    /**
     * No args constructor for use in serialization
     *
     */
    public RegisterSucess() {
    }

    /**
     *
     * @param data
     * @param success
     */
    public RegisterSucess(Boolean success, String data) {
        super();
        this.success = success;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}