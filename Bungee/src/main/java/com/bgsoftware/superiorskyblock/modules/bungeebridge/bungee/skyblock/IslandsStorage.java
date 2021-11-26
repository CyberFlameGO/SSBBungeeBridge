package com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.skyblock;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public final class IslandsStorage {

    private final Map<UUID, UUID> playerIslands = new HashMap<>();

    public IslandsStorage() {

    }

    public void setIsland(ProxiedPlayer proxiedPlayer, UUID islandUUID) {
        this.playerIslands.put(proxiedPlayer.getUniqueId(), islandUUID);
    }

    public Optional<UUID> getIsland(ProxiedPlayer proxiedPlayer) {
        return Optional.ofNullable(this.playerIslands.get(proxiedPlayer.getUniqueId()));
    }

}
