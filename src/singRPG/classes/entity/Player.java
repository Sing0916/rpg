package singRPG.classes.entity;

public class Player extends Unit {
    double MP = 50;
    double maxMP = 50;
    double baseMP = 50;

    public Player(double health, double attack, double Defence, double mattack, double mdefence, double mp, String n,
            boolean isPlayer, double EXP) {
        super(health, attack, Defence, mattack, mdefence, n, isPlayer, EXP);
        baseMP = mp;
        maxMP = mp + 5 * Math.floor(EXP / 100);
        MP = maxMP;
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

    public void setBaseMP(double mp) {
        baseMP = mp;
    }

    public double getBaseMP() {
        return baseMP;
    }
}