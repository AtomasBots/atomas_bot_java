package bot

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.squareup.okhttp.OkHttpClient
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

object MainKt {

    val retrofit = Retrofit.Builder()
            .baseUrl("http://89.73.67.164:46176/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
    val newGameApi = retrofit.create(NewGameApi::class.java)
    val moveApi = retrofit.create(MoveApi::class.java)

    fun main() {
        newGameApi.call().subscribe { doMove(it) }
    }

    var maxScore = 0
    var maxScoreGameId = ""

    fun doMove(it: Game): Unit {
        if (it.score > maxScore) {
            maxScore = it.score
            maxScoreGameId = it.id
        }
        println(it.score)
        println("$maxScore $maxScoreGameId")
        if (it.next == -1000) {
            newGameApi.call().subscribe { doMove(it) }
        } else {
            moveApi.call(it.id, 0).subscribe { doMove(it) }
        }
    }

    interface NewGameApi {

        @GET("/new_game")
        fun call(): Observable<Game>
    }

    interface MoveApi {

        @GET("/move/{gameId}/{target}")
        fun call(@Path("gameId") gameId: String, @Path("target") target: Int): Observable<Game>
    }

    data class Game(
            val id: String,
            val board: List<Int>,
            val next: Int,
            val round: Int,
            val score: Int)
}
