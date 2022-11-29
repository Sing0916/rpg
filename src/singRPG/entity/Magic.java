package singRPG.entity;

import singRPG.constant.MagicType;

public class Magic {
    double AMT = 1;
    double COST = 1;
    String name = "empty magic";
    int chance = 1;// 0-9
    MagicType type;

    public Magic(double d, double c, String n, int ch, MagicType t) {
        AMT = d;
        COST = c;
        name = n;
        chance = ch;
        type = t;
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

    public MagicType getType() {
        return type;
    }

    public void setType(MagicType t) {
        type = t;
    }
}
