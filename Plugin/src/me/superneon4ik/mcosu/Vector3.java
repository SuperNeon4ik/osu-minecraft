package me.superneon4ik.mcosu;

public class Vector3 {
    public int x;
    public int y;
    public int z;

    public Vector3(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }

    @Override
    public String toString() {
        return "Vector3(" + x + "; " + y + "; " + z + ")";
    }
}
