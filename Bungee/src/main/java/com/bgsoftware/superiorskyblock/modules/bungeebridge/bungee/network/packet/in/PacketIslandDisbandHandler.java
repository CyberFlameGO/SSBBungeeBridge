package com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.network.packet.in;

import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.SSBBungeeBridge;
import com.google.common.io.ByteArrayDataInput;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public final class PacketIslandDisbandHandler implements IPacketHandler {

    private final SSBBungeeBridge plugin;

    public PacketIslandDisbandHandler(SSBBungeeBridge plugin) {
        this.plugin = plugin;
    }

    @Override
    public void handlePacket(ProxiedPlayer proxiedPlayer, ByteArrayDataInput messageInput) {
        long uuidMSB = messageInput.readLong();
        long uuidLSB = messageInput.readLong();

        UUID islandUUID = new UUID(uuidMSB, uuidLSB);

        this.plugin.getStorage().removeIsland(islandUUID);
    }

}
