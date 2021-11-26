package com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.network;

import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.SSBBungeeBridge;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.network.packet.in.IPacketHandler;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.network.packet.in.PacketIslandCreateHandler;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public final class PluginMessageCommunicator implements Listener {

    private static final String CHANNEL_NAME = "ssb:bungee";

    private final SSBBungeeBridge plugin;
    private final List<IPacketHandler> packetHandlers;

    public PluginMessageCommunicator(SSBBungeeBridge plugin) {
        this.plugin = plugin;
        this.packetHandlers = Arrays.asList(new PacketIslandCreateHandler(plugin));
    }

    @EventHandler
    public void onPluginMessageEvent(PluginMessageEvent event) {
        if (!event.getTag().equals(CHANNEL_NAME) || !(event.getReceiver() instanceof ProxiedPlayer))
            return;

        ByteArrayDataInput messageInput = ByteStreams.newDataInput(event.getData());

        byte packetId = messageInput.readByte();
        handlePacket(packetId, (ProxiedPlayer) event.getReceiver(), messageInput);
    }

    public void registerCommunicator() {
        ProxyServer proxy = this.plugin.getProxy();
        proxy.registerChannel(CHANNEL_NAME);
        proxy.getPluginManager().registerListener(this.plugin, this);
    }

    public void unregisterCommunicator() {
        ProxyServer proxy = this.plugin.getProxy();
        proxy.unregisterChannel(CHANNEL_NAME);
        proxy.getPluginManager().unregisterListener(this);
    }

    public void handlePacket(byte packetId, ProxiedPlayer proxiedPlayer, ByteArrayDataInput messageInput) {
        if(packetId > packetHandlers.size())
            return;

        packetHandlers.get(packetId - 1).handlePacket(proxiedPlayer, messageInput);
    }

}
