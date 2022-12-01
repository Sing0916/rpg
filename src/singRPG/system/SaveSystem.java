package singRPG.system;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import singRPG.classes.entity.Player;

public class SaveSystem {
    static Scanner scan = new Scanner(System.in);
    static String array[] = new String[3];

    // current version = 1.1
    public static String[] readConfig()
            throws org.json.simple.parser.ParseException, FileNotFoundException, IOException {
        Object obj = new JSONParser().parse(new FileReader("save/config.json"));
        JSONObject jo = (JSONObject) obj;
        array[0] = (String) jo.get("Name");
        array[1] = (String) jo.get("Version");
        int temp = Integer.parseInt((String) jo.get("Player"));
        array[2] = Integer.toString(temp);
        return array;
    }

    public static void write(Player p, int user) {
        JSONObject playerDetails = new JSONObject();
        playerDetails.put("Name", p.getNAME());
        playerDetails.put("MaxHp", p.getBaseHP());
        playerDetails.put("MaxMp", p.getBaseMP());
        playerDetails.put("Atk", p.getOATK());
        playerDetails.put("Def", p.getODEF());
        playerDetails.put("MAtk", p.getOMATK());
        playerDetails.put("MDef", p.getOMDEF());
        playerDetails.put("Exp", p.getEXP());
        playerDetails.put("Level", p.getLevel());
        playerDetails.put("version", array[1]);

        try (FileWriter file = new FileWriter("save/player" + user + ".json")) {
            file.write(playerDetails.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Player read(int user)
            throws org.json.simple.parser.ParseException, FileNotFoundException, IOException {
        Object obj = new JSONParser().parse(new FileReader("save/player" + user + ".json"));
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

    public static void update() throws org.json.simple.parser.ParseException, FileNotFoundException, IOException {
        for (int i = 1; i <= 2; i++) {
            Object obj = new JSONParser().parse(new FileReader("save/player" + i + ".json"));
            JSONObject jo = (JSONObject) obj;
            String version = (String) jo.get("version");
            switch (version) {
                case "1.0":
                    jo.put("Level", 0);
                    break;
            }
            jo.replace("version", array[1]);
            try (FileWriter file = new FileWriter("save/player" + i + ".json")) {
                file.write(jo.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void create() throws FileNotFoundException, IOException, ParseException {
        System.out.print("Name: ");
        String name = scan.nextLine();
        JSONObject playerDetails = new JSONObject();
        playerDetails.put("Name", name);
        playerDetails.put("MaxHp", 100);
        playerDetails.put("MaxMp", 10);
        playerDetails.put("Atk", 10);
        playerDetails.put("Def", 10);
        playerDetails.put("MAtk", 20);
        playerDetails.put("MDef", 10);
        playerDetails.put("Exp", 0);
        playerDetails.put("Level", 0);
        playerDetails.put("version", array[1]);
        int temp = Integer.parseInt(array[2]) + 1;

        try (FileWriter file = new FileWriter("save/player" + temp + ".json")) {
            file.write(playerDetails.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object obj = new JSONParser().parse(new FileReader("save/config.json"));
        JSONObject jo = (JSONObject) obj;
        array[0] = (String) jo.get("Name");
        array[1] = (String) jo.get("Version");
        array[2] = (String) jo.get("Player");

        JSONObject config = new JSONObject();
        config.put("Name", array[0]);
        config.put("Version", array[1]);
        config.put("Player", Integer.toString(Integer.parseInt(array[2]) + 1));

        try (FileWriter file = new FileWriter("save/config.json")) {
            file.write(config.toJSONString());
            file.flush();
            System.out.println("Success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}