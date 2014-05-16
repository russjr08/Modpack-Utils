package com.kronosad.projects.modpack.common.objects.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kronosad.projects.modpack.common.objects.mclauncher.LauncherProfile;
import com.kronosad.projects.modpack.common.objects.mclauncher.Profile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Author russjr08
 * Created at 5/15/14
 */
public class LauncherUtils {

    public static void addProfileToLauncher(Profile profile) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        File launcherProfilesFile = new File(OperatingSystem.getCurrentOperatingSystem().getMinecraftFolder(), "launcher_profiles.json");
        LauncherProfile launcherProfile = gson.fromJson(FileUtils.readFileToString(launcherProfilesFile), LauncherProfile.class);
        profile.setJavaArgs("-Xmx2G -XX:MaxPermSize=128m");
        launcherProfile.getProfiles().put(profile.getName(), profile);

        launcherProfile.setSelectedProfile(profile.getName());

        FileUtils.writeStringToFile(launcherProfilesFile, gson.toJson(launcherProfile));

    }

    public static void removeProfileFromLauncher(String name) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            File launcherProfilesFile = new File(OperatingSystem.getCurrentOperatingSystem().getMinecraftFolder(), "launcher_profiles.json");

            LauncherProfile launcherProfile = gson.fromJson(FileUtils.readFileToString(launcherProfilesFile), LauncherProfile.class);

            if (launcherProfile.getProfiles().containsKey(name)) {
                launcherProfile.getProfiles().remove(name);
            }

            FileUtils.writeStringToFile(launcherProfilesFile, gson.toJson(launcherProfile));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
