import common.Game;
import org.junit.Test;

import java.util.Arrays;

public class JavaBotTest {

    @Test
    public void testShouldReturnIntegerInRange() {
        int calculateMove = new JavaBot().calculateMove(new Game("", Arrays.asList(1, 2, 3, 4, 5), 0, 0, 0));
        assert calculateMove >= 0;
        assert calculateMove <= 5;
    }
}