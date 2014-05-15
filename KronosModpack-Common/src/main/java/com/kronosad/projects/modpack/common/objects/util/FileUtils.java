package com.kronosad.projects.modpack.common.objects.com.kronosad.projects.modpack.common.util;


import com.kronosad.projects.modpack.common.objects.PackFile;
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

    public static boolean md5Match(PackFile file, String md5) {

        try {
            return md5.equals(DigestUtils.md5Hex(IOUtils.toByteArray(new FileInputStream(new File(file.getPath() + File.separator + file.getName())))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
