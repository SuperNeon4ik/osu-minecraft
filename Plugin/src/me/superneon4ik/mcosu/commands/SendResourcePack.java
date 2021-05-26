package me.superneon4ik.mcosu.commands;

import me.superneon4ik.mcosu.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class SendResourcePack implements CommandExecutor {
    private static final String resourcePackLinkDropbox = "https://www.dropbox.com/s/7fyhm4w7d8j28gu/McOsuPack.zip?dl=1";
    //private static final String resourcePackLinkGithub = "http://github.com/SuperNeon4ik/osu-minecraft-resources/releases/latest/download/McOsuPack.zip";

    @Override public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {
            /*URL url = new URL(resourcePackLinkGithub);
            URLConnection connection = url.openConnection();
            connection.connect();
            Scanner sc = new Scanner(connection.getURL().openStream());
            StringBuffer sb = new StringBuffer();
            while(sc.hasNext()) {
                sb.append(sc.next());
            }
            String result = sb.toString();
            result = result.replaceAll("<[^>]*>", "");*/
            String downloadLink = resourcePackLinkDropbox; //result.split("\n")[0];
            Main.getPlugin().sendMessage("Generated RP Download Link: " + downloadLink);

            if (strings.length >= 1) {
                Player player = Bukkit.getPlayer(strings[0]);
                if (player != null) {
                    player.setResourcePack(downloadLink);
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "&a&l" + player.getName() + " &ais being prompted to the resource pack download screen."));
                } else {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCan't find player specified."));
                }
            } else {
                for (var p : Bukkit.getOnlinePlayers()) {
                    p.setResourcePack(downloadLink);
                }
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        "&a&lAll players &ajust got prompted to the resource pack download screen."));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
