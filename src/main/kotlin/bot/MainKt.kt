package bot

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.squareup.okhttp.OkHttpClient
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import retrofit.http.GET
import rx.Observable

object MainKt {

    fun main() {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://89.73.67.164:46176/")
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        retrofit.create(NewGame::class.java).call().subscribe {
            println(it.id)
        }
    }

    interface NewGame {

        @GET("/new_game")
        fun call(): Observable<Game>
    }

    data class Game(val id: String)
}
