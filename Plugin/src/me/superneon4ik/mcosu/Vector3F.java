package me.superneon4ik.mcosu;

import org.bukkit.Location;
import org.bukkit.World;

public class Vector3F {
    public float x;
    public float y;
    public float z;

    public Vector3F(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getZ() { return z; }

    public Location toLocation(World world) {
        return new Location(world, x, y, z);
    }

    @Override
    public String toString() {
        return "Vector3F(" + x + "; " + y + "; " + z + ")";
    }
}
