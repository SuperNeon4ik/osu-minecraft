package me.superneon4ik.mcosu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.UUID;

public class Essentials {
    private static final Vector3F PrespawnLocation = new Vector3F(-53, 69, -298);

    /**
     * @param nameTag Text that ArmorStand shows
     * @param position Location of ArmorStand
     * @return Entity UUID
     */
    public static UUID SpawnArmorStand(String nameTag, Location position) {
        ArmorStand stand = (ArmorStand) position.getWorld().spawnEntity(position, EntityType.ARMOR_STAND);
        //stand.setInvisible(true);
        stand.setVisible(false);
        stand.setInvulnerable(true);
        stand.setCustomName(ChatColor.translateAlternateColorCodes('&', nameTag));
        stand.setCustomNameVisible(true);
        stand.setGravity(false);
        stand.setCollidable(false);
        stand.setMarker(true);

        Main.addArmorStand(stand);

        return stand.getUniqueId();
    }
}
