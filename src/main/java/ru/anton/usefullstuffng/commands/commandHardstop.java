package ru.anton.usefullstuffng.commands;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class commandHardstop implements ICommand {

    @Override
    public String getCommandName() {
        return "hardstop";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "hardstop - is kill-switch for server";
    }

    @Override
    public List getCommandAliases() {
        return Arrays.asList(new String[] {
                "hs",
                "killserver"
        });
    }

    @Override
    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        new FMLCommonHandler().exitJava(0, true);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        if (p_71519_1_.getCommandSenderName() == "Server") {
            return true;
        }
        else {
            return false;
        }
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
