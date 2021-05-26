package me.superneon4ik.mcosu;

public class Vector2F {
    public float x;
    public float y;

    public Vector2F(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() { return x; }
    public float getY() { return y; }

    @Override
    public String toString() {
        return "Vector2F(" + x + "; " + y + ")";
    }
}
