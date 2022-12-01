package singRPG.system;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import singRPG.classes.Magic;
import singRPG.constant.enums.BuffType;
import singRPG.constant.enums.MagicType;

public class MagicSystem {
    static Scanner scan = new Scanner(System.in);

    public static Magic[] readMagic() throws FileNotFoundException, IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("config/magic.json"));
        JSONObject jo = (JSONObject) obj;
        int number = ((Long) jo.get("number")).intValue();
        Magic magics[] = new Magic[number];
        JSONArray jsonArray = (JSONArray) jo.get("magics");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jo2 = (JSONObject) jsonArray.get(i);
            String name = (String) jo2.get("Name");
            double amt = (double) jo2.get("Amount");
            double cost = (double) jo2.get("Cost");
            int chance = ((Long) jo2.get("Chance")).intValue();
            MagicType mtype = MagicType.valueOf((String) jo2.get("MagicType"));
            BuffType btype = BuffType.valueOf((String) jo2.get("BuffType"));
            magics[i] = new Magic(amt, cost, name, chance, mtype, btype);
        }
        return magics;
    }

    public static void createMagic() throws FileNotFoundException, IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("config/magic.json"));
        JSONObject jo = (JSONObject) obj;
        int number = ((Long) jo.get("number")).intValue();
        Magic magics[] = new Magic[number + 1];
        JSONArray jsonArray = (JSONArray) jo.get("magics");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jo2 = (JSONObject) jsonArray.get(i);
            String name = (String) jo2.get("Name");
            double amt = (double) jo2.get("Amount");
            double cost = (double) jo2.get("Cost");
            int chance = ((Long) jo2.get("Chance")).intValue();
            MagicType mtype = MagicType.valueOf((String) jo2.get("MagicType"));
            BuffType btype = BuffType.valueOf((String) jo2.get("BuffType"));
            magics[i] = new Magic(amt, cost, name, chance, mtype, btype);
        }

        System.out.print("Magic Type(DMG,BUFF,HEAL): ");
        String mtypeS = scan.nextLine();
        System.out.print("Buff Type(ATK,DEF,MATK,MDEF,HP)(NULL): ");
        String btypeS = scan.nextLine();
        System.out.print("Name: ");
        String name = scan.nextLine();
        System.out.print("Amount: ");
        double amt = scan.nextDouble();
        System.out.print("Cost: ");
        double cost = scan.nextDouble();
        System.out.print("Chance(0-9): ");
        int chance = scan.nextInt();
        MagicType mtype = MagicType.valueOf(mtypeS);
        if (btypeS.equals("NULL")) {
            magics[number] = new Magic(amt, cost, name, chance, mtype, BuffType.NULL);
        } else {
            BuffType btype = BuffType.valueOf(btypeS);
            magics[number] = new Magic(amt, cost, name, chance, mtype, btype);
        }

        JSONArray outArray = new JSONArray();
        for (int i = 0; i < number + 1; i++) {
            HashMap<String, Object> MagicDetails = new HashMap<String, Object>();
            MagicDetails.put("Name", magics[i].getNAME());
            MagicDetails.put("Amount", magics[i].getAMT());
            MagicDetails.put("Cost", magics[i].getCOST());
            MagicDetails.put("Chance", magics[i].getChance());
            MagicDetails.put("MagicType", magics[i].getMagicType().name());
            MagicDetails.put("BuffType", magics[i].getBuffType().name());
            JSONObject MagicDetailsJ = new JSONObject(MagicDetails);
            outArray.add(MagicDetailsJ);
        }
        HashMap<String, Object> magic = new HashMap<String, Object>();
        magic.put("magics", outArray);
        magic.put("number", number + 1);
        JSONObject magicJ = new JSONObject(magic);
        try (FileWriter file = new FileWriter("config/magic.json")) {
            file.write(magicJ.toJSONString());
            file.flush();
            System.out.println("Success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write() {
        Magic magics[] = new Magic[5];
        JSONArray ja = new JSONArray();
        magics[0] = new Magic(10.0, 5.0, "Fire Ball", 9, MagicType.DMG, BuffType.NULL);
        magics[1] = new Magic(20.0, 10.0, "Thunder Strike", 6, MagicType.DMG, BuffType.NULL);
        magics[2] = new Magic(50.0, 25.0, "Shadow Claw", 3, MagicType.DMG, BuffType.NULL);
        magics[3] = new Magic(20.0, 10.0, "Heal", 9, MagicType.HEAL, BuffType.NULL);
        magics[4] = new Magic(10.0, 10.0, "Power Up", 9, MagicType.BUFF, BuffType.ATK);

        for (int i = 0; i < 5; i++) {
            HashMap<String, Object> MagicDetails = new HashMap<String, Object>();
            MagicDetails.put("Name", magics[i].getNAME());
            MagicDetails.put("Amount", magics[i].getAMT());
            MagicDetails.put("Cost", magics[i].getCOST());
            MagicDetails.put("Chance", magics[i].getChance());
            MagicDetails.put("MagicType", magics[i].getMagicType().name());
            MagicDetails.put("BuffType", magics[i].getBuffType().name());
            JSONObject MagicDetailsJ = new JSONObject(MagicDetails);
            ja.add(MagicDetailsJ);
        }

        HashMap<String, Object> magic = new HashMap<String, Object>();
        magic.put("magics", ja);
        magic.put("number", 5);
        JSONObject magicJ = new JSONObject(magic);
        try (FileWriter file = new FileWriter("config/magic.json")) {
            file.write(magicJ.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
