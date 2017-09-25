package in.silive.scrolls17.network;

import java.util.List;

import in.silive.scrolls17.models.CheckStudentNoExsist;
import in.silive.scrolls17.models.CollegeModel;
import in.silive.scrolls17.models.DomainModel;
import in.silive.scrolls17.models.LoginModelF;
import in.silive.scrolls17.models.LoginSucess;
import in.silive.scrolls17.models.LogoutSucess;
import in.silive.scrolls17.models.QueryModel;
import in.silive.scrolls17.models.RegisterModel;
import in.silive.scrolls17.models.RegisterSucess;
import in.silive.scrolls17.models.SelfRegister;
import in.silive.scrolls17.models.TopicModel;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
 @Headers({"Content-Type: application/json", "Accept: application/json; charset=UTF-8" })
    @POST("api/register")

    Call<RegisterSucess> register(@Body RegisterModel object);
    @FormUrlEncoded
    @POST("api/checkstudentalreadyexist")
Call<CheckStudentNoExsist>  checkStudentNo(@Field(value = "student_no") String Student);
    @FormUrlEncoded
    @POST("api/checkemailalreadyexist")
    Call<CheckStudentNoExsist>  checkEamilId(@Field(value = "email") String Email);
    @FormUrlEncoded
    @POST("api/login")
    Call<LoginModelF> Login(@Field(value = "teamid") String teamid,@Field(value = "password") String password);
    @FormUrlEncoded
    @POST("api/fcmregister")
    Call<LoginSucess>  Fcm(@Field(value = "fcmtoken") String token);
    @Multipart
    @POST("api/fileentry/add")
    Call<okhttp3.ResponseBody> upload(@Query("token") String token,
                                      @Part MultipartBody.Part file
    );
    @GET("api/logout")
    Call<LogoutSucess> logout(@Query("token") String token);
    @GET("api/fileentryexist")
    Call<CheckStudentNoExsist>  fileUploadStatus(@Query("token") String token);
}
