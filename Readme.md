Modpack Utilities (Also known KronosModpack)
=============================================

Modpack Utilities is a nifty way of distributing Modpacks with your friends
-----------------------------------------------------------------------------

![Screenshot of KronosModpack](http://git.kronosad.com/russjr08/kronosmodpack/raw/master/res/images/ScreenShot.png "A Screenshot of KronosModpack")

For Users
----------
1. Obtain a Modpack JSON file from your Modpack developer/maintainer and input it into the app.
2. Click 'Update'.
3. ???
4. *Profit*


* *If you don't have the required version of Minecraft Forge, Modpack Utilities will prompt you to download the right version.*


For Modpack Developers
----------------------
Creating JSON Files for Modpack Utilities is very simple! An example Modpack JSON file can be found [here](http://api.kronosad.com/Modpacks/TMPI.json).

```json
{
  "version": 1.0,
  "md5": "IM AN MD5",
  "name": "Testing pack pls ignore",
  "forgeVersion": "1.7.2-Forge10.12.1.1082",
  "files": [
    {
      "version": 1.0,
      "author": "Roosall Rikardsun",
      "md5": "37470408cb00cb58915615676e6c2207",
      "name": "Test mod pls ignore",
      "path": "mods/ThermalExpansion.jar",
      "url": "http://minecraft.curseforge.com/mc-mods/69163-thermalexpansion/files/789826/download"
    },
    {
      "version": 1.0,
      "author": "Roosall Rikardsun",
      "md5": "37470408cb00cb58915615676e6c2207",
      "name": "Test mod pls ignore1",
      "path": "mods/ThermalExpansion1.jar",
      "url": "http://minecraft.curseforge.com/mc-mods/69163-thermalexpansion/files/789826/download"
    }
  ],
  "type": "INDIVIDUAL_FILES"
}
```

#### JSON Keys
*version*: Pretty self explanatory here, the version of the Modpack. (Optional/Not used yet)

*md5*: MD5 of the Modpack folder itself (Optinoal/Not used yet)

*name*: Name of the Modpack. (Appears in Modpack Utilities' Menu, also the name of the folder/launcher profile it creates

*forgeVersion*: The version of Forge (Can any "version" installed in the Minecraft launcher) that Modpack Utilities will try to set the respective Launcher Profile to use.

*files*: An array of 'files', each 'file' has a version (Optional / Not used yet), an Author (Optional/Not used yet), an MD5 Checksum of the file (This **_Important_**, as Modpack Utilities verifies the checksum of the file, and will attempt to redownload it if it doesn't match.), a name which appears in the Downloading GUI, a path, which is where the file will be installed (And the file name of the resulting file), and finally a URL to download the file from.

*type*: This tells Modpack Utilities whether it's downloading each file individually, or a zip file to extract in the root of the Modpack folder. (This is not implemented yet) Valid values are "INDIVIDUAL_FILES" and "ZIP"

If you're feeling lucky, (or lazy ;D ) [you can try out the Modpack Creator utility (VERY BETA)](http://www.google.com) (Link needs to be changed)

[Here are some instructions if you're up to the challenge!](doc/ModpackCreator.md)
