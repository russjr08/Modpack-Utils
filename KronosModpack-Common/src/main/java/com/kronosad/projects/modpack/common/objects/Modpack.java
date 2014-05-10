package com.kronosad.projects.modpack.common.objects;

import com.kronosad.projects.modpack.common.objects.enums.DistributionType;

import java.util.List;

/**
 * Author russjr08
 * Created at 5/3/14
 */
public class Modpack {

    private double version;

    private String md5, name, forgeVersion;

    private List<Mod> mods;

    private DistributionType type;

    public double getVersion() {
        return version;
    }

    public String getMd5() {
        return md5;
    }

    public String getName() {
        return name;
    }

    public String getForgeVersion() {
        return forgeVersion;
    }

    public List<Mod> getMods() {
        return mods;
    }

    public DistributionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Modpack{" +
                "version=" + version +
                ", md5='" + md5 + '\'' +
                ", name='" + name + '\'' +
                ", forgeVersion='" + forgeVersion + '\'' +
                ", mods=" + mods +
                ", type=" + type +
                '}';
    }
}
