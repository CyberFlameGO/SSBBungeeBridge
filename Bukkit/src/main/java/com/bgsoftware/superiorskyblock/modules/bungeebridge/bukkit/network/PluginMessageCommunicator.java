package com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.network;

import com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.SSBBungeeBridge;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.network.packet.out.IPacket;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.network.packet.in.IPacketHandler;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public final class PluginMessageCommunicator implements PluginMessageListener {

    private static final String CHANNEL_NAME = "ssb:bungee";

    private final SSBBungeeBridge module;
    private final List<IPacketHandler> packetHandlers;

    public PluginMessageCommunicator(SSBBungeeBridge module) {
        this.module = module;
        this.packetHandlers = Arrays.asList();
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals(CHANNEL_NAME))
            return;

        ByteArrayDataInput messageInput = ByteStreams.newDataInput(message);

        byte packetId = messageInput.readByte();
        handlePacket(packetId, player, messageInput);
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

    public void handlePacket(byte packetId, Player player, ByteArrayDataInput messageInput) {
        if (packetId > packetHandlers.size())
            return;

        packetHandlers.get(packetId - 1).handlePacket(player, messageInput);
    }

    public void sendPacket(Player player, IPacket packet) {
        JavaPlugin plugin = this.module.getJavaPlugin();

        ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
        dataOutput.writeByte(packet.getPacketId());
        packet.serialize(dataOutput);

        player.sendPluginMessage(plugin, CHANNEL_NAME, dataOutput.toByteArray());
    }

}
