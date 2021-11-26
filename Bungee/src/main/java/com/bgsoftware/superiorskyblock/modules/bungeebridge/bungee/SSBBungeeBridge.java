package com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee;

import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.network.PluginMessageCommunicator;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.skyblock.IslandsStorage;
import net.md_5.bungee.api.plugin.Plugin;

public final class SSBBungeeBridge extends Plugin {

    private final PluginMessageCommunicator communicator = new PluginMessageCommunicator(this);
    private final IslandsStorage storage = new IslandsStorage();

    @Override
    public void onEnable() {
        this.communicator.registerCommunicator();
    }

    @Override
    public void onDisable() {
        this.communicator.unregisterCommunicator();
    }

    public PluginMessageCommunicator getCommunicator() {
        return communicator;
    }

    public IslandsStorage getStorage() {
        return storage;
    }

}
