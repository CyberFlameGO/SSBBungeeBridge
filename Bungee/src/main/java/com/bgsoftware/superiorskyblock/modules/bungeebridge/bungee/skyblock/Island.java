package com.bgsoftware.superiorskyblock.modules.bungeebridge.bungee.skyblock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class Island {

    private final UUID islandUUID;
    private final Set<UUID> players = new HashSet<>();

    public Island(UUID islandUUID) {
        this.islandUUID = islandUUID;
    }

    public UUID getUniqueId() {
        return islandUUID;
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
