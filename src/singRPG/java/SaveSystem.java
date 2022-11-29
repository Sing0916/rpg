package singRPG.java;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import singRPG.classes.entity.Player;

public class SaveSystem {
    public static void write(Player p, int user) {
        JSONObject playerDetails = new JSONObject();
        playerDetails.put("Name", p.getNAME());
        playerDetails.put("MaxHp", p.getMaxHP());
        playerDetails.put("MaxMp", p.getMaxMP());
        playerDetails.put("Atk", p.getOATK());
        playerDetails.put("Def", p.getODEF());
        playerDetails.put("MAtk", p.getOMATK());
        playerDetails.put("MDef", p.getOMDEF());
        playerDetails.put("Exp", p.getEXP());

        try (FileWriter file = new FileWriter("rpg/save/player" + user + ".json")) {
            file.write(playerDetails.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Player read(int user)
            throws org.json.simple.parser.ParseException, FileNotFoundException, IOException {
        Object obj = new JSONParser().parse(new FileReader("rpg/save/player" + user + ".json"));
        JSONObject jo = (JSONObject) obj;
        String name = (String) jo.get("Name");
        double maxHP = (double) jo.get("MaxHp");
        double maxMP = (double) jo.get("MaxMp");
        double atk = (double) jo.get("Atk");
        double def = (double) jo.get("Def");
        double matk = (double) jo.get("MAtk");
        double mdef = (double) jo.get("MDef");
        double exp = (double) jo.get("Exp");
        Player p = new Player(maxHP, atk, def, matk, mdef, maxMP, name, true, exp);
        return p;
    }
}