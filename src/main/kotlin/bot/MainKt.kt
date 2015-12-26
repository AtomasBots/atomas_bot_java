package bot

import common.BaseAlgorithm
import common.Game

object MainKt : BaseAlgorithm() {

    override val host = "http://89.73.67.164:46176/"
    //    override val host = "http://127.0.0.1:8080/"

    override fun calculateMove(game: Game): Int {
        return 0
    }
}
