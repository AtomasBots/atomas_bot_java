package common

import bot.MainKt
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

interface MoveApi {

    @GET("/move/{gameId}/{target}")
    fun call(@Path("gameId") gameId: String, @Path("target") target: Int): Observable<MainKt.Game>
}
