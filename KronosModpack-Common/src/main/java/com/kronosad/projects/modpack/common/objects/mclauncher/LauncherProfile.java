package com.kronosad.projects.modpack.common.objects.mclauncher;

import java.util.HashMap;

/**
 * Author russjr08
 * Created at 5/15/14
 */
public class LauncherProfile {

    HashMap<String, Profile> profiles;
    String selectedProfile, clientToken;

    public HashMap<String, Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(HashMap<String, Profile> profiles) {
        this.profiles = profiles;
    }

    public String getSelectedProfile() {
        return selectedProfile;
    }

    public void setSelectedProfile(String selectedProfile) {
        this.selectedProfile = selectedProfile;
    }


    @Override
    public String toString() {
        return "LauncherProfile{" +
                "profiles=" + profiles +
                ", selectedProfile='" + selectedProfile + '\'' +
                ", clientToken='" + clientToken + '\'' +
                '}';
    }
}
