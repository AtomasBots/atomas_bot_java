import bot.GhostBot
import common.Game
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class GhostBotTestCase {

    private val PLUS_SIGN = 0

    @Test
    fun shouldPutElementOnTheLeftOfExistingElement() {
        val game = gameWithBoard(board = arrayListOf(1, 2, 3, 4, 5), next = 3)
        val move = GhostBot.calculateMove(game)

        assertEquals(2, move)
    }

    @Test
    fun shouldPutElementAtTheBeginningOfBoardIfThereIsNoSuchElement() {
        val game = gameWithBoard(board = arrayListOf(1, 2), next = 3)
        val move = GhostBot.calculateMove(game)

        assertEquals(0, move)
    }

    @Test
    fun shouldPutElementAfterFirstElementWhenFirstAndLastAreCreatingPair() {
        val game = gameWithBoard(board = arrayListOf(1, 2, 1), next = 3)
        val move = GhostBot.calculateMove(game)

        assertEquals(1, move)
    }

    @Test
    fun shouldPutPlusSignInTheMiddleOfThePair() {
        val game = gameWithBoard(board = arrayListOf(2, 1, 3, 2, 2, 1), next = PLUS_SIGN)
        val move = GhostBot.calculateMove(game)

        assertEquals(4, move)
    }

    @Test
    fun shouldPutPlusSignAtTheBeginningOfBoardWhenThereAreNoPairs() {
        val game = gameWithBoard(board = arrayListOf(2, 1, 3, 4, 2, 1), next = PLUS_SIGN)
        val move = GhostBot.calculateMove(game)

        assertEquals(0, move)
    }

    @Test
    fun shouldFindCenterOfPairIfThereIsOne() {
        val board = arrayListOf(1, 2, 2, 3)

        val result = GhostBot.findPairs(board)

        assertEquals(listOf(2), result)
    }

    @Test
    fun shouldFindAllCentersOfPairs() {
        val board = arrayListOf(1, 1, 2, 2)

        val result = GhostBot.findPairs(board)

        assertEquals(listOf(1, 3), result)
    }

    @Test
    fun shouldPutElementOnLeftSideOfPlusSignWhenOnTheRightSideIsElementTheSameType() {
        val game = gameWithBoard(board = arrayListOf(4, 3, 2, PLUS_SIGN, 1), next = 1)
        val move = GhostBot.calculateMove(game)

        assertEquals(3, move)
    }

    @Test
    fun shouldPutElementOnRightSideOfPlusSignWhenOnTheLeftSideIsElementTheSameType() {
        val game = gameWithBoard(board = arrayListOf(4, 3, 2, PLUS_SIGN, 1), next = 2)
        val move = GhostBot.calculateMove(game)

        assertEquals(4, move)
    }

    private fun gameWithBoard(board: ArrayList<Int>, next: Int): Game {
        return Game(id = "", board = board, next = next, round = 0, score = 0)
    }
}