package singRPG.classes.entity;

import singRPG.constant.enums.BuffType;
import singRPG.constant.enums.DmgType;

public class Unit {
    double HP = 1;
    double ATK = 1;
    double DEF = 1;
    double MATK = 1;
    double MDEF = 1;

    double maxHP = 1;
    double oATK = 1;
    double oDEF = 1;
    double oMATK = 1;
    double oMDEF = 1;

    double baseHP = 1;
    double exp = 0;
    double level = 0;

    String name = "empty unit";
    boolean isEnemy = true;
    boolean shielded = false;

    public Unit() {
    }

    public Unit(double health, double attack, double Defence, double mattack, double mdefence, String n,
            boolean isPlayer, double EXP) {
        maxHP = health + 10 * Math.floor(EXP / 100);
        oATK = attack;
        oDEF = Defence;
        oMATK = mattack;
        oMDEF = mdefence;

        HP = maxHP;
        ATK = oATK + 1 * Math.floor(EXP / 100);
        DEF = oDEF + 1 * Math.floor(EXP / 100);
        MATK = oMATK + 1 * Math.floor(EXP / 100);
        MDEF = oMDEF + 1 * Math.floor(EXP / 100);

        if (EXP <= 0)
            exp = 0;
        else
            exp = EXP;
        level = Math.floor(exp / 100);
        baseHP = health;
        name = n;
        if (isPlayer)
            isEnemy = false;
    }

    public void updateLV() {
        double tmp = level;
        level = Math.floor(exp / 100);
        if (tmp != level) {
            maxHP = baseHP + 10 * level;
            HP = maxHP;
            System.out.println("Level up! " + tmp + " -> " + level);
        }
    }

    public double takeDMG(double dmg, DmgType type) {
        double dmgDeal = 0;
        if (shielded) {
            shielded = false;
            return dmgDeal;
        } else {
            switch (type) {
                case PHY:
                    dmgDeal = dmg - DEF;
                    if (dmgDeal > 0)
                        if (dmgDeal < HP)
                            HP -= dmgDeal;
                        else {
                            dmgDeal = HP;
                            HP = 0;
                        }
                    break;
                case MAG:
                    dmgDeal = dmg - MDEF;
                    if (dmgDeal > 0)
                        if (dmgDeal < HP)
                            HP -= dmgDeal;
                        else {
                            dmgDeal = HP;
                            HP = 0;
                        }
                    break;
                case TRE:
                    dmgDeal = dmg;
                    HP -= dmgDeal;
                    break;
            }
            return dmgDeal;
        }
    }

    public double heal(double h) {
        double healAMT = 0;
        if ((maxHP - HP) >= h) {
            healAMT = h;
            HP += healAMT;
        } else {
            healAMT = maxHP - HP;
            HP += healAMT;
        }
        return healAMT;
    }

    public void buff(double a, BuffType t) {
        switch (t) {
            case ATK:
                ATK += a;
                break;
            case DEF:
                DEF += a;
                break;
            case MATK:
                MATK += a;
                break;
            case MDEF:
                MDEF += a;
                break;
            case HP:
                HP += a;
                break;
            case NULL:
                break;
        }
    }

    public void shield() {
        shielded = true;
    }

    public double defUP() {
        DEF += 2;
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

    public void setMATK(double mATK) {
        MATK = mATK;
    }

    public double getMATK() {
        return MATK;
    }

    public void setMDEF(double mDEF) {
        MDEF = mDEF;
    }

    public double getMDEF() {
        return MDEF;
    }

    public void setOATK(double attack) {
        oATK = attack;
    }

    public double getOATK() {
        return oATK;
    }

    public void setODEF(double DEFence) {
        oDEF = DEFence;
    }

    public double getODEF() {
        return oDEF;
    }

    public void setOMATK(double mATK) {
        oMATK = mATK;
    }

    public double getOMATK() {
        return oMATK;
    }

    public void setOMDEF(double mDEF) {
        oMDEF = mDEF;
    }

    public double getOMDEF() {
        return oMDEF;
    }

    public void setEXP(double EXP) {
        exp = EXP;
    }

    public double getEXP() {
        return exp;
    }

    public void setBaseHP(double hp) {
        baseHP = hp;
    }

    public double getBaseHP() {
        return baseHP;
    }

    public void setLevel(double lv) {
        level = lv;
    }

    public double getLevel() {
        return level;
    }
}
