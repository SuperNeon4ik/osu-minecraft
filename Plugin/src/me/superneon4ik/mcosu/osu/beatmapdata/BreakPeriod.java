package me.superneon4ik.mcosu.osu.beatmapdata;

public class BreakPeriod {
    private int StartTime;
    private int EndTime;

    public BreakPeriod(int startTime, int endTime) {
        StartTime = startTime;
        EndTime = endTime;
    }

    public int getStartTime() { return StartTime; }
    public int getEndTime() { return EndTime; }

    @Override
    public String toString() {
        return "BreakPeriod{" +
                "StartTime=" + StartTime +
                ", EndTime=" + EndTime +
                '}';
    }
}
