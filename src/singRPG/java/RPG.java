package singRPG.java;

import singRPG.entity.Unit;

class RPG {
    static Util util = new Util();

    public static void main(String[] args) {
        util.clearScreen();

        Unit player = new Unit(100, 10, 10, "Sing", true);
        Unit enemy = new Unit(100, 5, 5, "Wolf", false);
        Game game = new Game(player, enemy);
        boolean win = game.start();
        System.out.println("Ended!");
        if (win)
            System.out.println("You win!");
        else
            System.out.println("You lose!");
    }
}
