package ru.anton.usefullstuffng.commands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

import javax.annotation.Nullable;
import java.util.List;

public class commandGetNBTData implements ICommand {
    @Override
    public String getCommandName() {
        return "getNBT";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "getNBT";
    }

    @Override
    public List getCommandAliases() {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        TileEntity tileEntity = sender.getEntityWorld().getTileEntity(Integer.valueOf(args[0]), Integer.valueOf(args[1]), Integer.valueOf(args[2]));
        //Entity entity = tileEntity;
        String outputMessage = String.valueOf(tileEntity);
        sender.addChatMessage(new ChatComponentText(outputMessage));
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
