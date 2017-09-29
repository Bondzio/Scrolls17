package in.silive.scrolls17.network;

import java.util.concurrent.TimeUnit;

import in.silive.scrolls17.util.Config;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String BASE_URL = Config.BASE_URL;
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(100000, TimeUnit.SECONDS)
                    .readTimeout(100000,TimeUnit.SECONDS).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).client(client)
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create())

                    .build();
        }
        return retrofit;
    }
}