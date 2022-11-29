import java.util.Scanner;

class rpg {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Hello, World!");

        Unit player = new Unit(100, 10, 10, "Player");
        Unit enemy = new Unit(100, 5, 5, "Enemy");
        boolean userActionValid = false;
        int counter = 1;

        while (true) {
            System.out.println("------Round " + counter + " Start------");
            System.out.println(
                    "Enemy HP: " + enemy.getHP() + " (ATK:" + enemy.getATK() + ", DEF:" + enemy.getDEF() + ")");
            showHP(enemy.getHP(), enemy.getMaxHP());
            System.out.println("--------------------");
            System.out.println(
                    "Player HP: " + player.getHP() + " (ATK:" + player.getATK() + ", DEF:" + player.getDEF() + ")");
            showHP(player.getHP(), player.getMaxHP());
            System.out.println("--------------------");
            System.out.println("[0]: Attack");
            System.out.println("[1]: Defence");
            System.out.println("[2]: Power Up");

            // user action
            int userAction = -1;
            while (!userActionValid) {
                userAction = scan.nextInt();
                System.out.print("\033[1F\33[K");
                System.out.flush();
                if ((userAction >= 0) && (userAction <= 2)) {
                    userActionValid = true;
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("--------------------");
                } else {
                    System.out.print("\033[1F\33[K");
                    System.out.flush();
                    System.out.println("Invalid input!");
                }
            }
            action(userAction, player, enemy);
            if ((enemy.getHP() <= 0)) {
                break;
            }

            // enemy action
            int r = (int) (Math.random() * 3);
            action(r, enemy, player);
            if ((player.getHP() <= 0)) {
                break;
            }

            userActionValid = false;
            userAction = -1;
            counter++;
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        System.out.println("Ended!");
        if (enemy.getHP() <= 0)
            System.out.println("You win!");
        else if (player.getHP() <= 0)
            System.out.println("You lose!");
        else
            System.out.println("Error end");
    }

    public static void action(int input, Unit from, Unit to) {
        double tmp;
        switch (input) {
            case 0:
                tmp = to.takeDMG(from.getATK());
                System.out.println(from.getNAME() + " used Attack!");
                if (tmp > 0)
                    System.out.println(to.getNAME() + " takes " + tmp + " damage!");
                else
                    System.out.println(to.getNAME() + " takes 0 damage!");
                System.out.println(to.getNAME() + " current HP is " + to.getHP());
                break;
            case 1:
                tmp = from.DEFUP();
                System.out.println(from.getNAME() + " used Defence!");
                System.out.println(from.getNAME() + "'s defence changed to " + tmp);
                break;
            case 2:
                tmp = from.pwrUp();
                System.out.println(from.getNAME() + " used Power Up!");
                System.out.println(from.getNAME() + "'s attack changed to " + tmp);
                break;
        }
        System.out.println("--------------------");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void showHP(double HP, double maxHP) {
        int p = (int) Math.floor((HP / maxHP) * 10);
        System.out.print("[");
        for (int i = 0; i < 10; i++) {
            if (i < p)
                System.out.print(ANSI_GREEN + "*" + ANSI_RESET);
            else
                System.out.print(ANSI_RED + "=" + ANSI_RESET);
        }
        System.out.print("]");
        System.out.println();
    }
}