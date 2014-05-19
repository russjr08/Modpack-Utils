package com.kronosad.projects.modpack.common.objects.util;

import java.io.File;

/**
 * Created by russjr08.
 */
public abstract class OperatingSystem {

    public abstract File getMinecraftFolder();

    public File getModpacksFolder() {
        return new File(getMinecraftFolder().getAbsoluteFile(), "modpacks");
    }

    public static OperatingSystem getCurrentOperatingSystem() throws Exception {
        String OS = System.getProperty("os.name");
        if (OS.toLowerCase().contains("windows")) {
            return new Windows();
        } else if (OS.toLowerCase().contains("os x")) {
            return new MacOSX();
        } else if (OS.toLowerCase().contains("linux")) {
            return new Linux();
        }

        throw new Exception("Your Operating System is not supported!");
    }

    @Override
    public String toString() {
        try {
            if (getCurrentOperatingSystem() instanceof Windows) {
                return "Windows";
            } else if (getCurrentOperatingSystem() instanceof MacOSX) {
                return "Mac OS X";
            } else if (getCurrentOperatingSystem() instanceof Linux) {
                return "Linux";
            } else {
                return "Unknown";
            }
        } catch (Exception e) {
        }
        return "Unknown";
    }

}

class Windows extends OperatingSystem {

    @Override
    public File getMinecraftFolder() {
        return new File(System.getProperty("user.home"), "AppData" + File.separator + "Roaming" + File.separator + ".minecraft");
    }

}

class MacOSX extends OperatingSystem {

    @Override
    public File getMinecraftFolder() {
        return new File(System.getProperty("user.home") + File.separator + "Library/Application Support/", "minecraft");
    }

}

class Linux extends OperatingSystem {

    @Override
    public File getMinecraftFolder() {
        return new File(System.getenv("user.home"), ".minecraft");
    }

}