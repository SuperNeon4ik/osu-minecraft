package me.superneon4ik.mcosu.osu;

import me.superneon4ik.mcosu.osu.beatmapdata.BreakPeriod;
import me.superneon4ik.mcosu.osu.beatmapdata.HitObject;
import me.superneon4ik.mcosu.osu.beatmapdata.TimingPoint;

import java.util.ArrayList;

public class Beatmap {
    // General
    public final String AudioFilename;
    public final int Mode;
    public final boolean LetterboxInBreaks;

    // Metadata
    public final String Title;
    public final String Artist;
    public final String Creator;
    public final String Version;
    public final String Source;
    public final String Tags;

    // Difficulty
    public final int HPDrainRate;
    public final int OverallDifficulty;
    public final float SliderMultiplier;

    // Events
    public final ArrayList<BreakPeriod> BreakPeriods;

    // Timing Points
    public final ArrayList<TimingPoint> TimingPoints;

    // Hit Objects
    public final ArrayList<HitObject> HitObjects;

    public Beatmap(String audioFilename, int mode, boolean letterboxInBreaks,
                   String title, String artist, String creator, String version, String source, String tags,
                   int hpDrainRate, int overallDifficulty, float sliderMultiplier,
                   ArrayList<BreakPeriod> breakPeriods, ArrayList<TimingPoint> timingPoints, ArrayList<HitObject> hitObjects) {
        AudioFilename = audioFilename;
        Mode = mode;
        LetterboxInBreaks = letterboxInBreaks;

        Title = title;
        Artist = artist;
        Creator = creator;
        Version = version;
        Source = source;
        Tags = tags;

        HPDrainRate = hpDrainRate;
        OverallDifficulty = overallDifficulty;
        SliderMultiplier = sliderMultiplier;

        BreakPeriods = breakPeriods;
        TimingPoints = timingPoints;
        HitObjects = hitObjects;
    }

    @Override
    public String toString() {
        return "Beatmap{\n" +
                "AudioFilename='" + AudioFilename + '\'' +
                ", \nMode=" + Mode +
                ", \nLetterboxInBreaks=" + LetterboxInBreaks +
                ", \nTitle='" + Title + '\'' +
                ", \nArtist='" + Artist + '\'' +
                ", \nCreator='" + Creator + '\'' +
                ", \nVersion='" + Version + '\'' +
                ", \nSource='" + Source + '\'' +
                ", \nTags='" + Tags + '\'' +
                ", \nHPDrainRate=" + HPDrainRate +
                ", \nOverallDifficulty=" + OverallDifficulty +
                ", \nSliderMultiplier=" + SliderMultiplier +
                ", \nBreakPeriods=" + BreakPeriods +
                ", \nTimingPoints=" + TimingPoints +
                ", \nHitObjects=" + HitObjects +
                "\n}";
    }
}

