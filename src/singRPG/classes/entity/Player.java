package singRPG.classes.entity;

public class Player extends Unit {
    double MP = 50;
    double maxMP = 50;

    public Player(double health, double attack, double Defence, double mattack, double mdefence, double mp, String n,
            boolean isPlayer, double EXP) {
        super(health, attack, Defence, mattack, mdefence, n, isPlayer, EXP);
        maxMP = mp;
        MP = mp;
    }

    public Player() {
        super();
    }

    public void useMagic(double c) {
        MP -= c;
    }

    public void setMP(double mp) {
        MP = mp;
    }

    public double getMP() {
        return MP;
    }

    public void setMaxMP(double mp) {
        maxMP = mp;
    }

    public double getMaxMP() {
        return maxMP;
    }
}