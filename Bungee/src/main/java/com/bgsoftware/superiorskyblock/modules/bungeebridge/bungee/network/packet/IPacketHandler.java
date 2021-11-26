package com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.network.packet;

import com.google.common.io.ByteArrayDataInput;

public interface IPacketHandler {

    void handlePacket(ByteArrayDataInput messageInput);

}
