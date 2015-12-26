package bot

import common.Game
import common.MoveApi
import common.NewGameApi
import common.RetrofitProvider
import rx.schedulers.Schedulers

abstract class BaseAlgorithm {

    abstract val host: String
    private val retrofit = RetrofitProvider.newRetrofit(host)
    private val newGameApi = retrofit.create(NewGameApi::class.java)
    private val moveApi = retrofit.create(MoveApi::class.java)

    fun main() {
        newGameApi.call().subscribe { doMove(it) }
    }

    private var maxScore = 0
    private var maxScoreGameId = ""

    private fun doMove(game: Game): Unit {
        if (game.score > maxScore) {
            maxScore = game.score
            maxScoreGameId = game.id
        }
        println(game.score)
        println("$maxScore $maxScoreGameId")
        if (game.next == -1000) {
            newGameApi.call().subscribeOn(Schedulers.io()).observeOn(Schedulers.computation()).subscribe { doMove(it) }
        } else {
            moveApi.call(game.id, calculateMove(game)).subscribe { doMove(it) }
        }
    }

    abstract fun calculateMove(game: Game): Int
}