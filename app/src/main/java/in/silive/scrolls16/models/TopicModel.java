package in.silive.scrolls16.models;

import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

import in.silive.scrolls16.Models.*;

public class TopicModel {

    @SerializedName("data")
    @Expose
    private List<in.silive.scrolls16.Models.Datum> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public TopicModel() {
    }

    /**
     *
     * @param data
     */
    public TopicModel(List<in.silive.scrolls16.Models.Datum> data) {
        super();
        this.data = data;
    }

    public List<in.silive.scrolls16.Models.Datum> getData() {
        return data;
    }

    public void setData(List<in.silive.scrolls16.Models.Datum> data) {
        this.data = data;
    }

}

