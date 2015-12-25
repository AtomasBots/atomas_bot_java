import common.Game
import org.junit.Test
import kotlin.test.assertTrue

class KotlinBotTest {

    @Test
    fun testShouldReturnIntegerInRange() {
        assertTrue((0..5).contains(KotlinBot().calculateMove(Game("", listOf(), 0, 0, 0))))
    }
}