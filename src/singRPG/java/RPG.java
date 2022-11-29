package singRPG.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;
import singRPG.classes.entity.Player;
import singRPG.classes.entity.Unit;

class RPG {
    static Util util = new Util();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
        util.clearScreen();

        util.printLine();
        System.out.println("Welcome to SingRPG");
        util.printLine();
        System.out.println("Select player:");
        System.out.println("[1] Player 1 (op)");
        System.out.println("[2] Player 2");
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

        Player player = SaveSystem.read(userAction);

        // Player player = new Player(100, 10, 10, 20, 10, "Sing", true);
        Unit enemy = new Unit(100, 5, 5, 0, 0, "Wolf", false, 0);
        Game game = new Game(player, enemy);
        util.clearScreen();
        boolean win = game.start();
        if (win)
            System.out.println("You win!");
        else
            System.out.println("You lose!");

        SaveSystem.write(player, userAction);
    }
}
