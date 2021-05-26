package me.superneon4ik.mcosu.osu;

import me.superneon4ik.mcosu.Main;
import me.superneon4ik.mcosu.osu.beatmapdata.BreakPeriod;
import me.superneon4ik.mcosu.osu.beatmapdata.HitObject;
import me.superneon4ik.mcosu.osu.beatmapdata.TimingPoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BeatmapParser {
    public static Beatmap FromString(String data) throws Exception {
        String[] strings = data.split("\n");

        String AudioFilename = null;
        int Mode = -1;
        boolean LetterboxInBreaks = true;

        String Title = null;
        String Artist = null;
        String Creator = null;
        String Version = null;
        String Source = null;
        String Tags = null;

        int HPDrainRate = -1;
        int OverallDifficulty = -1;
        float SliderMultiplier = -1;

        ArrayList<BreakPeriod> BreakPeriods = new ArrayList<>();
        ArrayList<TimingPoint> TimingPoints = new ArrayList<>();
        ArrayList<HitObject> HitObjects = new ArrayList<>();

        int fileZone = 0;
        for (String s: strings) {
            if (s.startsWith("//") || s.startsWith(" ") || s.startsWith("\n") || s.equalsIgnoreCase("")) continue;
            if (s.startsWith("[")) {
                fileZone++;
                continue;
            }

            if (fileZone > 0 && fileZone <= 4 && fileZone != 2) { // General, Metadata, Difficulty
                String[] dataLine = s.split(":" + (fileZone == 1 ? " " : ""));

                if (dataLine[0].equalsIgnoreCase("AudioFilename"))
                    AudioFilename = dataLine[1];
                else if (dataLine[0].equalsIgnoreCase("Mode"))
                    Mode = Integer.parseInt(dataLine[1]);
                else if (dataLine[0].equalsIgnoreCase("LetterboxInBreaks"))
                    LetterboxInBreaks = (Integer.parseInt(dataLine[1]) == 1);

                else if (dataLine[0].equalsIgnoreCase("Title"))
                    Title = dataLine[1];
                else if (dataLine[0].equalsIgnoreCase("Artist"))
                    Artist = dataLine[1];
                else if (dataLine[0].equalsIgnoreCase("Creator"))
                    Creator = dataLine[1];
                else if (dataLine[0].equalsIgnoreCase("Version"))
                    Version = dataLine[1];
                else if (dataLine[0].equalsIgnoreCase("Source"))
                    Source = dataLine[1];
                else if (dataLine[0].equalsIgnoreCase("Tags"))
                    Tags = dataLine[1];

                else if (dataLine[0].equalsIgnoreCase("HPDrainRate"))
                    HPDrainRate = Integer.parseInt(dataLine[1]);
                else if (dataLine[0].equalsIgnoreCase("OverallDifficulty"))
                    OverallDifficulty = Integer.parseInt(dataLine[1]);
                else if (dataLine[0].equalsIgnoreCase("SliderMultiplier"))
                    SliderMultiplier = Float.parseFloat(dataLine[1]);
            }
            else if (fileZone == 5) { // Events
                String[] dataLine = s.split(",");

                if (dataLine[0].equalsIgnoreCase("2")) {
                    BreakPeriods.add(new BreakPeriod(Integer.parseInt(dataLine[1]), Integer.parseInt(dataLine[2])));
                }
            }
            else if (fileZone == 6) { // Timing Points
                String[] dataLine = s.split(",");

                TimingPoints.add(new TimingPoint(Integer.parseInt(dataLine[0]),
                        Float.parseFloat(dataLine[1]), Integer.parseInt(dataLine[2])));
            }
            else if (fileZone == 8) { // Hit Objects
                String[] dataLine = s.split(",");

                HitObjects.add(new HitObject(Integer.parseInt(dataLine[0]),
                        Integer.parseInt(dataLine[1]), Integer.parseInt(dataLine[2])));
            }
        }

        if (AudioFilename == null || Mode == -1 || Title == null || Artist == null || Creator == null ||
            Version == null || Source == null || Tags == null || HPDrainRate == -1 || OverallDifficulty == -1 ||
            SliderMultiplier == -1 || TimingPoints.isEmpty() || HitObjects.isEmpty())
        {
            String statuses = "";
            if (AudioFilename == null) statuses += "\nAudioFilename == null";
            if (Mode == -1) statuses += "\nMode == -1";
            if (Title == null) statuses += "\nTitle == null";
            if (Artist == null) statuses += "\nArtist == null";
            if (Creator == null) statuses += "\nCreator == null";
            if (Version == null) statuses += "\nVersion == null";
            if (Source == null) statuses += "\nSource == null";
            if (Tags == null) statuses += "\nTags == null";
            if (HPDrainRate == -1) statuses += "\nHPDrainRate == -1";
            if (OverallDifficulty == -1) statuses += "\nOverallDifficulty == -1";
            if (SliderMultiplier == -1) statuses += "\nSliderMultiplier == -1";
            if (TimingPoints.isEmpty()) statuses += "\nTimingPoints.isEmpty()";
            if (HitObjects.isEmpty()) statuses += "\nHitObjects.isEmpty()";

            throw new Exception("Can't create Beatmap. Not all data is available." + statuses);
        }

        Beatmap output = new Beatmap(AudioFilename, Mode, LetterboxInBreaks,
                Title, Artist, Creator, Version, Source, Tags,
                HPDrainRate, OverallDifficulty, SliderMultiplier,
                BreakPeriods, TimingPoints, HitObjects);
        return output;
    }

    public static Beatmap FromFile(String pathToFile) throws Exception {
        StringBuilder beatmapData = new StringBuilder();

        ClassLoader cl = Main.class.getClassLoader();
        File file = new File(cl.getResource(pathToFile).getFile());
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            beatmapData.append(data).append('\n');
        }
        reader.close();

        return FromString(beatmapData.toString());
    }

    public static Beatmap FromFile(File file) throws Exception {
        StringBuilder beatmapData = new StringBuilder();
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            beatmapData.append(data).append('\n');
        }
        reader.close();

        return FromString(beatmapData.toString());
    }
}
