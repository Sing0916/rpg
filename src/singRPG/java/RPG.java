package singRPG.java;

import singRPG.entity.Player;
import singRPG.entity.Unit;

class RPG {
    static Util util = new Util();

    public static void main(String[] args) {
        util.clearScreen();

        Player player = new Player(100, 10, 10, 20, 10, "Sing", true);
        Unit enemy = new Unit(100, 5, 5, 0, 0, "Wolf", false);
        Game game = new Game(player, enemy);
        boolean win = game.start();
        if (win)
            System.out.println("You win!");
        else
            System.out.println("You lose!");
    }
}
