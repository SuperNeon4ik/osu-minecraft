package me.superneon4ik.mcosu.osu;

import me.superneon4ik.mcosu.Main;
import me.superneon4ik.mcosu.Vector2;
import me.superneon4ik.mcosu.Vector2F;
import me.superneon4ik.mcosu.Vector3F;
import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;

public class OsuCursor {
    private static final float horizontalGo = 8;
    private static final float verticalGo = 6;
    private static final float wallOffset = 0.2f;

    public static final Particle cursorParticle = Particle.REDSTONE;

    private final World GameWorld;
    public final Vector2F DefaultPosition;

    private Vector2F CurrentLocation;
    private boolean isHidden = false;

    public OsuCursor(World gameWorld, Vector2F defaultPosition, int frameRate) {
        GameWorld = gameWorld;
        DefaultPosition = defaultPosition;

        DefaultPosition.x -= 1;
        DefaultPosition.y -= 1;

        CurrentLocation = DefaultPosition;

        new BukkitRunnable() {
            @Override public void run() {
                Update();
            }
        }.runTaskTimer(Main.getPlugin(), 0, frameRate);
    }

    private void Update() {
        if (!isHidden) {
            DrawParticle();
        }
    }

    public void SetLocation(Vector2F value) {
        CurrentLocation = value;
        DrawParticle();
    }

    public void SetHidden(boolean value) {
        isHidden = value;
    }

    private void DrawParticle() {
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(252, 252, 3), 1);
        var cornerPos = OsuGame.BlockBottomLeftPosition;
        Location pointerLoc = new Location(GameWorld, cornerPos.getX() + wallOffset, cornerPos.getY() - CurrentLocation.getY(), cornerPos.getZ() - CurrentLocation.getX());
        for (var p : Bukkit.getOnlinePlayers()) p.spawnParticle(cursorParticle, pointerLoc, 1, dustOptions);
    }
}
