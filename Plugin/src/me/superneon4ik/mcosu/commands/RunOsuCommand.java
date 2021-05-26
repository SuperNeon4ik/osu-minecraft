package me.superneon4ik.mcosu.commands;

import me.superneon4ik.mcosu.Main;
import me.superneon4ik.mcosu.osu.Beatmap;
import me.superneon4ik.mcosu.osu.BeatmapParser;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RunOsuCommand implements CommandExecutor {
    @Override public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Searching for beatmap provided. "
                + ChatColor.GREEN + "Beatmaps Count : " + Main.Beatmaps.size());
        try {
            Beatmap bm = BeatmapParser.FromFile(Main.Beatmaps.get(Integer.parseInt(strings[0])));
            commandSender.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Found beatmap specified. \n" +
                    ChatColor.GREEN + "Loading : "
                    + String.format("%1$s - %2$s (%3$s) [%4$s]", bm.Artist, bm.Title, bm.Creator, bm.Version));

            Main.Game.PlayGame(bm);
        } catch (Exception exception) {
            commandSender.sendMessage("" + ChatColor.RED + ChatColor.BOLD + "Error. Check your input.");
            exception.printStackTrace();
        }
        return true;
    }
}
