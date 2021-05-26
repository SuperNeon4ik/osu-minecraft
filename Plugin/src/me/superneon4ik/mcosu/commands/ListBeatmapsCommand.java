package me.superneon4ik.mcosu.commands;

import me.superneon4ik.mcosu.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.IOException;

public class ListBeatmapsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage(ChatColor.BOLD + "All available beatmaps:");
        for (int i = 0; i < Main.Beatmaps.size(); i++) {
            commandSender.sendMessage(i + ". " + ChatColor.GRAY + Main.Beatmaps.get(i).getName());
        }

        return true;
    }
}
