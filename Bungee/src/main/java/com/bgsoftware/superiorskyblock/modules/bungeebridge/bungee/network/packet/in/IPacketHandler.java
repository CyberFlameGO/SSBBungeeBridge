package com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.network.packet.in;

import com.google.common.io.ByteArrayDataInput;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public interface IPacketHandler {

    void handlePacket(ProxiedPlayer proxiedPlayer, ByteArrayDataInput messageInput);

}
