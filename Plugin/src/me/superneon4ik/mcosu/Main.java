package me.superneon4ik.mcosu;

import me.superneon4ik.mcosu.osu.Beatmap;
import me.superneon4ik.mcosu.osu.BeatmapParser;
import me.superneon4ik.mcosu.osu.OsuGame;
import me.superneon4ik.mcosu.osu.OsuUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends JavaPlugin {
    private static Main plugin;
    private static final ArrayList<ArmorStand> armorStands = new ArrayList<>();

    private static Path DataFolder;
    public static ArrayList<File> Beatmaps = new ArrayList<>();

    public static OsuGame Game;

    @Override public void onEnable() {
        plugin = this;

        if (!getDataFolder().exists()) {
            try {
               DataFolder = Files.createDirectory(Paths.get(getDataFolder().getAbsolutePath()));
               sendMessage("&aSuccessfully created dataFolder : " + getDataFolder().getAbsolutePath());
            } catch (IOException e) {
                DataFolder = null;
                sendMessage("&cFailed to create dataFolder : " + getDataFolder().getAbsolutePath());
                e.printStackTrace();
            }
        } else {
            DataFolder = Paths.get(getDataFolder().getAbsolutePath());
        }

        if (DataFolder != null && DataFolder.toFile().listFiles() != null) {
            for (var f : Objects.requireNonNull(DataFolder.toFile().listFiles())) {
                if (f.getName().endsWith(".osu")) {
                    Beatmaps.add(f);
                    sendMessage("&aAdded beatmap path : " + f.getPath());
                }
                else {
                    sendMessage("&cIgnoring path : " + f.getPath());
                }
            }
        } else {
            sendMessage("&cData folder is null or Data folder has no beatmaps.");
        }

        getServer().getPluginManager().registerEvents(new Events(), this);
        getCommand("mcosu.sendresourcepack").setExecutor(new me.superneon4ik.mcosu.commands.SendResourcePack());
        getCommand("mcosu.testspawnarmorstand").setExecutor(new me.superneon4ik.mcosu.commands.TestSpawnArmorstandCommand());
        getCommand("mcosu.listbeatmaps").setExecutor(new me.superneon4ik.mcosu.commands.ListBeatmapsCommand());
        getCommand("mcosu.play").setExecutor(new me.superneon4ik.mcosu.commands.RunOsuCommand());

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);

        sendMessage("&aPlugin enabled.");

        Game = new OsuGame();
    }

    @Override public void onDisable() {
        for (var as: armorStands) {
            try {
                as.remove();
            } catch (Exception ignore) { }
        }

        sendMessage("&cPlugin disabled.");
    }

    public void sendMessage(String message) {
        getServer().getConsoleSender().sendMessage("[" + getName() + "] " + ChatColor.translateAlternateColorCodes('&', message));
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static Path getDataFolderPaths() { return DataFolder; }

    public static void addArmorStand(ArmorStand as) {
        armorStands.add(as);
    }
}
