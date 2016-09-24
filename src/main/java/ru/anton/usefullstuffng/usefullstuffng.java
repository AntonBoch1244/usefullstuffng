package ru.anton.usefullstuffng;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import ru.anton.usefullstuffng.commands.*;
import ru.anton.usefullstuffng.events.autoAcceptingEula;

import java.io.File;
import java.io.IOException;

@Mod(
        modid = reference.modid,
        name = reference.modname,
        acceptedMinecraftVersions = reference.acceptedMinecraftVersion,
        version = reference.version,
        acceptableRemoteVersions = "*"
)

public class usefullstuffng {

    @Mod.Instance(reference.modid)
    public static usefullstuffng instance;

    @SideOnly(Side.SERVER)
    @Mod.EventHandler
    public void serverPreInit(FMLPreInitializationEvent e) {
        new autoAcceptingEula();
    }

    @SideOnly(Side.CLIENT)
    @Mod.EventHandler
    public void clientConstruct(FMLConstructionEvent e) {
        System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL","true");
        for (String i : System.getProperties().stringPropertyNames()){
            //DEBUG
            System.out.println(i + ": " + System.getProperties().getProperty(i));
        }
    }
    @SideOnly(Side.CLIENT)
    @Mod.EventHandler
    public void clientPreInit(FMLPreInitializationEvent e) {
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent e) {
        e.registerServerCommand(new commandGetChunk());
        e.registerServerCommand(new commandGetBank());
        e.registerServerCommand(new commandSpawn());
        e.registerServerCommand(new commandHardstop());
        e.registerServerCommand(new commandTPS());
        // TODO: WB MAKE CRAFT
        // TODO e.registerServerCommand(new commandWorkbench());
        e.registerServerCommand(new commandBlockScan());
        // TODO: NBT Data Getter
        // TODO e.registerServerCommand(new commandGetNBTData());
        // TODO WIP MileStone 1.1 e.registerServerCommand(new commandChunkManage());
    }
}
