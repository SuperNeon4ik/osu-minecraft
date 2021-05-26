package me.superneon4ik.mcosu.commands;

import me.superneon4ik.mcosu.Essentials;
import me.superneon4ik.mcosu.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class TestSpawnArmorstandCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            StringBuilder text = new StringBuilder("&9&lTest");
            if (strings.length >= 1) {
                text = new StringBuilder();
                for (var str: strings) text.append(str).append(" ");
                text.trimToSize();
            }

            Player sender = (Player) commandSender;
            Essentials.SpawnArmorStand(text.toString(), sender.getLocation());
        }

        return true;
    }
}
