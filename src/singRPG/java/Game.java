package singRPG.java;

import java.util.Arrays;
import java.util.Scanner;

import singRPG.entity.Magic;
import singRPG.entity.Player;
import singRPG.entity.Unit;
import singRPG.constant.Colours;
import singRPG.constant.DmgType;

public class Game {
    static Player player = new Player();
    static Unit enemy = new Unit();
    static Util util = new Util();
    static Magic magics[] = new Magic[3];
    static Scanner scan = new Scanner(System.in);

    public Game(Player p, Unit e) {
        player = p;
        enemy = e;
        magics[0] = new Magic(10.0, 5.0, "Fire Ball", 9);
        magics[1] = new Magic(20.0, 10.0, "Thunder Strike", 6);
        magics[2] = new Magic(50.0, 25.0, "Shadow Claw", 3);
    }

    public boolean start() {
        int counter = 1;

        while (true) {
            System.out
                    .println(Colours.ANSI_YELLOW + "------Round " + counter + " Start------" + Colours.ANSI_RESET);
            showDetail(enemy);
            showDetail(player);
            System.out.println("[0]: Attack");
            System.out.println("[1]: Magic");
            System.out.println("[2]: Defence");
            System.out.println("[3]: Power Up");

            // user action
            int userAction = -1;
            boolean firstAction = true;
            while (true) {
                userAction = scan.nextInt();
                if ((userAction >= 0) && (userAction <= 3)) {
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
            playerAction(userAction, player, enemy);
            if ((enemy.getHP() <= 0)) {
                System.out.println("Enemy is dead!");
                break;
            }

            // enemy action
            int r = (int) (Math.random() * 3);
            enemyAction(r, enemy, player);
            if ((player.getHP() <= 0)) {
                System.out.println("You are dead!");
                break;
            }

            userAction = -1;
            counter++;
            util.clearScreen();
            if (player.getMP() < 50)
                player.setMP(player.getMP() + 5);
        }
        return enemy.getHP() == 0 ? true : false;
    }

    public static void playerAction(int input, Player from, Unit to) {
        double tmp;
        switch (input) {
            case 0:
                util.clearScreen();
                util.printLine();
                tmp = to.takeDMG(from.getATK(), DmgType.PHY);
                System.out.println(from.getNAME() + " used Attack!");
                if (tmp > 0)
                    System.out.println(to.getNAME() + " takes " + (int) tmp + " damage!");
                else
                    System.out.println(to.getNAME() + " takes 0 damage!");
                System.out.println(to.getNAME() + " current HP is " + to.getHP());
                break;
            case 1:
                util.clearLine(5);
                for (Magic magic : magics) {
                    System.out.println("[" + Arrays.asList(magics).indexOf(magic) + "]: " + magic.getNAME() + " - DMG: "
                            + magic.getDMG() + ", COST: " + magic.getCOST() + ", HIT CHANCE: "
                            + (double) magic.getChance() / 10);
                }
                int userAction = -1;
                boolean firstAction = true;
                while (true) {
                    userAction = scan.nextInt();
                    if ((userAction >= 0) && (userAction <= 2)) {
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
                util.clearScreen();
                util.printLine();
                System.out.println(from.getNAME() + " used " + magics[userAction].getNAME() + "!");
                int r = (int) (Math.random() * 10);
                if (r > magics[userAction].getChance()) {
                    tmp = to.takeDMG(magics[userAction].getDMG(), DmgType.MAG);
                    from.useMagic(magics[userAction].getCOST());
                } else
                    tmp = 0;
                if (tmp > 0)
                    System.out.println(to.getNAME() + " takes " + (int) tmp + " damage!");
                else
                    System.out.println(from.getNAME() + "'s Magic missed!");
                System.out.println(to.getNAME() + " current HP is " + to.getHP());
                break;
            case 2:
                util.clearScreen();
                util.printLine();
                tmp = from.DEFUP();
                System.out.println(from.getNAME() + " used Defence!");
                System.out.println(from.getNAME() + "'s defence changed to " + (int) tmp);
                break;
            case 3:
                util.clearScreen();
                util.printLine();
                tmp = from.pwrUp();
                System.out.println(from.getNAME() + " used Power Up!");
                System.out.println(from.getNAME() + "'s attack changed to " + (int) tmp);
                break;
        }
        util.printLine();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void enemyAction(int input, Unit from, Player to) {
        double tmp;
        switch (input) {
            case 0:
                tmp = to.takeDMG(from.getATK(), DmgType.PHY);
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
        util.printLine();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void showDetail(Unit u) {
        if (u.getIsEnemy())
            System.out.println("Enemy: " + u.getNAME() + ", ATK:" + (int) u.getATK() + ", DEF:" + (int) u.getDEF());
        else
            System.out.println("Player: " + u.getNAME() + ", ATK:" + (int) u.getATK() + ", DEF:" + (int) u.getDEF());

        int p = (int) Math.floor((u.getHP() / u.getMaxHP()) * 20);
        System.out.print("HP: " + (int) u.getHP() + "/" + (int) u.getMaxHP() + " [");
        for (int i = 0; i < 20; i++) {
            if (i < p)
                System.out.print(Colours.ANSI_GREEN + "=" + Colours.ANSI_RESET);
            else
                System.out.print(Colours.ANSI_RED + "-" + Colours.ANSI_RESET);
        }
        System.out.print("]");
        System.out.println("");
        util.printLine();
    }

    public static void showDetail(Player u) {
        if (u.getIsEnemy())
            System.out.println("Enemy: " + u.getNAME() + ", ATK:" + (int) u.getATK() + ", DEF:" + (int) u.getDEF());
        else
            System.out.println("Player: " + u.getNAME() + ", ATK:" + (int) u.getATK() + ", DEF:" + (int) u.getDEF());

        int p = (int) Math.floor((u.getHP() / u.getMaxHP()) * 20);
        System.out.print("HP: " + (int) u.getHP() + "/" + (int) u.getMaxHP() + " [");
        for (int i = 0; i < 20; i++) {
            if (i < p)
                System.out.print(Colours.ANSI_GREEN + "=" + Colours.ANSI_RESET);
            else
                System.out.print(Colours.ANSI_RED + "-" + Colours.ANSI_RESET);
        }
        p = (int) Math.floor((u.getMP() / u.getMaxMP()) * 20);
        System.out.print("] MP: " + (int) u.getMP() + "/" + (int) u.getMaxMP() + " [");
        for (int i = 0; i < 20; i++) {
            if (i < p)
                System.out.print(Colours.ANSI_CYAN + "=" + Colours.ANSI_RESET);
            else
                System.out.print(Colours.ANSI_RED + "-" + Colours.ANSI_RESET);
        }
        System.out.print("]");
        System.out.println("");
        util.printLine();
    }
}