package com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.skyblock;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public final class IslandsStorage {

    private final Map<UUID, Island> islands = new HashMap<>();
    private final Map<UUID, Island> playerIslands = new HashMap<>();

    public IslandsStorage() {

    }

    public void removeIsland(UUID islandUUID) {
        Island island = this.islands.remove(islandUUID);
        if (island != null) {
            for (UUID playerUUID : island.getPlayers())
                this.playerIslands.remove(playerUUID);
        }
    }

    public void createIsland(UUID islandUUID, UUID playerUUID, String serverName) {
        Island island = new Island(islandUUID, serverName);
        island.addPlayer(playerUUID);

        this.islands.put(islandUUID, island);
        this.playerIslands.put(playerUUID, island);
    }

    public void addToIsland(UUID playerUUID, UUID islandUUID) {
        Island island = this.islands.get(islandUUID);
        if(island != null) {
            island.addPlayer(playerUUID);
            this.playerIslands.put(playerUUID, island);
        }
    }

    public void removeFromIsland(UUID playerUUID) {
        Island island = this.playerIslands.remove(playerUUID);
        if (island != null) {
            island.removePlayer(playerUUID);
        }
    }

    public Optional<Island> getIsland(ProxiedPlayer proxiedPlayer) {
        return Optional.ofNullable(this.playerIslands.get(proxiedPlayer.getUniqueId()));
    }

}
