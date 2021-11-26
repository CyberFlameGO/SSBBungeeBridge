package com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblock;
import com.bgsoftware.superiorskyblock.api.commands.SuperiorCommand;
import com.bgsoftware.superiorskyblock.api.modules.PluginModule;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.network.PluginMessageCommunicator;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SSBBungeeBridge extends PluginModule {

    private final PluginMessageCommunicator communicator = new PluginMessageCommunicator(this);
    private SuperiorSkyblock plugin;

    public SSBBungeeBridge() {
        super("SSBBungeeBridge", "Ome_R");
    }

    @Override
    public void onEnable(SuperiorSkyblock plugin) {
        this.plugin = plugin;

        this.communicator.registerCommunicator();
    }

    @Override
    public void onReload(SuperiorSkyblock plugin) {
        this.communicator.unregisterCommunicator();
    }

    @Override
    public void onDisable(SuperiorSkyblock plugin) {
        this.communicator.unregisterCommunicator();
    }

    @Override
    public Listener[] getModuleListeners(SuperiorSkyblock plugin) {
        return new Listener[0];
    }

    @Override
    public SuperiorCommand[] getSuperiorCommands(SuperiorSkyblock plugin) {
        return new SuperiorCommand[0];
    }

    @Override
    public SuperiorCommand[] getSuperiorAdminCommands(SuperiorSkyblock plugin) {
        return new SuperiorCommand[0];
    }

    public SuperiorSkyblock getPlugin() {
        return plugin;
    }

    public JavaPlugin getJavaPlugin() {
        return (JavaPlugin) getPlugin();
    }

}
