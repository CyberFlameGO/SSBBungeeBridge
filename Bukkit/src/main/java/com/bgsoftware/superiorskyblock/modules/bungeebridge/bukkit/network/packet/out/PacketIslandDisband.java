package com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.network.packet.out;

import com.google.common.io.ByteArrayDataOutput;

import java.util.UUID;

public final class PacketIslandDisband implements IPacket {

    private final UUID islandUUID;

    public PacketIslandDisband(UUID islandUUID) {
        this.islandUUID = islandUUID;
    }

    @Override
    public byte getPacketId() {
        return 2;
    }

    @Override
    public void serialize(ByteArrayDataOutput dataOutput) {
        dataOutput.writeLong(this.islandUUID.getMostSignificantBits());
        dataOutput.writeLong(this.islandUUID.getLeastSignificantBits());
    }

}
