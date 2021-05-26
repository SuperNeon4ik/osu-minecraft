package me.superneon4ik.mcosu.osu;

import me.superneon4ik.mcosu.*;
import me.superneon4ik.mcosu.osu.beatmapdata.BreakPeriod;
import me.superneon4ik.mcosu.osu.beatmapdata.HitObject;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class OsuGame {
    public static final Material BgMaterial = Material.BLACK_CONCRETE;
    public static final Material GreatMaterial = Material.LIGHT_BLUE_CONCRETE;
    public static final Material GoodMaterial = Material.LIME_CONCRETE;
    public static final Material MehMaterial = Material.YELLOW_CONCRETE;
    public static final Material MissMaterial = Material.RED_CONCRETE;

    public static final int TicksPerHitObjectStage = 3;
    public static final Material Stage1Material = Material.GUNPOWDER;
    public static final Material Stage2Material = Material.FLINT;
    public static final Material Stage3Material = Material.NETHERITE_SCRAP;

    public static final Vector3 BlockBottomLeftPosition = new Vector3(-67, 74, -295); // goes Y- Z-

    public static final String CompletenessText = "----Completeness----";

    public long Score = 0;
    public int Health = 20; // 0 - 20
    public int Combo = 0;

    public long MaxScore = 0;

    public Beatmap CurrentBeatmap;
    public OsuUI UserInterface;
    public OsuCursor GameCursor;
    public OsuStatus GameStatus = OsuStatus.Idle;

    public ArrayList<HitObject> TpsTimedHitObjects;
    public ArrayList<BreakPeriod> TpsTimedBreakPeriods;

    public OsuGame() {
        UserInterface = new OsuUI();
        GameCursor = new OsuCursor(Bukkit.getWorld("world"), new Vector2F(4, 6), 1);
        GameCursor.SetHidden(true);
    }

    public void PlayGame(Beatmap beatmap) {
        if (GameStatus == OsuStatus.Idle || GameStatus == OsuStatus.Results) {
            CurrentBeatmap = beatmap;

            Score = 0;
            Health = 20;
            Combo = 0;
            MaxScore = 0;

            TpsTimedHitObjects = new ArrayList<>();
            TpsTimedBreakPeriods = new ArrayList<>();
            for (var ho : CurrentBeatmap.HitObjects) {
                TpsTimedHitObjects.add(new HitObject(ho.getPosition().getX(), ho.getPosition().getY(), Math.round(ho.getTime() / 50.0f)));
            }
            for (var obj : CurrentBeatmap.BreakPeriods) {
                TpsTimedBreakPeriods.add(new BreakPeriod(Math.round(obj.getStartTime() / 50.0f), Math.round(obj.getEndTime() / 50.0f)));
            }

            for (var p : Bukkit.getOnlinePlayers()) {
                p.stopSound("osu.sfx.applause", SoundCategory.VOICE);
            }

            InfoState();
        }
    }

    private void InfoState() {
        GameStatus = OsuStatus.BeatmapInfo;

        UserInterface.removeUIStands();
        UserInterface.initMapInfoUI(CurrentBeatmap.Title, CurrentBeatmap.Artist, CurrentBeatmap.Creator,
                CurrentBeatmap.Version, CurrentBeatmap.Source, CurrentBeatmap.Tags);

        for (var p: Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), "osu.sfx.game.start", 1, 1);
            p.playSound(p.getLocation(), CurrentBeatmap.AudioFilename, SoundCategory.VOICE, 0.01f, 1);
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
            for (var p: Bukkit.getOnlinePlayers()) {
                p.stopSound(CurrentBeatmap.AudioFilename, SoundCategory.VOICE);
            }
        }, 10);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), this::GameState, 20 * 7 + 10);
    }

    private void GameState() {
        GameStatus = OsuStatus.Game;

        GameCursor.SetHidden(false);
        GameCursor.SetLocation(new Vector2F(4 - 0.5f, 3 - 0.5f));

        UserInterface.removeUIStands();
        UserInterface.initGameUI();

        for (var p: Bukkit.getOnlinePlayers())
            p.stopSound(CurrentBeatmap.AudioFilename, SoundCategory.VOICE);

        new BukkitRunnable() {
            @Override public void run() {
                if (GameStatus == OsuStatus.Game) UpdateUI();
                else cancel();
            }
        }.runTaskTimer(Main.getPlugin(), 0, 2);

        new BukkitRunnable() {
            int ticksPast = 0;
            final int lastTick = TpsTimedHitObjects.get(TpsTimedHitObjects.size() - 1).getTime();

            @Override public void run() {
                int ticksBeforeHit = TicksPerHitObjectStage * 3;
                for (var ho: TpsTimedHitObjects) {
                    if (ho.getTime() - ticksBeforeHit == ticksPast) {
                        Vector2 highScalePos = new Vector2(ho.getPosition().getX() / 4 * 5, Math.round(ho.getPosition().getY() / 3.0f * 3.75f));
                        Vector2 blockScalePos = new Vector2(Math.round(highScalePos.getX() / 100.0f), Math.round(highScalePos.getY() / 100.0f));

                        if (!(blockScalePos.getX() > 8 || blockScalePos.getY() > 6)) {
                            Vector3 hitObjectPosition = new Vector3(BlockBottomLeftPosition.getX(),
                                    BlockBottomLeftPosition.getY() - blockScalePos.getY(), BlockBottomLeftPosition.getZ() - blockScalePos.getX());

                            Location hitObjectLocation = new Location(Bukkit.getWorld("world"), hitObjectPosition.getX(),
                                    hitObjectPosition.getY(), hitObjectPosition.getZ());

                            //hitObjectLocation.getBlock().setType(Material.ITEM_FRAME);

                            if (ho.getTime() == TpsTimedHitObjects.get(TpsTimedHitObjects.size() - 1).getTime()) {
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
                                    for (var p : Bukkit.getOnlinePlayers()) {
                                        p.stopSound(CurrentBeatmap.AudioFilename, SoundCategory.VOICE);
                                        p.playSound(p.getLocation(), "osu.sfx.applause", SoundCategory.VOICE, 0.5f, 1);
                                    }

                                    UserInterface.removeUIStands();
                                    UserInterface.initMapEndInfoUI("&l" + CurrentBeatmap.Title + " &7[" + CurrentBeatmap.Version + "]",
                                            "&lArtist: &7" + CurrentBeatmap.Artist + " &f&lCreator: &7" + CurrentBeatmap.Creator,
                                            Score, ((int)((float)Score / (float)MaxScore * 10000) / 100.0f), Combo);

                                    GameStatus = OsuStatus.Results;

                                    GameCursor.SetLocation(GameCursor.DefaultPosition);
                                    GameCursor.SetHidden(true);
                                }, TicksPerHitObjectStage * 3 + 60);
                            }

                            try {
                                ItemFrame itemFrame = (ItemFrame) hitObjectLocation.getWorld().spawnEntity(hitObjectLocation, EntityType.ITEM_FRAME);
                                itemFrame.setFacingDirection(BlockFace.EAST);
                                itemFrame.setItem(new ItemStack(Stage1Material), false /* Somewhy this boolean doesn't work */);
                                itemFrame.setVisible(false);

                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
                                    itemFrame.setItem(new ItemStack(Stage2Material));
                                }, TicksPerHitObjectStage);
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
                                    itemFrame.setItem(new ItemStack(Stage3Material));
                                }, TicksPerHitObjectStage * 2);
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
                                    for (var p : Bukkit.getOnlinePlayers())
                                        p.playSound(itemFrame.getLocation(), "osu.sfx.hit.default", 1, 1);

                                    GameCursor.SetLocation(new Vector2F(blockScalePos.x - 0.5f, blockScalePos.y - 0.5f));

                                    Combo++;
                                    Score += Combo * 300;
                                    MaxScore += Combo * 300;

                                    ArmorStand as = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&b&lGreat", hitObjectLocation.add(0.2f, 0.2f, 0.5f)));
                                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), as::remove, TicksPerHitObjectStage * 3);

                                    itemFrame.remove();
                                }, TicksPerHitObjectStage * 3);
                            } catch (IllegalArgumentException ignore) {
                            }
                        }

                        //break;
                    }
                }

                for (var bp: TpsTimedBreakPeriods) {
                    if (bp.getStartTime() == ticksPast) {
                        if (Health < 10) {
                            for (var p: Bukkit.getOnlinePlayers()) {
                                p.sendTitle("", "" + ChatColor.RED + ChatColor.BOLD + "Section FAIL", 0, 40, 20);
                                p.playSound(p.getLocation(), "osu.sfx.section.fail", 0.75f, 1);
                            }
                        } else {
                            for (var p: Bukkit.getOnlinePlayers()) {
                                p.sendTitle("", "" + ChatColor.GREEN + ChatColor.BOLD + "Section PASS", 0, 40, 20);
                                p.playSound(p.getLocation(), "osu.sfx.section.pass", 0.75f, 1);
                            }
                        }
                    }
                    else if (bp.getEndTime() == ticksPast) {
                        for (var p: Bukkit.getOnlinePlayers()) {
                            p.sendTitle("", "", 0, 0, 0);
                        }
                    }
                }

                int percentageDone = Math.round((float)ticksPast / (float)lastTick * 100.0f);
                int textAmount = Math.round((float)percentageDone * (CompletenessText.length() / 100.0f));

                StringBuilder completenessText = new StringBuilder("&a&l");
                completenessText.append(CompletenessText, 0, textAmount).append("&c&l")
                        .append(CompletenessText.substring(textAmount));

                if (GameStatus == OsuStatus.Game)
                    UserInterface.CompletenessStand.setCustomName(ChatColor.translateAlternateColorCodes('&', completenessText.toString()));

                ticksPast++;
            }
        }.runTaskTimer(Main.getPlugin(), 0, 1);

        for (var p: Bukkit.getOnlinePlayers())
            p.playSound(p.getLocation(), CurrentBeatmap.AudioFilename, SoundCategory.VOICE, 0.75f, 1);
    }

    public void UpdateUI() {
        if (GameStatus == OsuStatus.Game) {
            StringBuilder HealthText = new StringBuilder("&a");
            HealthText.append("=".repeat(Health)).append("&c").append("=".repeat(20 - Health));

            float tps = Math.round(Lag.getTPS() * 10) / 10.0f;
            ChatColor tpsColor = ChatColor.GREEN;
            if (tps < 15)
                tpsColor = ChatColor.YELLOW;
            else if (tps < 10)
                tpsColor = ChatColor.RED;

            UserInterface.HealthStand.setCustomName(ChatColor.translateAlternateColorCodes('&', HealthText.toString()));
            UserInterface.ScoreStand.setCustomName("" + ChatColor.BOLD + Score);
            UserInterface.AccuracyStand.setCustomName("" + ChatColor.BOLD + (MaxScore == 0 ? "100.00" : ((int)((float)Score / (float)MaxScore * 10000) / 100.0f)) + "%");
            UserInterface.TpsStand.setCustomName("" + tpsColor + ChatColor.BOLD + tps + " TPS");
            UserInterface.ComboStand.setCustomName(ChatColor.BOLD + "x" + Combo);
        }
    }
}

