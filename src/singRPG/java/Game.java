package singRPG.java;

import java.util.Scanner;
import singRPG.entity.Unit;
import singRPG.constant.Colours;
import singRPG.java.Util;

public class Game {
    static Unit player = new Unit();
    static Unit enemy = new Unit();
    static Util util = new Util();

    public Game(Unit p, Unit e) {
        player = p;
        enemy = e;
    }

    public boolean start() {
        try (Scanner scan = new Scanner(System.in)) {
            int counter = 1;

            while (true) {
                System.out
                        .println(Colours.ANSI_YELLOW + "------Round " + counter + " Start------" + Colours.ANSI_RESET);
                showDetail(enemy);
                showDetail(player);
                System.out.println("[0]: Attack");
                System.out.println("[1]: Defence");
                System.out.println("[2]: Power Up");

                // user action
                int userAction = -1;
                boolean firstAction = true;
                while (true) {
                    userAction = scan.nextInt();
                    if ((userAction >= 0) && (userAction <= 2)) {
                        util.clearScreen();
                        System.out.println(Colours.ANSI_YELLOW + "-------------------------" + Colours.ANSI_RESET);
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
                action(userAction, player, enemy);
                if ((enemy.getHP() <= 0)) {
                    System.out.println("Enemy is dead!");
                    break;
                }

                // enemy action
                int r = (int) (Math.random() * 3);
                action(r, enemy, player);
                if ((player.getHP() <= 0)) {
                    System.out.println("You are dead!");
                    break;
                }

                userAction = -1;
                counter++;
                util.clearScreen();
            }
            return enemy.getHP() == 0 ? true : false;
        }
    }

    public static void action(int input, Unit from, Unit to) {
        double tmp;
        switch (input) {
            case 0:
                tmp = to.takeDMG(from.getATK());
                System.out.println(from.getNAME() + " used Attack!");
                if (tmp > 0)
                    System.out.println(to.getNAME() + " takes " + (int) tmp + " damage!");
                else
                    System.out.println(to.getNAME() + " takes 0 damage!");
                System.out.println(to.getNAME() + " current HP is " + to.getHP());
                break;
            case 1:
                tmp = from.DEFUP();
                System.out.println(from.getNAME() + " used Defence!");
                System.out.println(from.getNAME() + "'s defence changed to " + (int) tmp);
                break;
            case 2:
                tmp = from.pwrUp();
                System.out.println(from.getNAME() + " used Power Up!");
                System.out.println(from.getNAME() + "'s attack changed to " + (int) tmp);
                break;
        }
        System.out.println(Colours.ANSI_YELLOW + "-------------------------" + Colours.ANSI_RESET);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void showDetail(Unit u) {
        double HP = u.getHP();
        double maxHP = u.getMaxHP();

        if (u.getIsEnemy())
            System.out.println("Enemy: " + u.getNAME());
        else
            System.out.println("Player: " + u.getNAME());

        int p = (int) Math.floor((HP / maxHP) * 20);
        System.out.print("HP: " + (int) HP + "/" + (int) maxHP + " [");
        for (int i = 0; i < 20; i++) {
            if (i < p)
                System.out.print(Colours.ANSI_GREEN + "=" + Colours.ANSI_RESET);
            else
                System.out.print(Colours.ANSI_RED + "-" + Colours.ANSI_RESET);
        }
        System.out.print("]");
        System.out.print(" ATK:" + (int) u.getATK() + ", DEF:" + (int) u.getDEF());
        System.out.println("");
        System.out.println(Colours.ANSI_YELLOW + "-------------------------" + Colours.ANSI_RESET);
    }
}