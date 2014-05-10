package com.kronosad.projects.modpack.common.objects.com.kronosad.projects.modpack.common.util;

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

}

class Windows extends OperatingSystem {

    @Override
    public File getMinecraftFolder() {
        return new File(System.getProperty("user.home"), ".minecraft");
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