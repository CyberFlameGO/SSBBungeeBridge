package com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee;

import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.network.PluginMessageCommunicator;
import net.md_5.bungee.api.plugin.Plugin;

public final class SSBBungeeBridge extends Plugin {

    private final PluginMessageCommunicator communicator = new PluginMessageCommunicator(this);

    @Override
    public void onEnable() {
        this.communicator.registerCommunicator();
    }

    @Override
    public void onDisable() {
        this.communicator.unregisterCommunicator();
    }

}
