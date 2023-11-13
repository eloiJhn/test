package org.epitech.pacman2dgame;

import org.epitech.pacman2dgame.Converter.JSONConverter;
import org.epitech.pacman2dgame.Model.Config;

import java.io.File;
import java.io.IOException;

public class Configuration {
    private static final JSONConverter jsonConverter = new JSONConverter();
    public static Config config = new Config();
    protected static String path = System.getProperty("user.home") + "/.pacman2dgame";

    public static void loadConfig() {
        gameFolder();
        configFile();
    }

    private static void gameFolder() {
        File folder = new File(path);
        if (folder.mkdir()) {
            System.out.println("The configuration folder has been created.");
        } else {
            System.out.println("The configuration folder already exists.");
        }

    }

    private static void configFile() {
        try {
            File file = new File(path + "/config.json");
            if (file.createNewFile()) {
                jsonConverter.objectToJson(config, file);
                System.out.println("The configuration file has been created.");
            } else {
                config = (Config) jsonConverter.stringToObject(file, Config.class);
                System.out.println("The configuration file already exists.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    
    public static void save() {
        File folder = new File(path);
        File file = new File(path + "/config.json");
        if (!folder.exists() && !file.exists()) { loadConfig(); }
        jsonConverter.objectToJson(config, file);
        System.out.println("The configuration file has been updated.");
    }

}
