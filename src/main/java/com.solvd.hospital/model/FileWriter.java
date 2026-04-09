package com.solvd.hospital.model;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileWriter {
    public static void writeFile(String fileName, String content) throws IOException {
        FileUtils.write(new File(fileName), content, StandardCharsets.UTF_8,true);
    }
}
