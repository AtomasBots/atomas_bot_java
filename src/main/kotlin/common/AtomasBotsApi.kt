package common

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.squareup.okhttp.OkHttpClient
import retrofit.Call
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import retrofit.http.GET
import retrofit.http.Path
import retrofit.http.Query

interface AtomasBotsApi {

    @GET("/move/{gameId}/{target}")
    fun move(@Path("gameId") gameId: String, @Path("target") target: Int): Call<Game>

    @GET("/new_game")
    fun newGame(@Query("name") name: String?): Call<Game>

    companion object {
        fun createApi(remote: Boolean = true): AtomasBotsApi {
            return Retrofit.Builder()
                    .baseUrl(if (remote) "http://89.73.67.164:46176/" else "http://127.0.0.1:8080/")
                    .client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
                    .create(AtomasBotsApi::class.java)
        }
    }
}
