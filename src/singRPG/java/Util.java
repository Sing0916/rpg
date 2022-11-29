package singRPG.java;

public class Util {
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void clearLine(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print("\033[F");
            System.out.flush();
        }
        System.out.print("\033[0J");
    }
}
