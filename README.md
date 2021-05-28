# osu!minecraft
osu!, but in Minecraft

---
## YouTube Video Showcase
[![Video Showcase on YouTube](https://img.youtube.com/vi/UqI-zQyVGuQ/0.jpg)](https://www.youtube.com/watch?v=UqI-zQyVGuQ)

---
## How to use?
+ Download `MinecraftServer` from [Latest Release](https://github.com/SuperNeon4ik/osu-minecraft/releases/latest)
+ Extract all files from archive into seperate folder
+ Run the server by running `_start NoGUI paper.bat` file
+ Join the server using IP `localhost` via **Minecraft 1.16.3**
+ Use `/mcosu.listbeatmaps` to get a list of avaliable beatmaps
+ Use `/mcosu.play <beatmapIndex>` to start a beatmap. *`beatmapIndex` - a number in `/mcosu.listbeatmaps`*
+ **Do NOT** reload or start another game while beatmap is in progress, stuff may break
+ This plugin may conflict with others
+ **For PERSONAL USE ONLY**

---
### Beatmap List:
+ [Kurokotei - Galaxy Collapse](https://osu.ppy.sh/beatmapsets/396221#osu/862088)
+ [Knife Party - Centipede](https://osu.ppy.sh/beatmapsets/150945#osu/372245)
+ [S3RL - Pika Girl](https://osu.ppy.sh/beatmapsets/40440#osu/128321)
+ [Eminem feat. Ed Sheeran - River](https://osu.ppy.sh/beatmapsets/770477#osu/1619884)
+ [Laur - Sound Chimera](https://osu.ppy.sh/beatmapsets/813569#osu/1821081)
+ [Yooh - MariannE](https://osu.ppy.sh/beatmapsets/1391659#osu/2873429)

#### Note, that you can easily add new beatmaps by following these steps:
+ Download beatmap set.
+ Get `.osu` file of difficulty you want.
+ Copy it to the `plugins/McOsu`
+ Open [OGG Converter](https://audio.online-convert.com/convert-to-ogg) and convert music file.
+ Download [latest osu!minecraft Resource Pack Release](https://github.com/SuperNeon4ik/osu-minecraft-resources/releases/latest)
+ Put converted music file into `assets/minecraft/sounds/custom/music` and rename it *(**NOTE!** You can't have capital letters in the file name. I'm sure you can use `-_`)
+ Open file `assets/minecraft/sounds.json` and add these lines:
  ```json
  "osu.music.{FILE_NAME}": {
		"sounds": ["custom/music/{FILE_NAME}"]
  },
  ```
+ Open beatmap *(`.osu`)* and edit `AudioFilename` property to `osu.music.{FILE_NAME}`
+ Make sure that beatmap has **Source** and **Tags** in **Metadata**
+ Also if after `[TimingPoints]` goes `[HitObjects]`, put `[Colours]` in between. Template:
  ```
  [Colours]
  Combo1 : 255,255,255
  Combo2 : 252,221,3
  Combo3 : 239,24,30
  Combo4 : 116,71,71
  ```
+ And now you can play your beatmap.
  
### Links:
+ [osu!minecraft](https://github.com/SuperNeon4ik/osu-minecraft)
+ [osu!minecraft resources](https://github.com/SuperNeon4ik/osu-minecraft-resources)
+ [osu!](https://osu.ppy.sh/)
+ [SuperNeon4ik - GitHub](https://github.com/SuperNeon4ik)
+ [SuperNeon4ik - YouTube Channel](https://www.youtube.com/channel/UCesTpB2QEv95GvMlA8cl41A)
+ [**SuperNeon4ik - Plugin Showcase**](https://youtu.be/UqI-zQyVGuQ)

---
[osu!](https://osu.ppy.sh/home) in made by [ppy](https://github.com/ppy)

I am **NOT** affilated with [ppy](https://github.com/ppy)
