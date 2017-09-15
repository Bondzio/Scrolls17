package in.silive.scrolls16.models;

/**
 * Created by root on 15/9/17.
 */



        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("domain_name")
    @Expose
    private String domainName;

    /**
     * No args constructor for use in serialization
     *
     */
    public Datum() {
    }

    /**
     *
     * @param id
     * @param domainName
     */
    public Datum(Integer id, String domainName) {
        super();
        this.id = id;
        this.domainName = domainName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

}