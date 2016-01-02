package common

import retrofit.Call
import retrofit.http.GET
import retrofit.http.Path

interface MoveApi {

    @GET("/move/{gameId}/{target}")
    fun call(@Path("gameId") gameId: String, @Path("target") target: Int): Call<Game>
}
