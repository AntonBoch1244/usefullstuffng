package ru.anton.usefullstuffng.commands;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import ru.anton.usefullstuffng.advenced.BlockScanningThread;
import ru.anton.usefullstuffng.advenced.IBlockScan;
import scala.tools.nsc.ScalaDoc;

import javax.annotation.Nullable;
import java.util.List;


public class commandBlockScan implements ICommand {

    @Override
    public String getCommandName() {
        return "blockscan";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "blockscan <radius(must be int)> <blockName1> [blockNameN] - blocks scanning in the player square radius by name of block";
    }

    @Override
    public List getCommandAliases() {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        BlockScanningThread blockScanningRun = new BlockScanningThread();
        blockScanningRun.setAllArgs(sender, args);
        Thread scanThread = new Thread(blockScanningRun.scanThread);
        scanThread.start();
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return !sender.getEntityWorld().isRemote & sender.getEntityWorld().playerEntities.size()==1;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return (args.length >= 1 ? CommandBase.getListOfStringsFromIterableMatchingLastWord(args, Block.blockRegistry.getKeys()) : null);
    }

    @Override
    public boolean isUsernameIndex(String[] usernameList, int index) {
        return false;
    }

    @Override
    public int compareTo(@Nullable Object o) {
        return 0;
    }
}
