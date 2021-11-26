package com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.network.packet.in;

import com.google.common.io.ByteArrayDataInput;
import org.bukkit.entity.Player;

public interface IPacketHandler {

    void handlePacket(Player player, ByteArrayDataInput messageInput);

}
