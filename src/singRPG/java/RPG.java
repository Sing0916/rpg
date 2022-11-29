package singRPG.java;

import singRPG.entity.Unit;

class RPG {
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Unit player = new Unit(100, 10, 10, "Player");
        Unit enemy = new Unit(100, 5, 5, "Enemy");
        Game game = new Game(player, enemy);
        game.run();
    }
}
