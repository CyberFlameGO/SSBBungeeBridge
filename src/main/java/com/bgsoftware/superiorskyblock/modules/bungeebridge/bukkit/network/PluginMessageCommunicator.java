package com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.network;

import com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.SSBBungeeBridge;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.network.packet.IPacketHandler;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public final class PluginMessageCommunicator implements PluginMessageListener {

    private static final String CHANNEL_NAME = "SSBBungee";
    private static final List<IPacketHandler> PACKET_HANDLERS = Arrays.asList();

    private final SSBBungeeBridge module;

    public PluginMessageCommunicator(SSBBungeeBridge module) {
        this.module = module;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if(!channel.equals(CHANNEL_NAME))
            return;

        ByteArrayDataInput messageInput = ByteStreams.newDataInput(message);

        byte packetId = messageInput.readByte();
        handlePacket(packetId, messageInput);
    }

    public void registerCommunicator() {
        JavaPlugin plugin = this.module.getJavaPlugin();
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, CHANNEL_NAME, this);
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, CHANNEL_NAME);
    }

    public void unregisterCommunicator() {
        JavaPlugin plugin = this.module.getJavaPlugin();
        plugin.getServer().getMessenger().unregisterIncomingPluginChannel(plugin, CHANNEL_NAME);
        plugin.getServer().getMessenger().unregisterOutgoingPluginChannel(plugin, CHANNEL_NAME);
    }

    public void handlePacket(byte packetId, ByteArrayDataInput messageInput) {
        if(packetId > PACKET_HANDLERS.size())
            return;

        PACKET_HANDLERS.get(packetId + 1).handlePacket(messageInput);
    }

}
