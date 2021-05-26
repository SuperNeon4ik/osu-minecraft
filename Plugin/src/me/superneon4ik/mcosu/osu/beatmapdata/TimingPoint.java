package me.superneon4ik.mcosu.osu.beatmapdata;

public class TimingPoint {
    public int Time;
    public float BeatLength;
    public int Meter;

    public TimingPoint(int time, float beatLength, int meter) {
        Time = time;
        BeatLength = beatLength;
        Meter = meter;
    }

    @Override
    public String toString() {
        return "TimingPoint{" +
                "Time=" + Time +
                ", BeatLength=" + BeatLength +
                ", Meter=" + Meter +
                '}';
    }
}
