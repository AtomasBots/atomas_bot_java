package bot

import common.BaseAlgorithm
import common.Game

object Version2Kt : BaseAlgorithm() {

    override val host = "http://89.73.67.164:46176/"
    //    override val host = "http://127.0.0.1:8080/"

    override fun calculateMove(game: Game): Int {
        if (game.next == 0) {
            var i = 0
            while (i + 1 < game.board.size && game.board[i] != game.board[i + 1]) i++
            if (i + 1 < game.board.size) {
                return i + 1
            } else {
                return 0
            }
        } else {
            val find = game.board.indexOf(game.next)
            if (find != -1) {
                return find
            } else {
                val find2 = game.board.indexOfFirst { it < game.next }
                if (find2 != -1) {
                    return find2
                } else {
                    return game.board.size
                }
            }
        }
    }
}
