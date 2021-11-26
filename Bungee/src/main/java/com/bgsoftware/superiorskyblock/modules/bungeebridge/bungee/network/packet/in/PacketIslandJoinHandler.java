package com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.network.packet.in;

import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.SSBBungeeBridge;
import com.google.common.io.ByteArrayDataInput;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public final class PacketIslandJoinHandler implements IPacketHandler {

    private final SSBBungeeBridge plugin;

    public PacketIslandJoinHandler(SSBBungeeBridge plugin) {
        this.plugin = plugin;
    }

    @Override
    public void handlePacket(ProxiedPlayer proxiedPlayer, ByteArrayDataInput messageInput) {
        long uuidMSB = messageInput.readLong();
        long uuidLSB = messageInput.readLong();
        UUID playerUUID = new UUID(uuidMSB, uuidLSB);

        uuidMSB = messageInput.readLong();
        uuidLSB = messageInput.readLong();

        UUID islandUUID = new UUID(uuidMSB, uuidLSB);

        this.plugin.getStorage().addToIsland(playerUUID, islandUUID);
    }

}
