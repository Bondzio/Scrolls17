package in.silive.scrolls16.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckStudentNoExsist {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("code")
    @Expose
    private Integer code;

    /**
     * No args constructor for use in serialization
     *
     */
    public CheckStudentNoExsist() {
    }

    /**
     *
     * @param error
     * @param code
     */
    public CheckStudentNoExsist(String error, Integer code) {
        super();
        this.error = error;
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}