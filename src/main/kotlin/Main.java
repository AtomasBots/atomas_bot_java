import bot.MainKt;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MainKt.INSTANCE.main();
        while (true) {
            Thread.sleep(10000000000L);
        }
    }
}
