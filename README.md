# Minecraft-Infdev-20100360-with-20100415-terrain
This is the source code for a Minecraft Infdev 20100360 mod I made myself in a couple of hours (which I believe should already tell you about the level of stability we're dealing with). As the name suggests, it brings back the terrain generation from Minecraft Infdev 20100415 by replacing the vanilla terrain generator with the one from the desired version of the game. Unfortunately, you cannot load worlds from Minecraft Infdev 20100415 with this mod as a result of me not adding support for whatever format it uses for its worlds. But other than that, the mod is playable. Please keep in mind that I'm a beginner at computer programming, so don't set your expectations high.

If you so happen to be interested in building this mod, then here's what ya' gotta do:


1 - Download Retro MCP-Java and the JDK 8 from Azul Zulu, but make sure that the version of JDK 8 you're installing comes with Java FX: https://github.com/MCPHackers/RetroMCP-Java.

2 - Open Retro MCP-Java, and the version of Minecraft you're going to select is "Infdev 20100360-2", not Infdev 20100360-1.

3. There is going to be a popup saying: "Are you sure you want to run setup for selected version?". You're supposed to click on yes.

4. After the environment has finished setting up, click on the "Decompile" button at the upper left corner.

5. After the source code has finished decompiling, close Retro MCP-Java, head over to the newly created "minecraft" folder, double click on the "src" folder, double click on the "net" folder within it, double click on the "minecraft" folder within it, double click on the "src" folder within it, and that is where you will drag-&-drop the .java files found on this repository.

6. Open Retro MCP-Java one last time, click on the "Recompile" button, and after that is done, you will click on the "Build" button.



You've successfully built my mod! You should be able to see a newly created folder called "build" containing a zip archive called "minecraft". That's the mod, you just have to extract it with any program of your choice (I personally use 7-Zip). They contain a handful of class files you can drag-&-drop into a Minecraft jar. Now you may feel free to play it through the BetaCraft launcher (which can be found here" https://betacraft.uk/). Now I will teach you how to install this mod through BetaCraft:


1. Make sure to install Minecraft Infdev 20100360 before anything. The version you will choose to download is listed as "inf-20100360-1835 (1.139)".

2. Right after that is done, close BetaCraft, and head over to wherever your instance directory is located. If you're using Windows, then the default directory is typically found on "Roaming", which is located in "AppData". You can quickly head over there by clicking on the search icon typically present on your search bar (although, you can alternatively click on your home button and click on the search bar found over there), typing "run" (which will bring up a program simply called "Run"), typing %appdata%, and pressing enter on your keyboard.

3. The instance folder should be called ".betacraft". Head over there, double click on the "versions" folder, and look for a jar file named "inf-20100415".

4. Use either WinRAR or 7-Zip to open the jar file.

5. Delete the "META-INF" folder found within the jar file.

6. Drag-&-drop the class files you just created.

7. Open BetaCraft and boot up Minecraft Infdev 20100360, the one you just installed. Create a new world and see if you spot any differences in the terrain generation.


![Screenshot 2024-05-20 16-48-13](https://github.com/JohnCartoonist/Minecraft-Infdev-20100360-with-20100415-terrain/assets/86744606/ade3bd02-f7b0-4074-934e-7cf6eaad0489)
