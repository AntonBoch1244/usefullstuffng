package ru.anton.usefullstuffng.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.network.play.server.S2DPacketOpenWindow;

import javax.annotation.Nullable;
import java.util.List;

//For Reasons Can't work
public class commandWorkbench implements ICommand {
    @Override
    public String getCommandName() {
        return "workbench";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "workbench - open workbench without workbench in inventory";
    }

    @Override
    public List getCommandAliases() {
        return null;
    }

    @Override
    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        EntityPlayerMP iPlayer = CommandBase.getCommandSenderAsPlayer(p_71515_1_);
        iPlayer.getNextWindowId();
        iPlayer.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(iPlayer.currentWindowId, 1, "Air Workbench", 9, true));
        iPlayer.openContainer = new ContainerWorkbench(iPlayer.inventory, iPlayer.getEntityWorld(), iPlayer.getPlayerCoordinates().posX, iPlayer.getPlayerCoordinates().posY, iPlayer.getPlayerCoordinates().posZ);
        iPlayer.openContainer.windowId = iPlayer.currentWindowId;
        iPlayer.openContainer.addCraftingToCrafters(iPlayer);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    @Override
    public int compareTo(@Nullable Object o) {
        return 0;
    }
}
