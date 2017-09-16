package in.silive.scrolls16.Network;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONObject;

import java.util.List;

import in.silive.scrolls16.Util.Config;
import in.silive.scrolls16.models.CollegeModel;
import in.silive.scrolls16.models.DomainModel;
import in.silive.scrolls16.models.QueryModel;
import in.silive.scrolls16.models.RegisterModel;
import in.silive.scrolls16.models.RegisterSucess;
import in.silive.scrolls16.models.SelfRegister;
import in.silive.scrolls16.models.TopicModel;
import in.silive.scrolls16.models.Topics;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by root on 28/8/17.
 */

public interface RetrofitApiInterface {
@GET("api/getdomains")
Call<DomainModel> getDomians();
    @GET("api/getdomains/{id}/gettopics")
    Call<TopicModel> getTopics(@Path("id") String id);
    @GET("api/Colleges/GetColleges")
    Call<List<CollegeModel>> getCollege();
    @FormUrlEncoded
    @POST("api/Queries/RegisterQuery/")
    Call<QueryModel> submitQuery(@Field(value = "Email") String email,@Field(value = "body") String body);
    @FormUrlEncoded
    @POST("api/Participants/CreateParticipant")
    Call<SelfRegister> register(@Field(value = "Name") String Name,@Field(value = "StudentId") String StudentId,
                                @Field(value = "CollegeId") int CollegeId,@Field(value = "EmailId") String EmailId,
                                @Field(value = "MobileNo") String MobileNo,@Field(value = "CourseId") int CourseId,
                                @Field(value = "Year") int Year,@Field(value = "Source") String Source,@Field(value =
    "AccomodationRequired") int AccomodationRequired);
    @Headers("Content-Type: application/json")
    @POST("api/register")
    Call<RegisterSucess> register(@Body JSONObject body);
}
