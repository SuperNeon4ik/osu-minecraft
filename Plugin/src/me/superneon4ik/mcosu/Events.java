package me.superneon4ik.mcosu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {
    private static final Vector3F defaultSpawnPosition = new Vector3F(-57.5f, 69, -298);

    @EventHandler public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Location spawn = defaultSpawnPosition.toLocation(Bukkit.getWorld("world"));
        spawn.setYaw(90);
        player.teleport(spawn);

        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&e&lWARNING!!! &7This project is under development\n" +
                        "&f&lProject is located @&7 https://github.com/SuperNeon4ik/osu-minecraft \n" +
                        "&eosu! is made by &cpeppy&e, osu!minecraft is made by &cSuperNeon4ik.\n" +
                        "&cSuperNeon4ik is &lNOT &caffiliated with ppy"));
        event.setJoinMessage(null);
    }
}
