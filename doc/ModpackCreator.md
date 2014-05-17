Modpack Creator (A helper app for Modpack Utilities)
=====================================================

Modpack Creator allows you to easily create Modpack JSON files for Modpack Utilities.

All you have to do is point it towards the folder of your Modpack. It will then generate
an entry for each file found in your Modpack folder in the generated JSON file.

You'll have to go back and edit in the Forge Version, and any optional keys you want to use (Such as the mod name).

Example usage

````
java -jar ModpackCreator.jar /home/russjr08/PathToModpackFolder/ http://baseURLOnWhereToDownloadModpackFilesFrom

````

*Warning:* A current bug in this app causes it to read the passed in arguments incorrectly
if your path has any spaces in it. If the path to modpack folder has any spaces in it, you'll
have to move it to a folder/path that doesn't have any spaces.

The resulting JSON file will be created in the root folder of the passed in Modpack folder.
You might want to open the JSON file just to make sure everything is correct :)
