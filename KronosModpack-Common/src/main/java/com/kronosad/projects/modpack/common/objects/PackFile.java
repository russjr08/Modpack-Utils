package com.kronosad.projects.modpack.common.objects;

/**
 * Author russjr08
 * Created at 5/3/14
 */
public class PackFile {

    private String md5, name, path, url;

    public String getMd5() {
        return md5;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "PackFile{" +
                "md5='" + md5 + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
