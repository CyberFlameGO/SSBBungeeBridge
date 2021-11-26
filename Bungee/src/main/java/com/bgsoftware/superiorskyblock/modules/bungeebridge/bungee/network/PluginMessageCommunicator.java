package com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.network;

import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.SSBBungeeBridge;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.network.packet.IPacketHandler;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public final class PluginMessageCommunicator implements Listener {

    private static final String CHANNEL_NAME = "ssb:bungee";
    private static final List<IPacketHandler> PACKET_HANDLERS = Arrays.asList();

    private final SSBBungeeBridge plugin;

    public PluginMessageCommunicator(SSBBungeeBridge plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPluginMessageEvent(PluginMessageEvent event) {
        if (!event.getTag().equals(CHANNEL_NAME))
            return;

        ByteArrayDataInput messageInput = ByteStreams.newDataInput(event.getData());

        byte packetId = messageInput.readByte();
        handlePacket(packetId, messageInput);
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

    public void handlePacket(byte packetId, ByteArrayDataInput messageInput) {
        if(packetId > PACKET_HANDLERS.size())
            return;

        PACKET_HANDLERS.get(packetId + 1).handlePacket(messageInput);
    }

}
