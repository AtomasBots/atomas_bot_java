import common.AtomasBotRunner;
import common.Game;
import common.MoveCalculator;

public class JavaBot implements MoveCalculator {

    //TODO: Add your logic here
    @Override
    public int calculateMove(Game game) {
        return 0;
    }

    public static void main(String[] args) throws Exception {
        new AtomasBotRunner(new JavaBot()).run(/*Put optional nick here*/);
    }
}