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
    static Util util = new Util();
    static Scanner scan = new Scanner(System.in);
    static String array[];

    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
        util.clearScreen();
        array = SaveSystem.readConfig();
        SaveSystem.update();

        outer: while (true) {
            util.printLine();
            System.out.println("Welcome to " + array[0]);
            System.out.println("Version: " + array[1]);
            util.printLine();
            System.out.println("Options:");
            System.out.println("[1] Start");
            System.out.println("[2] Quit");
            int userAction = -1;
            boolean firstAction = true;
            while (true) {
                userAction = scan.nextInt();
                if ((userAction >= 1) && (userAction <= 2)) {
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
                    util.clearLine(4);
                    System.out.println("Select player:");
                    System.out.println("[1] Player 1 (op)");
                    System.out.println("[2] Player 2");
                    userAction = -1;
                    firstAction = true;
                    while (true) {
                        userAction = scan.nextInt();
                        if ((userAction >= 1) && (userAction <= 2)) {
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
                    break;
                case 2:
                    break outer;
            }

            Player player = SaveSystem.read(userAction);

            // Player player = new Player(100, 10, 10, 20, 10, "Sing", true);
            Unit enemy = new Unit(100, 5, 5, 0, 0, "Wolf", false, 100);
            Game game = new Game(player, enemy);
            util.clearScreen();
            boolean win = game.start();
            if (win)
                System.out.println("You win!");
            else
                System.out.println("You lose!");

            player.updateLV();
            System.out.println("EXP: " + (int) player.getEXP() + "/" + (int) (player.getLevel() + 1) * 100);
            SaveSystem.write(player, userAction);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            util.clearScreen();
        }
    }
}
