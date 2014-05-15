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

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setUrl(String url) {
        this.url = url;
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
