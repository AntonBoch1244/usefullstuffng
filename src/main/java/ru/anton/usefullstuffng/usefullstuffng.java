package ru.anton.usefullstuffng;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ru.anton.usefullstuffng.commands.*;
import ru.anton.usefullstuffng.events.autoAcceptingEula;

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
        e.registerServerCommand(new commandGetNBTData());
    }
}
