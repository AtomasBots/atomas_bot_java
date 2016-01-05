package bot

import common.Game
import common.MoveCalculator

object Version2Kt : MoveCalculator {

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
