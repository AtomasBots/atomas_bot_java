package common

import bot.MainKt
import retrofit.http.GET
import rx.Observable

interface NewGameApi {

    @GET("/new_game")
    fun call(): Observable<MainKt.Game>
}
