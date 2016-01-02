package common

import retrofit.Call
import retrofit.http.GET

interface NewGameApi {

    @GET("/new_game")
    fun call(): Call<Game>
}
