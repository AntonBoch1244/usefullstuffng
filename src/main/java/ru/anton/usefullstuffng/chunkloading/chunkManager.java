package ru.anton.usefullstuffng.chunkloading;

import net.minecraft.command.ICommandSender;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import ru.anton.usefullstuffng.usefullstuffng;

/**
 * //TODO: Make Chunkload Command!
 * For now is not implemented yet!
 */
public class chunkManager {

    private static class forceChunk extends ForgeChunkManager.ForceChunkEvent {

        forceChunk(ForgeChunkManager.Ticket ticket, ChunkCoordIntPair location) {
            super(ticket, location);
        }
    }

    private static class unForceChunk extends ForgeChunkManager.UnforceChunkEvent {

        unForceChunk(ForgeChunkManager.Ticket ticket, ChunkCoordIntPair location) {
            super(ticket, location);
        }
    }

    public static void requestChunkLoad(World world, ICommandSender sender, int x, int z) {
        new forceChunk(ForgeChunkManager.requestTicket(usefullstuffng.instance, world, ForgeChunkManager.Type.ENTITY), world.getChunkFromBlockCoords(x, z).getChunkCoordIntPair());
    }

    public static void requestChunkUnload(World world, ICommandSender sender, int x, int z) {
        ForgeChunkManager.Ticket ticket = ForgeChunkManager.requestTicket(usefullstuffng.instance, world, ForgeChunkManager.Type.ENTITY);
        new unForceChunk(ticket, world.getChunkFromBlockCoords(x, z).getChunkCoordIntPair());
        ForgeChunkManager.releaseTicket(ticket);
    }
}
