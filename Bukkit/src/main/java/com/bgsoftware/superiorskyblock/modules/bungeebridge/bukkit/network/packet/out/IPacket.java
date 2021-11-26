package com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.network.packet.out;

import com.google.common.io.ByteArrayDataOutput;

public interface IPacket {

    byte getPacketId();

    void serialize(ByteArrayDataOutput dataOutput);

}
