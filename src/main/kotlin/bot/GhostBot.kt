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
        val loopedBoard = MyList(game.board)
        val pairs = findPairs(loopedBoard)
        if (pairs.size > 0 ) {
            return pairs.map { it to calculatePairLength(loopedBoard, it, 0) }.sortedBy { it.first.value }.reversed().maxBy { it.second }!!.first.middle
        } else {
            return 0
        }
    }

    private fun calculatePairLength(board: MyList, pair: PairWithMiddle, result: Int): Int {
        val prevPrev = board.prevWithLoop(pair.first)
        val nextNext = board.nextWithLoop(pair.second)
        if ( prevPrev === nextNext || (prevPrev === pair.second && nextNext === pair.first) ) {
            return result
        }
        if (prevPrev.value == nextNext.value ) {
            return result + calculatePairLength(board, PairWithMiddle(prevPrev, nextNext), result + 1)
        }
        return result
    }

    private fun handleNormalElement(game: Game): Int {
        val similarSearchResult = game.board.indexOfFirst { it == game.next }
        val result = if (similarNotFound(similarSearchResult)) handleWhenThereAreNoSimilarElements(game) else handleWhenThereAreSimilarElements(game, similarSearchResult)
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

    fun findPairs(board: MyList): List<PairWithMiddle> {
        return board.list.filter {
            it.value == board.nextWithLoop(it).value
        }.map {
            PairWithMiddle(it, board.nextWithLoop(it))
        }
    }

    class PairWithMiddle(val first: MyElement, val second: MyElement) {
        val middle = (first.index + second.index) / 2 + 1//warning
        val value = first.value
    }

    class MyList {
        val head: MyElement
        val tail: MyElement
        val list = arrayListOf<MyElement>()

        constructor(board: List<Int>) {
            (0..board.size - 1).forEach {
                list.add(MyElement(it, board[it]))
            }
            head = list.first()
            tail = list.last()
            (0..board.size - 1).forEach {
                if (it < board.size - 1) list[it].next = list[it + 1]
                if ( it > 0 ) list[it].previous = list[it - 1]
            }
        }

        fun nextWithLoop(element: MyElement): MyElement {
            return element.next ?: head
        }

        fun prevWithLoop(element: MyElement): MyElement {
            return element.previous ?: tail
        }
    }

    class MyElement(val index: Int, val value: Int, var next: MyElement? = null, var previous: MyElement? = null)
}