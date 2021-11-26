package com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.network.packet.out;

import com.google.common.io.ByteArrayDataOutput;

import java.util.UUID;

public final class PacketIslandLeave implements IPacket {

    private final UUID playerUUID;

    public PacketIslandLeave(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    @Override
    public byte getPacketId() {
        return 4;
    }

    @Override
    public void serialize(ByteArrayDataOutput dataOutput) {
        dataOutput.writeLong(this.playerUUID.getMostSignificantBits());
        dataOutput.writeLong(this.playerUUID.getLeastSignificantBits());
    }

}
