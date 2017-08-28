package in.silive.scrolls16.models;

/**
 * Created by root on 27/8/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Topics {

    @SerializedName("TopicId")
    @Expose
    private Integer topicId;
    @SerializedName("TopicName")
    @Expose
    private String topicName;

    /**
     * No args constructor for use in serialization
     *
     */
    public Topics() {
    }

    /**
     *
     * @param topicId
     * @param topicName
     */
    public Topics(Integer topicId, String topicName) {
        super();
        this.topicId = topicId;
        this.topicName = topicName;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

}
