package com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.listener;

import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.SSBBungeeBridge;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.skyblock.Island;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public final class PlayersListener implements Listener {

    private final SSBBungeeBridge plugin;
    private final Set<UUID> joiningProxyPlayers = new HashSet<>();

    public PlayersListener(SSBBungeeBridge plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PostLoginEvent event) {
        joiningProxyPlayers.add(event.getPlayer().getUniqueId());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerConnect(ServerConnectEvent event) {
        ProxiedPlayer proxiedPlayer = event.getPlayer();

        if (!this.joiningProxyPlayers.remove(proxiedPlayer.getUniqueId()))
            return;

        Optional<Island> playerIsland = this.plugin.getStorage().getIsland(proxiedPlayer);
        String playerIslandServer;

        if (!playerIsland.isPresent()) {
            playerIslandServer = "Lobby";
            // TODO: Send to default hub.
        } else {
            playerIslandServer = playerIsland.get().getServerName();
        }

        String targetServer = event.getTarget().getName();

        this.plugin.getProxy().getConsole().sendMessage(
                new TextComponent("Target: " + targetServer + ", Island: " + playerIslandServer));

        ServerInfo playerIslandServerInfo = this.plugin.getProxy().getServerInfo(playerIslandServer);

        if(playerIslandServerInfo == null)
            return;

        if (!playerIslandServer.equalsIgnoreCase(targetServer)) {
            event.setTarget(playerIslandServerInfo);
        }

        proxiedPlayer.setReconnectServer(playerIslandServerInfo);
    }

}
