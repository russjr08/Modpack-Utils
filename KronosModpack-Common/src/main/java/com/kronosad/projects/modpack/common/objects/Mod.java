package com.kronosad.projects.modpack.common.objects;

/**
 * Author russjr08
 * Created at 5/3/14
 */
public class Mod extends PackFile {

    private double version;

    private String author;

    public double getVersion() {
        return version;
    }

    public String getAuthor() {
        return author;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Mod{" +
                "version=" + version +
                ", author='" + author + '\'' +
                '}';
    }
}
