package bot

import common.BaseAlgorithm
import common.Game

object Version3 : BaseAlgorithm() {

    override val remote = true

    override fun calculateMove(game: Game): Int {
        return 0
    }
}