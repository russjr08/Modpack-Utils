package com.kronosad.projects.modpack.common.objects.util;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Author russjr08
 * Created at 5/3/14
 */
public class FileUtils {

    public static boolean md5Match(File file, String md5) {

        try {
            return md5.equals(DigestUtils.md5Hex(IOUtils.toByteArray(new FileInputStream(file))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
