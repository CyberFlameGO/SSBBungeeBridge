package com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.network.packet.out;

import com.google.common.io.ByteArrayDataOutput;

import java.util.UUID;

public final class PacketIslandJoin implements IPacket {

    private final UUID playerUUID;
    private final UUID islandUUID;

    public PacketIslandJoin(UUID playerUUID, UUID islandUUID) {
        this.playerUUID = playerUUID;
        this.islandUUID = islandUUID;
    }

    @Override
    public byte getPacketId() {
        return 3;
    }

    @Override
    public void serialize(ByteArrayDataOutput dataOutput) {
        dataOutput.writeLong(this.playerUUID.getMostSignificantBits());
        dataOutput.writeLong(this.playerUUID.getLeastSignificantBits());
        dataOutput.writeLong(this.islandUUID.getMostSignificantBits());
        dataOutput.writeLong(this.islandUUID.getLeastSignificantBits());
    }

}
