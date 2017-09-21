package in.silive.scrolls16.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("teamname")
    @Expose
    private String teamname;
    @SerializedName("member1name")
    @Expose
    private String member1name;
    @SerializedName("member2name")
    @Expose
    private String member2name;
    @SerializedName("member3name")
    @Expose
    private String member3name;

    /**
     * No args constructor for use in serialization
     *
     */
    public Data() {
    }

    /**
     *
     * @param member2name
     * @param teamname
     * @param token
     * @param member1name
     * @param member3name
     */
    public Data(String token, String teamname, String member1name, String member2name, String member3name) {
        super();
        this.token = token;
        this.teamname = teamname;
        this.member1name = member1name;
        this.member2name = member2name;
        this.member3name = member3name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getMember1name() {
        return member1name;
    }

    public void setMember1name(String member1name) {
        this.member1name = member1name;
    }

    public String getMember2name() {
        return member2name;
    }

    public void setMember2name(String member2name) {
        this.member2name = member2name;
    }

    public String getMember3name() {
        return member3name;
    }

    public void setMember3name(String member3name) {
        this.member3name = member3name;
    }

}