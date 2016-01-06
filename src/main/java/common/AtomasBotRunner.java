package common;

import java.util.ArrayList;

public class AtomasBotRunner {

    private final MoveCalculator moveCalculator;
    private final AtomasBotsApi atomasBotsApi;
    private Game bestGame = new Game("id", new ArrayList<Integer>(), -1000, 0, 0);

    public AtomasBotRunner(MoveCalculator moveCalculator) {
        this(moveCalculator, true);
    }

    public AtomasBotRunner(MoveCalculator moveCalculator, boolean remote) {
        this.moveCalculator = moveCalculator;
        this.atomasBotsApi = AtomasBotsApi.Builder.createApi(remote);
    }

    public void run() throws Exception {
        run(null);
    }

    public void run(String name) throws Exception {
        while (true) {
            Game newGame = atomasBotsApi.newGame(name).execute().body();
            while (!newGame.isEndOfGame()) {
                newGame = doMove(newGame);
            }
            logStatus(newGame);
        }
    }

    private Game doMove(Game game) throws Exception {
        return atomasBotsApi.move(game.id, moveCalculator.calculateMove(game)).execute().body();
    }

    private void logStatus(Game game) {
        if (game.score >= bestGame.score) {
            bestGame = game;
        }
        System.out.println("Current score:\t" + game.score);
        System.out.println("Max score:\t\t" + bestGame.score + " (in game " + bestGame.id + ")");
    }
}