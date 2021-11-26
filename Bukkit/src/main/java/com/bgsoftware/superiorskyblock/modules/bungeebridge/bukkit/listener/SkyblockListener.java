package com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.listener;

import com.bgsoftware.superiorskyblock.api.events.IslandCreateEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandDisbandEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandJoinEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandKickEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandQuitEvent;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.SSBBungeeBridge;
import com.bgsoftware.superiorskyblock.modules.bungeebridge.bukkit.network.packet.out.PacketIslandCreate;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public final class SkyblockListener implements Listener {

    private final SSBBungeeBridge module;

    public SkyblockListener(SSBBungeeBridge module) {
        this.module = module;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onIslandCreate(IslandCreateEvent event) {
        Player player = event.getPlayer().asPlayer();
        if(player != null)
            this.module.getCommunicator().sendPacket(player, new PacketIslandCreate(event.getIsland().getUniqueId()));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onIslandDisband(IslandDisbandEvent event) {

    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onIslandQuit(IslandQuitEvent event) {

    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onIslandJoin(IslandJoinEvent event) {

    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onIslandKick(IslandKickEvent event) {

    }

}
