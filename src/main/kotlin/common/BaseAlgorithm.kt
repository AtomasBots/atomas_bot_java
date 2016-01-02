package common

abstract class BaseAlgorithm {

    abstract val remote: Boolean
    private val host = if (remote) "http://89.73.67.164:46176/" else "http://127.0.0.1:8080/"
    private val retrofit = RetrofitProvider.newRetrofit(host)
    private val newGameApi = retrofit.create(NewGameApi::class.java)
    private val moveApi = retrofit.create(MoveApi::class.java)

    fun main() {
        while (true) {
            startGame()
        }
    }

    private var maxScore = 0
    private var maxScoreGameId = ""

    private fun startGame() {
        doMove(newGameApi.call().execute().body())
    }

    private fun doMove(game: Game) {
        logStatus(game)
        if (!isEndOfGame(game)) {
            nextMove(game, calculateMove(game))
        }
    }

    private fun logStatus(game: Game) {
        if (game.score > maxScore) {
            maxScore = game.score
            maxScoreGameId = game.id
        }
        println(game.score)
        println("$maxScore $maxScoreGameId")
    }

    private fun isEndOfGame(game: Game) = game.next == -1000


    abstract fun calculateMove(game: Game): Int

    private fun nextMove(game: Game, target: Int) {
        doMove(moveApi.call(game.id, target).execute().body())
    }
}