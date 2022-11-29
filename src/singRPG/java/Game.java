package singRPG.java;

import java.util.Arrays;
import java.util.Scanner;

import singRPG.classes.Magic;
import singRPG.classes.entity.Player;
import singRPG.classes.entity.Unit;
import singRPG.constant.Colours;
import singRPG.constant.enums.DmgType;
import singRPG.constant.enums.MagicType;

public class Game {
    static Player player = new Player();
    static Unit enemy = new Unit();
    static Util util = new Util();
    static Magic magics[] = new Magic[4];
    static Scanner scan = new Scanner(System.in);

    public Game(Player p, Unit e) {
        player = p;
        enemy = e;
        magics[0] = new Magic(10.0, 5.0, "Fire Ball", 9, MagicType.DMG);
        magics[1] = new Magic(20.0, 10.0, "Thunder Strike", 6, MagicType.DMG);
        magics[2] = new Magic(50.0, 25.0, "Shadow Claw", 3, MagicType.DMG);
        magics[3] = new Magic(20.0, 10.0, "Heal", 9, MagicType.HEAL);
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
                player.setEXP(player.getEXP() + 10);
                return true;
            }

            // enemy action
            int r = (int) (Math.random() * 3);
            enemyAction(r, enemy, player);
            if ((player.getHP() <= 0)) {
                System.out.println("You are dead!");
                return false;
            }

            userAction = -1;
            counter++;
            util.clearScreen();
            if (player.getMP() < 50)
                player.setMP(player.getMP() + 5);
        }
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
                System.out.println(to.getNAME() + " current HP is " + (int) to.getHP());
                break;
            case 1:
                util.clearLine(5);
                for (Magic magic : magics) {
                    switch (magic.getType()) {
                        case BUFF:
                            break;
                        case DMG:
                            System.out.println(
                                    "[" + Arrays.asList(magics).indexOf(magic) + "]: " + magic.getNAME() + " - Damage: "
                                            + magic.getAMT() + ", Cost: " + magic.getCOST() + ", Hit Chance: "
                                            + (double) magic.getChance() / 10);
                            break;
                        case HEAL:
                            System.out.println(
                                    "[" + Arrays.asList(magics).indexOf(magic) + "]: " + magic.getNAME() + " - Amount: "
                                            + magic.getAMT() + ", Cost: " + magic.getCOST() + ", Hit Chance: "
                                            + (double) magic.getChance() / 10);
                            break;
                    }
                }
                int userAction = -1;
                boolean firstAction = true;
                while (true) {
                    userAction = scan.nextInt();
                    if (((userAction >= 0) && (userAction <= 3)) || (userAction == 898)) {
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
                if (userAction == 898) {
                    System.out.println(from.getNAME() + " used cheats!");
                    tmp = to.takeDMG(to.getHP() - 1, DmgType.TRE);
                    System.out.println(to.getNAME() + " takes " + (int) tmp + " damage!");
                    System.out.println(to.getNAME() + " current HP is " + (int) to.getHP());
                } else {
                    System.out.println(from.getNAME() + " used " + magics[userAction].getNAME() + "!");
                    switch (magics[userAction].getType()) {
                        case DMG:
                            int r = (int) (Math.random() * 10);
                            if (r <= magics[userAction].getChance()) {
                                tmp = to.takeDMG(magics[userAction].getAMT(), DmgType.MAG);
                                from.useMagic(magics[userAction].getCOST());
                            } else
                                tmp = 0;
                            if (tmp > 0)
                                System.out.println(to.getNAME() + " takes " + (int) tmp + " damage!");
                            else
                                System.out.println(from.getNAME() + "'s Magic missed!");
                            System.out.println(to.getNAME() + " current HP is " + (int) to.getHP());
                            break;
                        case HEAL:
                            double amt = from.heal(magics[userAction].getAMT());
                            System.out
                                    .println(from.getNAME() + " healed for " + (int) amt + " HP!");
                            System.out.println(from.getNAME() + " current HP is " + (int) to.getHP());
                            break;
                        case BUFF:
                            break;
                    }
                }
                break;
            case 2:
                util.clearScreen();
                util.printLine();
                tmp = from.defUP();
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
                System.out.println(to.getNAME() + " current HP is " + (int) to.getHP());
                break;
            case 1:
                tmp = from.defUP();
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
            System.out.println(Colours.ANSI_PURPLE + "Lv." + (int) u.getLevel() + Colours.ANSI_RESET + " " + u.getNAME()
                    + ", ATK:" + (int) u.getATK() + ", DEF:"
                    + (int) u.getDEF());
        else
            System.out.println(Colours.ANSI_PURPLE + "Lv." + (int) u.getLevel() + Colours.ANSI_RESET + " " + u.getNAME()
                    + ", ATK:" + (int) u.getATK() + ", DEF:" + (int) u.getDEF());

        int p = (int) Math.floor((u.getHP() / u.getMaxHP()) * 20);
        System.out.print("HP: " + (int) u.getHP() + "/" + (int) u.getMaxHP() + " [");
        for (int i = 0; i < 20; i++) {
            if (i < p)
                System.out.print(Colours.ANSI_GREEN + "=" + Colours.ANSI_RESET);
            else {
                if ((p == 0) && (i == 0))
                    System.out.print(Colours.ANSI_GREEN + "|" + Colours.ANSI_RESET);
                else
                    System.out.print(Colours.ANSI_RED + "-" + Colours.ANSI_RESET);
            }
        }
        System.out.print("]");
        System.out.println("");
        util.printLine();
    }

    public static void showDetail(Player u) {
        if (u.getIsEnemy())
            System.out.println(Colours.ANSI_PURPLE + "Lv." + (int) u.getLevel() + Colours.ANSI_RESET + " " + u.getNAME()
                    + ", ATK:" + (int) u.getATK() + ", DEF:" + (int) u.getDEF());
        else
            System.out.println(Colours.ANSI_PURPLE + "Lv." + (int) u.getLevel() + Colours.ANSI_RESET + " " + u.getNAME()
                    + ", ATK:" + (int) u.getATK() + ", DEF:" + (int) u.getDEF());

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
            else {
                if ((p == 0) && (i == 0))
                    System.out.print(Colours.ANSI_GREEN + "|" + Colours.ANSI_RESET);
                else
                    System.out.print(Colours.ANSI_RED + "-" + Colours.ANSI_RESET);
            }
        }
        System.out.print("]");
        System.out.println("");
        util.printLine();
    }
}