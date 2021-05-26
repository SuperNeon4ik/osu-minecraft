package me.superneon4ik.mcosu.osu.beatmapdata;

import me.superneon4ik.mcosu.Vector2;

public class HitObject {
    private final Vector2 position;
    private final int time;

    public HitObject(int x, int y, int time) {
        this.position = new Vector2(x, y);
        this.time = time;
    }

    public Vector2 getPosition() { return position; }
    public int getTime() { return time; }

    @Override public String toString() {
        return "HitObject{" +
                "position=" + position +
                ", time=" + time +
                '}';
    }
}
