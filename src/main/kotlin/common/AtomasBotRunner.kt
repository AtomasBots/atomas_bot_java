package common

class AtomasBotRunner(val moveCalculator: MoveCalculator, val remote: Boolean = true) {

    private var bestGame: Game = Game("id", emptyList(), -1000, 0, 0)
    private val atomasBotsApi = AtomasBotsApi.createApi(remote)

    fun run(name: String? = null) {
        while (true) {
            doMove(atomasBotsApi.newGame(name).execute().body())
        }
    }

    private tailrec fun doMove(game: Game) {
        logStatus(game)
        if (game.isEndOfGame()) {
            return
        }
        doMove(atomasBotsApi.move(game.id, moveCalculator.calculateMove(game)).execute().body())
    }

    private fun logStatus(game: Game) {
        if (game.isEndOfGame()) {
            if (game.score >= bestGame.score) {
                bestGame = game
            }
            println("Current score:\t${game.score}")
            println("Max score:\t\t${bestGame.score} (in game ${bestGame.id})")
        }
    }
}