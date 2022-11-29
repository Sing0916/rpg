package singRPG.java;

import singRPG.constant.Colours;

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

    public void printLine() {
        System.out.println(Colours.ANSI_YELLOW + "-------------------------" + Colours.ANSI_RESET);
    }
}
