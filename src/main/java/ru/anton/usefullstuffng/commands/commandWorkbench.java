package ru.anton.usefullstuffng.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.network.play.server.S2DPacketOpenWindow;

import javax.annotation.Nullable;
import java.util.List;

//For reasons can't work | Yes in mod file are commented registering of command because workbench is not working for me
public class commandWorkbench implements ICommand {
    @Override
    public String getCommandName() {
        return "workbench";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "workbench - open workbench without workbench in inventory";
    }

    @Override
    public List getCommandAliases() {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        EntityPlayerMP iPlayer = CommandBase.getCommandSenderAsPlayer(sender);
        iPlayer.getNextWindowId();
        iPlayer.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(iPlayer.currentWindowId, 1, "Air Workbench", 9, true));
        iPlayer.openContainer = new ContainerWorkbench(iPlayer.inventory, iPlayer.getEntityWorld(), iPlayer.getPlayerCoordinates().posX, iPlayer.getPlayerCoordinates().posY, iPlayer.getPlayerCoordinates().posZ);
        iPlayer.openContainer.windowId = iPlayer.currentWindowId;
        iPlayer.openContainer.addCraftingToCrafters(iPlayer);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return null;
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
