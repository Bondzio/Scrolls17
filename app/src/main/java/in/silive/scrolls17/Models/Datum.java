package in.silive.scrolls17.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("topic_name")
    @Expose
    private String topicName;
    @SerializedName("domain_id")
    @Expose
    private Integer domainId;

    /**
     * No args constructor for use in serialization
     *
     */
    public Datum() {
    }

    /**
     *
     * @param id
     * @param domainId
     * @param topicName
     */
    public Datum(Integer id, String topicName, Integer domainId) {
        super();
        this.id = id;
        this.topicName = topicName;
        this.domainId = domainId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

}
