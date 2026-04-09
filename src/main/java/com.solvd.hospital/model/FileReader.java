package com.solvd.hospital.model;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileReader {
    public String readFile(String fileName) {
        List<String> lines = null;
        try {
            File file = new File(fileName);
            lines = FileUtils.readLines(file, StandardCharsets.UTF_8);

        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return String.join("\n", lines);
    }
}
