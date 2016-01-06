package common;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

interface AtomasBotsApi {

    @GET("/move/{gameId}/{target}")
    Call<Game> move(@Path("gameId") String gameId, @Path("target") int target);

    @GET("/new_game")
    Call<Game> newGame(@Query("name") String name);

    class Builder {
        static AtomasBotsApi createApi(boolean remote) {
            return new Retrofit.Builder()
                    .baseUrl(remote ? "http://89.73.67.164:46176/" : "http://127.0.0.1:8080/")
                    .client(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
                    .create(AtomasBotsApi.class);
        }
    }
}
