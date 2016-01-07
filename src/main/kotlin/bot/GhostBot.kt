package bot

import common.Game
import common.MoveCalculator

object GhostBot : MoveCalculator {

    private const val PLUS_SIGN = 0

    override fun calculateMove(game: Game): Int {
        println("board: ${game.board}")
        println("next: ${game.next}")
        if (game.next != PLUS_SIGN) {
            return handleNormalElement(game)
        } else {
            return handlePlus(game)
        }
    }

    private fun handlePlus(game: Game): Int {
        val pairs = findPairs(game.board)
        if (pairs.size > 0 ) {
            return pairs.first()
        } else {
            return 0
        }
    }

    private fun handleNormalElement(game: Game): Int {
        val similarSearchResult = game.board.indexOfFirst { it == game.next }
        val result = if (similarNotFound(similarSearchResult)) handleWhenThereAreNoSimilarElements(game) else handleWhenThereAreSimilarElements(game, similarSearchResult)
        println("move ${result}")
        return result
    }

    private fun handleWhenThereAreSimilarElements(game: Game, similarSearchResult: Int): Int {
        if (boardsContainsPlus(game) && plusIsNearOurSimilarElement(game.board, similarSearchResult)) {
            val indexOfPlus = indexOfNearestPlus(game.board, similarSearchResult)
            return if ( indexOfPlus > similarSearchResult ) indexOfPlus + 1 else indexOfPlus
        } else {
            return similarSearchResult
        }
    }

    private fun plusIsNearOurSimilarElement(board: List<Int>, similarSearchResult: Int): Boolean {
        //        board.forEachIndexed { index, element -> if (element == PLUS_SIGN) plusIndexes.add(index) }
        val plusIndexes = board.mapIndexed { index, element -> Pair(index, element) }.filter { it.second == PLUS_SIGN }.map { it.first }
        return plusIndexes.map { Pair(it, Math.abs(it - similarSearchResult)) }.firstOrNull { it.second == 1 }?.first != null
    }

    private fun indexOfNearestPlus(board: List<Int>, similarSearchResult: Int): Int {
        val plusIndexes = board.mapIndexed { index, element -> Pair(index, element) }.filter { it.second == PLUS_SIGN }.map { it.first }
        return plusIndexes.map { Pair(it, Math.abs(it - similarSearchResult)) }.first { it.second == 1 }.first
    }

    private fun boardsContainsPlus(game: Game): Boolean = game.board.contains(PLUS_SIGN)

    private fun handleWhenThereAreNoSimilarElements(game: Game): Int {
        return if (firstAndLastArePair(game)) 1 else 0
    }

    private fun firstAndLastArePair(game: Game) = game.board[0] == game.board[game.board.size - 1]

    private fun similarNotFound(similarSearchResult: Int) = similarSearchResult == -1

    fun findPairs(board: List<Int>): List<Int> {
        val centers = arrayListOf<PairWithMiddle>()
        val loopedBoard = listOf(board.last()) + board
        (0..board.size - 1).forEach {
            if (loopedBoard[it] == loopedBoard[it + 1]) {
                centers.add(PairWithMiddle(loopedBoard[it], it))
            }
        }
        return centers.map { it.middle }
    }

    class PairWithMiddle(val pairValue: Int, val middle: Int)
}