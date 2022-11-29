package singRPG.entity;

import singRPG.constant.DmgType;

public class Unit {
    double HP = 1;
    double maxHP = 1;
    double ATK = 1;
    double DEF = 1;
    double MATK = 1;
    double MDEF = 1;
    String name = "empty unit";
    boolean isEnemy = true;

    public Unit() {
    }

    public Unit(double health, double attack, double Defence, double mattack, double mdefence, String n,
            boolean isPlayer) {
        HP = health;
        maxHP = health;
        ATK = attack;
        DEF = Defence;
        name = n;
        MATK = mattack;
        MDEF = mdefence;
        if (isPlayer)
            isEnemy = false;
    }

    public double takeDMG(double dmg, DmgType type) {
        double dmgDeal = 0;
        switch (type) {
            case PHY:
                dmgDeal = dmg - DEF;
                if (dmgDeal > 0)
                    HP -= dmgDeal;
                break;
            case MAG:
                dmgDeal = dmg - MDEF;
                if (dmgDeal > 0)
                    HP -= dmgDeal;
                break;
            case TRE:
                dmgDeal = dmg;
                HP -= dmgDeal;
                break;
        }
        return dmgDeal;
    }

    public double DEFUP() {
        DEF += 5;
        return DEF;
    }

    public double pwrUp() {
        ATK += 5;
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

    public void setIsEnemy(boolean t) {
        isEnemy = t;
    }

    public boolean getIsEnemy() {
        return isEnemy;
    }
}
