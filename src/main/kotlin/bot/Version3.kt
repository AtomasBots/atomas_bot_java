package bot

import common.Game
import common.MoveCalculator

object Version3 : MoveCalculator {

    override fun calculateMove(game: Game): Int {
        if (game.next == 0) {
            return addPlus(game.board)
        } else {
            return addElement(game.board, game.next)
        }
    }

    private fun addPlus(board: List<Int>): Int {
        return (0..board.size).maxBy { scoreForPlus(board, it) }!!
    }

    fun scoreForPlus(board: List<Int>, index: Int): Int {
        if (board.getWithLoop(index - 1) != board.getWithLoop(index)) {
            return 0
        } else {
            if (board.getWithLoop(index - 2) != board.getWithLoop(index + 1)) {
                return 2 * board.getWithLoop(index)
            } else {
                if (board.getWithLoop(index - 3) != board.getWithLoop(index + 2)) {
                    return 4 * (board.getWithLoop(index) + board.getWithLoop(index + 1))
                } else {
                    return 6 * (board.getWithLoop(index) + board.getWithLoop(index + 1) + board.getWithLoop(index + 2))
                }
            }
        }
    }

    private fun addElement(board: List<Int>, next: Int): Int {
        return (0..board.size).maxBy { maxPossibleScoreForBoard(board.toArrayList().apply { add(it, next) }) }!!
    }

    private fun maxPossibleScoreForBoard(board: List<Int>): Int {
        return (0..board.size).map { scoreForPlus(board, it) }.max()!!
    }

}

fun List<Int>.getWithLoop(index: Int) = get(((index + size) % size + size ) % size)
