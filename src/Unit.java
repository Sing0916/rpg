public class Unit {
    double HP;
    double maxHP;
    double ATK;
    double DEF;
    String name;

    Unit(double health, double attack, double DEFence, String n) {
        HP = health;
        maxHP = health;
        ATK = attack;
        DEF = DEFence;
        name = n;
    }

    public double takeDMG(double dmg) {
        double dmgDeal = dmg - DEF;
        if (dmgDeal > 0)
            HP = HP - dmgDeal;
        return dmgDeal;
    }

    public double DEFUP() {
        DEF = DEF + 5;
        return DEF;
    }

    public double pwrUp() {
        ATK = ATK + 5;
        return ATK;
    }

    public void setHP(double health) {
        HP = health;
    }

    public double getHP() {
        return HP;
    }

    public void setATK(double attack) {
        ATK = attack;
    }

    public double getATK() {
        return ATK;
    }

    public void setDEF(double DEFence) {
        DEF = DEFence;
    }

    public double getDEF() {
        return DEF;
    }

    public void setNAME(String n) {
        name = n;
    }

    public String getNAME() {
        return name;
    }

    public void setMaxHP(double health) {
        maxHP = health;
    }

    public double getMaxHP() {
        return maxHP;
    }
}
