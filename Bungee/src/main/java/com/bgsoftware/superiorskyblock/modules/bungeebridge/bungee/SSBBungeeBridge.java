package com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee;

import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.listener.PlayersListener;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.network.PluginMessageCommunicator;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.network.packet.in.PacketIslandCreateHandler;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.network.packet.in.PacketIslandDisbandHandler;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.network.packet.in.PacketIslandJoinHandler;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.network.packet.in.PacketIslandLeaveHandler;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.skyblock.IslandsStorage;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.Arrays;

public final class SSBBungeeBridge extends Plugin {

    private final PluginMessageCommunicator communicator = new PluginMessageCommunicator(this, Arrays.asList(
            new PacketIslandCreateHandler(this), new PacketIslandDisbandHandler(this),
            new PacketIslandJoinHandler(this), new PacketIslandLeaveHandler(this)
    ));

    private final IslandsStorage storage = new IslandsStorage();

    @Override
    public void onEnable() {
        this.communicator.registerCommunicator();
        registerListeners();
    }

    @Override
    public void onDisable() {
        this.communicator.unregisterCommunicator();
    }

    private void registerListeners() {
        getProxy().getPluginManager().registerListener(this, new PlayersListener(this));
    }

    public PluginMessageCommunicator getCommunicator() {
        return communicator;
    }

    public IslandsStorage getStorage() {
        return storage;
    }

}
