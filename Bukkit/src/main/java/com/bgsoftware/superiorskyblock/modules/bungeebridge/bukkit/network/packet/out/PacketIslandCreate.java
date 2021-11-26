package com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.network.packet.out;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import java.util.UUID;

public final class PacketIslandCreate implements IPacket {

    private final UUID islandUUID;

    public PacketIslandCreate(UUID islandUUID) {
        this.islandUUID = islandUUID;
    }

    @Override
    public byte getPacketId() {
        return 1;
    }

    @Override
    public void serialize(ByteArrayDataOutput dataOutput) {
        dataOutput.writeLong(this.islandUUID.getMostSignificantBits());
        dataOutput.writeLong(this.islandUUID.getLeastSignificantBits());
    }

}
