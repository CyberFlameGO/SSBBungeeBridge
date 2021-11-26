package com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.skyblock;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class Island {

    private final UUID islandUUID;
    private final Set<UUID> players = new HashSet<>();
    private final String serverName;

    public Island(UUID islandUUID, String serverName) {
        this.islandUUID = islandUUID;
        this.serverName = serverName;
    }

    public UUID getUniqueId() {
        return islandUUID;
    }

    public String getServerName() {
        return serverName;
    }

    public void addPlayer(UUID playerUUID) {
        this.players.add(playerUUID);
    }

    public void removePlayer(UUID playerUUID) {
        this.players.remove(playerUUID);
    }

    public Collection<UUID> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

}
