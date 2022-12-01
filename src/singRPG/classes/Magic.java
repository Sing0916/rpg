package singRPG.classes;

import singRPG.constant.enums.BuffType;
import singRPG.constant.enums.MagicType;

public class Magic {
    double AMT = 1;
    double COST = 1;
    String name = "empty magic";
    int chance = 1;// 0-9
    MagicType Mtype;
    BuffType Btype;

    public Magic(double d, double c, String n, int ch, MagicType t, BuffType t2) {
        AMT = d;
        COST = c;
        name = n;
        chance = ch;
        Mtype = t;
        Btype = t2;
    }

    Magic() {
    }

    public double getAMT() {
        return AMT;
    }

    public void setAMT(double d) {
        AMT = d;
    }

    public double getCOST() {
        return COST;
    }

    public void setCOST(double c) {
        COST = c;
    }

    public String getNAME() {
        return name;
    }

    public void setNAME(String n) {
        name = n;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int c) {
        chance = c;
    }

    public MagicType getMagicType() {
        return Mtype;
    }

    public void setMagicType(MagicType t) {
        Mtype = t;
    }

    public BuffType getBuffType() {
        return Btype;
    }

    public void setBuffType(BuffType t) {
        Btype = t;
    }
}
