package singRPG.entity;

public class Magic {
    double DMG = 1;
    double COST = 1;
    String name = "empty magic";
    int chance = 1;// 0-9

    public Magic(double d, double c, String n, int ch) {
        DMG = d;
        COST = c;
        name = n;
        chance = ch;
    }

    Magic() {
    }

    public double getDMG() {
        return DMG;
    }

    public void setDMG(double d) {
        DMG = d;
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
}
