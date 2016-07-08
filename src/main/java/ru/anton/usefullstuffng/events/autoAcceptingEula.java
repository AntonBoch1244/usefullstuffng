package ru.anton.usefullstuffng.events;

import java.io.*;
import java.util.Properties;

/**
 * This class use for auto accepting Mojang AB EULA on Modded Minecraft Server with Minecraft Forge or Forge Mod Loader
 * @author anton and R4K
 */
public class autoAcceptingEula {
    /**
     * This method use for auto accept EULA for starting Minecraft Server
     * <b>R4K Thanks to you!</b>
     */
    public autoAcceptingEula() {
        if (!fileCheck()) {
            try {
                FileWriter fw = new FileWriter("eula.txt");
                fw.write("eula=true");
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This function checking file eula.txt in server root
     * @return  if file is exist and eula property is true then true everything else are false
     * <b>R4K Thanks to you!</b>
     */
    private Boolean fileCheck() {
        try {
            FileInputStream fis = new FileInputStream( new File("eula.txt"));
            Properties props = new Properties();
            props.load(fis);
            return java.lang.Boolean.parseBoolean(props.getProperty("eula"));
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

    }
}
