package singRPG.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;
import singRPG.classes.entity.Player;
import singRPG.classes.entity.Unit;
import singRPG.system.SaveSystem;
import singRPG.system.Util;

class RPG {
    static Scanner scan = new Scanner(System.in);
    static String array[];
    static int userAction = -1;

    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
        Util.clearScreen();
        array = SaveSystem.readConfig();
        SaveSystem.update();

        outer: while (true) {
            Util.printLine();
            System.out.println("Welcome to " + array[0]);
            System.out.println("Version: " + array[1]);
            Util.printLine();
            System.out.println("[1] Start");
            System.out.println("[2] Load");
            System.out.println("[3] Quit");
            userAction = Util.checkUserAction(1, 3);
            switch (userAction) {
                case 1:
                    userAction = SaveSystem.create();
                    break;
                case 2:
                    Util.clearLine(4);
                    System.out.println("[0] Back");
                    System.out.println("Select player:");
                    String names[] = SaveSystem.getPlayerList();
                    for (int i = 0; i < names.length; i++) {
                        System.out.println("[" + (i + 1) + "] " + names[i]);
                    }
                    userAction = Util.checkUserAction(0, names.length);
                    break;
                case 3:
                    break outer;
            }

            if (userAction == 0) {
                Util.clearScreen();
                continue outer;
            }
            Player player = SaveSystem.read(userAction);

            Unit enemy = new Unit(100, 5, 5, 0, 0, "Wolf", false, 100);
            Game game = new Game(player, enemy);
            Util.clearScreen();
            boolean win = game.start();
            if (win)
                System.out.println("You win!");
            else
                System.out.println("You lose!");

            player.updateLV();
            System.out.println("EXP: " + (int) player.getEXP() + "/" + (int) (player.getLevel() + 1) * 100);
            SaveSystem.write(player, userAction);
            Util.pressAnyKey();
            Util.clearScreen();
        }
    }
}
