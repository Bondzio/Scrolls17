package in.silive.scrolls16.models;

/**
 * Created by root on 27/8/17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamDetails {

    @SerializedName("TeamId")
    @Expose
    private Integer teamId;
    @SerializedName("TeamName")
    @Expose
    private String teamName;
    @SerializedName("TotalMembers")
    @Expose
    private Integer totalMembers;
    @SerializedName("Member1RegId")
    @Expose
    private Integer member1RegId;
    @SerializedName("Member2RegId")
    @Expose
    private Integer member2RegId;
    @SerializedName("Member3RegId")
    @Expose
    private Integer member3RegId;
    @SerializedName("DomainId")
    @Expose
    private Integer domainId;
    @SerializedName("TopicId")
    @Expose
    private Integer topicId;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("SynopsisName")
    @Expose
    private String synopsisName;
    @SerializedName("SynopsisAvailable")
    @Expose
    private Boolean synopsisAvailable;
    @SerializedName("TeamLeader")
    @Expose
    private Integer teamLeader;
    @SerializedName("Mem1Name")
    @Expose
    private Object mem1Name;
    @SerializedName("Mem2Name")
    @Expose
    private Object mem2Name;
    @SerializedName("Mem3Name")
    @Expose
    private Object mem3Name;
    @SerializedName("Source")
    @Expose
    private Object source;
    @SerializedName("AbstractAvailable")
    @Expose
    private Boolean abstractAvailable;
    @SerializedName("TeamMembers")
    @Expose
    private List<TeamMember> teamMembers = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public TeamDetails() {
    }

    /**
     *
     * @param teamName
     * @param member2RegId
     * @param mem3Name
     * @param member1RegId
     * @param synopsisName
     * @param abstractAvailable
     * @param member3RegId
     * @param teamId
     * @param password
     * @param source
     * @param teamMembers
     * @param domainId
     * @param topicId
     * @param synopsisAvailable
     * @param teamLeader
     * @param mem1Name
     * @param totalMembers
     * @param mem2Name
     */
    public TeamDetails(Integer teamId, String teamName, Integer totalMembers, Integer member1RegId, Integer member2RegId, Integer member3RegId, Integer domainId, Integer topicId, String password, String synopsisName, Boolean synopsisAvailable, Integer teamLeader, Object mem1Name, Object mem2Name, Object mem3Name, Object source, Boolean abstractAvailable, List<TeamMember> teamMembers) {
        super();
        this.teamId = teamId;
        this.teamName = teamName;
        this.totalMembers = totalMembers;
        this.member1RegId = member1RegId;
        this.member2RegId = member2RegId;
        this.member3RegId = member3RegId;
        this.domainId = domainId;
        this.topicId = topicId;
        this.password = password;
        this.synopsisName = synopsisName;
        this.synopsisAvailable = synopsisAvailable;
        this.teamLeader = teamLeader;
        this.mem1Name = mem1Name;
        this.mem2Name = mem2Name;
        this.mem3Name = mem3Name;
        this.source = source;
        this.abstractAvailable = abstractAvailable;
        this.teamMembers = teamMembers;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(Integer totalMembers) {
        this.totalMembers = totalMembers;
    }

    public Integer getMember1RegId() {
        return member1RegId;
    }

    public void setMember1RegId(Integer member1RegId) {
        this.member1RegId = member1RegId;
    }

    public Integer getMember2RegId() {
        return member2RegId;
    }

    public void setMember2RegId(Integer member2RegId) {
        this.member2RegId = member2RegId;
    }

    public Integer getMember3RegId() {
        return member3RegId;
    }

    public void setMember3RegId(Integer member3RegId) {
        this.member3RegId = member3RegId;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSynopsisName() {
        return synopsisName;
    }

    public void setSynopsisName(String synopsisName) {
        this.synopsisName = synopsisName;
    }

    public Boolean getSynopsisAvailable() {
        return synopsisAvailable;
    }

    public void setSynopsisAvailable(Boolean synopsisAvailable) {
        this.synopsisAvailable = synopsisAvailable;
    }

    public Integer getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(Integer teamLeader) {
        this.teamLeader = teamLeader;
    }

    public Object getMem1Name() {
        return mem1Name;
    }

    public void setMem1Name(Object mem1Name) {
        this.mem1Name = mem1Name;
    }

    public Object getMem2Name() {
        return mem2Name;
    }

    public void setMem2Name(Object mem2Name) {
        this.mem2Name = mem2Name;
    }

    public Object getMem3Name() {
        return mem3Name;
    }

    public void setMem3Name(Object mem3Name) {
        this.mem3Name = mem3Name;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Boolean getAbstractAvailable() {
        return abstractAvailable;
    }

    public void setAbstractAvailable(Boolean abstractAvailable) {
        this.abstractAvailable = abstractAvailable;
    }

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

}
