package ru.anton.usefullstuffng.events;

import cpw.mods.fml.common.FMLLog;

import java.io.*;
import java.util.Properties;

public class eulaFuck {
    public eulaFuck() {
        if (!fileCheck()) {
            try {
                FileWriter fw = new FileWriter("eula.txt");
                fw.write("eula=true");
                fw.flush();
                fw.close();
                FMLLog.info("EULA IS FUCKED!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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
