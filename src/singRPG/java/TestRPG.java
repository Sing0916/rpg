package singRPG.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import singRPG.system.MagicSystem;
import singRPG.system.SaveSystem;

public class TestRPG {
    static Util util = new Util();
    static Scanner scan = new Scanner(System.in);
    static String array[];

    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
        util.clearScreen();
        array = SaveSystem.readConfig();
        SaveSystem.update();

        outer: while (true) {
            util.printLine();
            System.out.println("[1] Create new player");
            System.out.println("[2] Create Magic");
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
                    SaveSystem.create();
                    break;
                case 2:
                    MagicSystem.write();
                    break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            util.clearScreen();
        }
    }
}
