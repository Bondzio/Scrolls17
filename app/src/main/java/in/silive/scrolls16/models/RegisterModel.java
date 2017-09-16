package in.silive.scrolls16.models;

/**
 * Created by root on 16/9/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterModel {

    @SerializedName("team_name")
    @Expose
    private String teamName;
    @SerializedName("domain_id")
    @Expose
    private String domainId;
    @SerializedName("topic_id")
    @Expose
    private String topicId;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("noofmembers")
    @Expose
    private String noofmembers;
    @SerializedName("members")
    @Expose
    private List<Member> members = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public RegisterModel() {
    }

    /**
     *
     * @param teamName
     * @param noofmembers
     * @param domainId
     * @param topicId
     * @param password
     * @param members
     */
    public RegisterModel(String teamName, String domainId, String topicId, String password, String noofmembers, List<Member> members) {
        super();
        this.teamName = teamName;
        this.domainId = domainId;
        this.topicId = topicId;
        this.password = password;
        this.noofmembers = noofmembers;
        this.members = members;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoofmembers() {
        return noofmembers;
    }

    public void setNoofmembers(String noofmembers) {
        this.noofmembers = noofmembers;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

}