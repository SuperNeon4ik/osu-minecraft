package me.superneon4ik.mcosu.osu;

import me.superneon4ik.mcosu.Essentials;
import me.superneon4ik.mcosu.Vector3F;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;

public class OsuUI {
    private static final String gameWorld = "world";

    // Welcome
    private static final Vector3F Welcome = new Vector3F(-66.5f, 72.5f, -298);
    private static final Vector3F RunCommand = new Vector3F(-66.5f, 72f, -298);

    private ArmorStand WelcomeStand = null;
    private ArmorStand RunCommandStand = null;

    // Game UI
    private static final Vector3F Combo = new Vector3F(-66.5f, 70, -294.5f);
    private static final Vector3F Tps = new Vector3F(-66.5f, 70, -301.5f);
    private static final Vector3F Score = new Vector3F(-66.5f, 74, -301.5f);
    private static final Vector3F Accuracy = new Vector3F(-66.5f, 73.5f, -301.5f);
    private static final Vector3F Mods = new Vector3F(-66.5f, 73f, -301.5f);
    private static final Vector3F Health = new Vector3F(-66.5f, 74, -295.5f);
    private static final Vector3F Completeness = new Vector3F(-66.5f, 69.5f, -298);

    public ArmorStand ComboStand = null;
    public ArmorStand TpsStand = null;
    public ArmorStand ScoreStand = null;
    public ArmorStand AccuracyStand = null;
    public ArmorStand ModsStand = null;
    public ArmorStand HealthStand = null;
    public ArmorStand CompletenessStand = null;

    // Map Info
    private static final Vector3F Title = new Vector3F(-66.5f, 73f, -298);
    private static final Vector3F Artist = new Vector3F(-66.5f, 72.5f, -298);
    private static final Vector3F Creator = new Vector3F(-66.5f, 72f, -298);
    private static final Vector3F Version = new Vector3F(-66.5f, 71.5f, -298);
    private static final Vector3F Source = new Vector3F(-66.5f, 71f, -298);
    private static final Vector3F Tags = new Vector3F(-66.5f, 70.5f, -298);

    private ArmorStand TitleStand = null;
    private ArmorStand ArtistStand = null;
    private ArmorStand CreatorStand = null;
    private ArmorStand VersionStand = null;
    private ArmorStand SourceStand = null;
    private ArmorStand TagsStand = null;

    // After End Info
    private static final Vector3F EndTitle = new Vector3F(-66.5f, 73.5f, -298);
    private static final Vector3F EndCreatorInfo = new Vector3F(-66.5f, 73f, -298);
    private static final Vector3F EndScore = new Vector3F(-66.5f, 72f, -298);
    private static final Vector3F EndAccuracy = new Vector3F(-66.5f, 71.5f, -298);
    private static final Vector3F EndCombo = new Vector3F(-66.5f, 71f, -298);

    private ArmorStand EndTitleStand;
    private ArmorStand EndCreatorInfoStand;
    private ArmorStand EndScoreStand;
    private ArmorStand EndAccuracyStand;
    private ArmorStand EndComboStand;

    public OsuUI() {
        initWelcomeUI();
    }

    public void initWelcomeUI() {
        World world = Bukkit.getWorld(gameWorld);

        WelcomeStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&e&lWelcome to &a&losu!minecraft", Welcome.toLocation(world)));
        RunCommandStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&7&l/mcosu.play <mapIndex>", RunCommand.toLocation(world)));
    }

    public void initGameUI() {
        World world = Bukkit.getWorld(gameWorld);

        ComboStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&c&lCombo", Combo.toLocation(world)));
        TpsStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&c&lTps", Tps.toLocation(world)));
        ScoreStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&c&lScore", Score.toLocation(world)));
        AccuracyStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&c&lAccuracy", Accuracy.toLocation(world)));
        ModsStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&l+AT", Mods.toLocation(world)));
        HealthStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&c&lHealth", Health.toLocation(world)));
        CompletenessStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&c&l----Completeness----", Completeness.toLocation(world)));
    }

    public void initMapInfoUI(String title, String artist, String creator, String version, String source, String tags) {
        World world = Bukkit.getWorld(gameWorld);

        TitleStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&lTitle: &7" + title, Title.toLocation(world)));
        ArtistStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&lArtist: &7" + artist, Artist.toLocation(world)));
        CreatorStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&lCreator: &7" + creator, Creator.toLocation(world)));
        VersionStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&lVersion: &7" + version, Version.toLocation(world)));
        SourceStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&lSource: &7" + source, Source.toLocation(world)));
        TagsStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&lTags: &7" + tags, Tags.toLocation(world)));
    }

    public void initMapEndInfoUI(String title, String info, long score, float accuracy, int combo) {
        World world = Bukkit.getWorld(gameWorld);

        EndTitleStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand(title, EndTitle.toLocation(world)));
        EndCreatorInfoStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand(info, EndCreatorInfo.toLocation(world)));
        EndScoreStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&lScore: &7" + score, EndScore.toLocation(world)));
        EndAccuracyStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&lAccuracy: &7" + accuracy + "%", EndAccuracy.toLocation(world)));
        EndComboStand = (ArmorStand) Bukkit.getEntity(Essentials.SpawnArmorStand("&lCombo: &7" + combo, EndCombo.toLocation(world)));
    }

    public void removeUIStands() {
        if (WelcomeStand != null) { WelcomeStand.remove(); WelcomeStand = null; }
        if (RunCommandStand != null) { RunCommandStand.remove(); RunCommandStand = null; }

        if (ComboStand != null) { ComboStand.remove(); ComboStand = null; }
        if (TpsStand != null) { TpsStand.remove(); TpsStand = null; }
        if (ScoreStand != null) { ScoreStand.remove(); ScoreStand = null; }
        if (AccuracyStand != null) { AccuracyStand.remove(); AccuracyStand = null; }
        if (ModsStand != null) { ModsStand.remove(); ModsStand = null; }
        if (HealthStand != null) { HealthStand.remove(); HealthStand = null; }
        if (CompletenessStand != null) { CompletenessStand.remove(); CompletenessStand = null; }

        if (TitleStand != null) { TitleStand.remove(); TitleStand = null; }
        if (ArtistStand != null) { ArtistStand.remove(); ArtistStand = null; }
        if (CreatorStand != null) { CreatorStand.remove(); CreatorStand = null; }
        if (VersionStand != null) { VersionStand.remove(); VersionStand = null; }
        if (SourceStand != null) { SourceStand.remove(); SourceStand = null; }
        if (TagsStand != null) { TagsStand.remove(); TagsStand = null; }

        if (EndTitleStand != null) { EndTitleStand.remove(); EndTitleStand = null; }
        if (EndCreatorInfoStand != null) { EndCreatorInfoStand.remove(); EndCreatorInfoStand = null; }
        if (EndScoreStand != null) { EndScoreStand.remove(); EndScoreStand = null; }
        if (EndAccuracyStand != null) { EndAccuracyStand.remove(); EndAccuracyStand = null; }
        if (EndComboStand != null) { EndComboStand.remove(); EndComboStand = null; }
    }
}
