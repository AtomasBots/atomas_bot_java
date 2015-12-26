package common

data class Game(
        val id: String,
        val board: List<Int>,
        val next: Int,
        val round: Int,
        val score: Int)
