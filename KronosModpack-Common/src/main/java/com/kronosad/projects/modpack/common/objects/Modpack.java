package com.kronosad.projects.modpack.common.objects;

import com.google.gson.Gson;
import com.kronosad.projects.modpack.common.objects.enums.DistributionType;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Author russjr08
 * Created at 5/3/14
 */
public class Modpack {

    private double version;

    private String md5, name, forgeVersion, internetURL;

    private List<PackFile> files;

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

    public List<PackFile> getFiles() {
        return files;
    }

    public DistributionType getType() {
        return type;
    }

    public String getInternetURL() {
        return internetURL;
    }


    public void setVersion(double version) {
        this.version = version;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setForgeVersion(String forgeVersion) {
        this.forgeVersion = forgeVersion;
    }

    public void setFiles(List<PackFile> files) {
        this.files = files;
    }

    public void setType(DistributionType type) {
        this.type = type;
    }

    public void setInternetURL(String internetURL) {
        this.internetURL = internetURL;
    }


    public static Modpack getModpackFromInternet(String url) throws IOException {
        InputStream in = new URL(url).openStream();
        String textFromInternet = IOUtils.toString(in);

        return new Gson().fromJson(textFromInternet, Modpack.class);
    }

    @Override
    public String toString() {
        return "Modpack{" +
                "version=" + version +
                ", md5='" + md5 + '\'' +
                ", name='" + name + '\'' +
                ", forgeVersion='" + forgeVersion + '\'' +
                ", files=" + files +
                ", type=" + type +
                '}';
    }
}
