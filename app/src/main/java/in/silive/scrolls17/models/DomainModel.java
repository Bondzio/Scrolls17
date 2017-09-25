package in.silive.scrolls17.models;

/**
 * Created by root on 15/9/17.
 */



        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class DomainModel {

    @SerializedName("data")
    @Expose
    public List<Datum> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public DomainModel() {
    }

    /**
     *
     * @param data
     */
    public DomainModel(List<Datum> data) {
        super();
        this.data = data;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
