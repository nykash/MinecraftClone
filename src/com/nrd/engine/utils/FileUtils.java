package com.nrd.engine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {

    public static String loadAsString(String path) {
        StringBuilder result = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(FileUtils.class.getResourceAsStream(path)));

        try (BufferedReader r = new BufferedReader(new InputStreamReader(FileUtils.class.getResourceAsStream(path)))) {
            String line = "";
            while ((line = r.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Couldn't find the file at " + path);
        }

        return result.toString();
    }
}
