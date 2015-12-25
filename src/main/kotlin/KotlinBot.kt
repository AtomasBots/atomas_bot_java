import common.AtomasBotRunner
import common.Game
import common.MoveCalculator

public class KotlinBot : MoveCalculator {

    //TODO: Add your logic here
    override fun calculateMove(game: Game): Int {
        return 0
    }

    companion object {
        @JvmStatic public fun main(args: Array<String>) {
            AtomasBotRunner(KotlinBot()).run(/*Put optional nick here*/)
        }
    }
}