package com.kronosad.projects.modpack.common.objects.mclauncher;

/**
 * Author russjr08
 * Created at 5/15/14
 */
public class Profile {

    String name, gameDir, lastVersionId, playerUUID, launcherVisibilityOnGameClose, javaArgs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastVersionId() {
        return lastVersionId;
    }

    public void setLastVersionId(String lastVersionId) {
        this.lastVersionId = lastVersionId;
    }

    public String getGameDir() {
        return gameDir;
    }

    public void setGameDir(String gameDir) {
        this.gameDir = gameDir;
    }

    public String getLauncherVisibilityOnGameClose() {
        return launcherVisibilityOnGameClose;
    }

    public void setLauncherVisibilityOnGameClose(String launcherVisibilityOnGameClose) {
        this.launcherVisibilityOnGameClose = launcherVisibilityOnGameClose;
    }

    public String getJavaArgs() {
        return javaArgs;
    }

    public void setJavaArgs(String javaArgs) {
        this.javaArgs = javaArgs;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", gameDir='" + gameDir + '\'' +
                ", lastVersionId='" + lastVersionId + '\'' +
                ", playerUUID='" + playerUUID + '\'' +
                ", launcherVisibilityOnGameClose='" + launcherVisibilityOnGameClose + '\'' +
                '}';
    }
}
