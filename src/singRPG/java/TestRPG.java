package singRPG.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import singRPG.classes.Magic;
import singRPG.system.MagicSystem;
import singRPG.system.SaveSystem;
import singRPG.system.Util;

public class TestRPG {
    static Util util = new Util();
    static Scanner scan = new Scanner(System.in);
    static String array[];

    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
        Util.clearScreen();
        array = SaveSystem.readConfig();
        SaveSystem.update();

        while (true) {
            util.printLine();
            System.out.println("[1] Create new player");
            System.out.println("[2] Create Magic");
            System.out.println("[3] List Magic");
            int userAction = -1;
            boolean firstAction = true;
            while (true) {
                userAction = scan.nextInt();
                if ((userAction >= 1) && (userAction <= 3)) {
                    break;
                } else {
                    if (firstAction) {
                        util.clearLine(1);
                        firstAction = false;
                    } else
                        util.clearLine(2);
                    System.out.println("Invalid input!");
                }
            }
            switch (userAction) {
                case 1:
                    SaveSystem.create();
                    break;
                case 2:
                    MagicSystem.createMagic();
                    break;
                case 3:
                    Magic magics[] = MagicSystem.readMagic();
                    String format = "%s%s%s%-20s%s%-7s%s%-7s%s%s\n";
                    for (int i = 0; i < magics.length; i++) {
                        switch (magics[i].getMagicType()) {
                            case BUFF:
                                format = "%s%s%s%-20s%s%-7s%s%-7s%s%-7s%s%s\n";
                                System.out.printf(format,
                                        "[", (i + 1), "]: ", magics[i].getNAME(), "Amount: ",
                                        magics[i].getAMT(), "Cost: ",
                                        magics[i].getCOST(), "Hit Chance: ",
                                        (double) magics[i].getChance() / 10, "Buff Type: ",
                                        magics[i].getBuffType());
                                break;
                            case DMG:
                                System.out.printf(format,
                                        "[", (i + 1), "]: ", magics[i].getNAME(), "Damage: ",
                                        magics[i].getAMT(), "Cost: ",
                                        magics[i].getCOST(), "Hit Chance: ",
                                        (double) magics[i].getChance() / 10);
                                break;
                            case HEAL:
                                System.out.printf(format,
                                        "[", (i + 1), "]: ", magics[i].getNAME(), "Amount: ",
                                        magics[i].getAMT(), "Cost: ",
                                        magics[i].getCOST(), "Hit Chance: ",
                                        (double) magics[i].getChance() / 10);
                                break;
                        }
                    }
                    break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Util.clearScreen();
        }
    }
}
