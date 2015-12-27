import bot.Version2Kt;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Version2Kt.INSTANCE.main();
        while (true) {
            Thread.sleep(10000000000L);
        }
    }
}
