package singRPG.java;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import singRPG.classes.Magic;
import singRPG.constant.Colours;
import singRPG.system.MagicSystem;
import singRPG.system.Util;

public class TestAlone {
    static Magic magics[];

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        magics = MagicSystem.readMagic();
        int menuCount = 0;
        int userAction = -1;
        int okMagic = 0;
        System.out.println("[0]: Back");
        System.out.println("[1]: Up");
        menuLoop: while (true) {
            /*
             * if ((menuCount * 3 + 2) < magics.length) {
             * for (int i = 0; i < 3; i++) {
             * showMagic(menuCount, i);
             * }
             * okMagic = 3;
             * } else if ((menuCount * 3 + 1) < magics.length) {
             * for (int i = 0; i < 2; i++) {
             * showMagic(menuCount, i);
             * }
             * okMagic = 2;
             * System.out.println("[4]: " + Colours.ANSI_RED + "--------------------" +
             * Colours.ANSI_RESET);
             * } else if ((menuCount * 3) < magics.length) {
             * okMagic = 1;
             * showMagic(menuCount, 0);
             * System.out.println("[3]: " + Colours.ANSI_RED + "--------------------" +
             * Colours.ANSI_RESET);
             * System.out.println("[4]: " + Colours.ANSI_RED + "--------------------" +
             * Colours.ANSI_RESET);
             * } else {
             * okMagic = 0;
             * System.out.println("[2]: " + Colours.ANSI_RED + "--------------------" +
             * Colours.ANSI_RESET);
             * System.out.println("[3]: " + Colours.ANSI_RED + "--------------------" +
             * Colours.ANSI_RESET);
             * System.out.println("[4]: " + Colours.ANSI_RED + "--------------------" +
             * Colours.ANSI_RESET);
             * }
             */

            for (int i = 0; (i < (3 - ((menuCount + 1) * 3 - magics.length))) && i < 3; i++) {
                showMagic(menuCount, i);
            }
            if ((3 - ((menuCount + 1) * 3 - magics.length)) < 0)
                okMagic = 0;
            else if ((3 - ((menuCount + 1) * 3 - magics.length)) > 3)
                okMagic = 3;
            else
                okMagic = 3 - ((menuCount + 1) * 3 - magics.length);
            for (int i = 0; (i < ((menuCount + 1) * 3 - magics.length)) && i < 3; i++) {
                System.out
                        .println(
                                "[" + (i + 2) + "]: " + Colours.ANSI_RED + "--------------------" + Colours.ANSI_RESET);
            }
            System.out.println("[5]: Down");
            userAction = Util.checkUserAction(0, okMagic + 1, 5, 898);
            if (userAction == 0) {
                System.out.println("quit");
            } else if (userAction == 1) {
                menuCount--;
                Util.clearLine(5);
            } else if (userAction == 5) {
                menuCount++;
                Util.clearLine(5);
            } else {
                break menuLoop;
            }
        }
    }

    public static void showMagic(int menuCount, int i) {
        String format = "%s%s%s%-20s%s%-8s%s%-7s%s%s\n";
        if (menuCount < 0) {
            menuCount = 0;
        }
        switch (magics[menuCount * 3 + i].getMagicType()) {
            case BUFF:
                format = "%s%s%s%-20s%s%-8s%s%-7s%s%-7s%s%s\n";
                System.out.printf(format,
                        "[", (i + 2), "]: ", magics[menuCount * 3 + i].getNAME(), "Amount: ",
                        magics[menuCount * 3 + i].getAMT(), "Cost: ",
                        magics[menuCount * 3 + i].getCOST(), "Hit Chance: ",
                        (double) magics[menuCount * 3 + i].getChance() / 10, "Buff Type: ",
                        magics[menuCount * 3 + i].getBuffType());
                break;
            case DMG:
                System.out.printf(format,
                        "[", (i + 2), "]: ", magics[menuCount * 3 + i].getNAME(), "Damage: ",
                        magics[menuCount * 3 + i].getAMT(), "Cost: ",
                        magics[menuCount * 3 + i].getCOST(), "Hit Chance: ",
                        (double) magics[menuCount * 3 + i].getChance() / 10);
                break;
            case HEAL:
                System.out.printf(format,
                        "[", (i + 2), "]: ", magics[menuCount * 3 + i].getNAME(), "Amount: ",
                        magics[menuCount * 3 + i].getAMT(), "Cost: ",
                        magics[menuCount * 3 + i].getCOST(), "Hit Chance: ",
                        (double) magics[menuCount * 3 + i].getChance() / 10);
                break;
        }
    }
}
