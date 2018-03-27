package com.shaw.fleshServer.utils;

import com.shaw.fleshServer.Stub;

import java.io.File;

public class ResourceUtil {

    public static String getResourcePath() {
        File file = new File(Stub.class.getClassLoader().getResource("").getPath());
        return file.getParentFile().getParentFile().getAbsolutePath();
    }
}
