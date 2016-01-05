package bot

import org.junit.Test
import kotlin.test.assertEquals

class Version3Test {

    @Test
    fun testShouldCalculateScoreForAddingPlusWhenNoJoins() {
        assertEquals(0, Version3.scoreForPlus(listOf(1, 2, 3, 4), 0))
    }

    @Test
    fun testShouldCalculateScoreForAddingPlusWhenOneJoin() {
        assertEquals(2, Version3.scoreForPlus(listOf(1, 2, 3, 1), 0))
    }

    @Test
    fun testShouldCalculateScoreForAddingPlusWhenOneJoinWithOtherValue() {
        assertEquals(10, Version3.scoreForPlus(listOf(5, 2, 3, 5), 0))
    }

    @Test
    fun testShouldCalculateScoreForAddingPlusWhenTwoJoins() {
        assertEquals(28, Version3.scoreForPlus(listOf(5, 2, 3, 4, 2, 5), 0))
    }

    @Test
    fun testShouldCalculateScoreForAddingPlusWhenTwoJoinsWithOtherValues() {
        assertEquals((6 + 7) * 4, Version3.scoreForPlus(listOf(7, 6, 3, 4, 6, 7), 0))
    }

    @Test
    fun testShouldCalculateScoreForAddingPlusWhenThreeJoins() {
        assertEquals((1 + 1 + 1) * 6, Version3.scoreForPlus(listOf(1, 1, 1, 3, 4, 1, 1, 1), 0))
    }

    @Test
    fun testShouldGetWithLoopMakeEvenMultipleLoops() {
        assertEquals(1, listOf(1,1).getWithLoop(-99))
    }
}