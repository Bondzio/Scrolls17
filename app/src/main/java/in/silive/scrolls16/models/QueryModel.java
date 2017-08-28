package in.silive.scrolls16.models;

/**
 * Created by root on 28/8/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryModel {

    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Body")
    @Expose
    private String body;

    /**
     * No args constructor for use in serialization
     *
     */
    public QueryModel() {
    }

    /**
     *
     * @param body
     * @param email
     */
    public QueryModel(String email, String body) {
        super();
        this.email = email;
        this.body = body;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
