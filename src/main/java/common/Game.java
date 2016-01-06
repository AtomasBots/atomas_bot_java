package common;

import java.util.List;

public class Game {
    public final String id;
    public final List<Integer> board;
    public final int next;
    public final int round;
    public final int score;

    public Game(String id, List<Integer> board, int next, int round, int score) {
        this.id = id;
        this.board = board;
        this.next = next;
        this.round = round;
        this.score = score;
    }

    boolean isEndOfGame() {
        return next == -1000;
    }
}
