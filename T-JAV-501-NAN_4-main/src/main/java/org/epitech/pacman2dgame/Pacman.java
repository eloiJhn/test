package org.epitech.pacman2dgame;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pacman {
    private String currentStage;
    protected static int mapHeight = 0;
    protected static int mapWidth = 0;

    public Pacman() {
        this.loadStages();
    }

    private void loadStages() {
        List<String> stages = new ArrayList<>();
        URL resource = getClass().getResource("/org/epitech/pacman2dgame/stages");
        if (resource != null) {
            File stageFolder = new File(resource.getFile());
            File[] files = stageFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    stages.add("/org/epitech/pacman2dgame/stages/" + file.getName());
                    System.out.println("Stage loaded : /org/epitech/pacman2dgame/stages/" + file.getName());
                }
            } else {
                System.out.println("No files in the stage folder.");
            }
            System.out.println("Stage folder: " + stageFolder);
        } else {
            System.out.println("Unable to locate the stage folder.");
        }

        int random = (int) (Math.random() * stages.size());
        currentStage = stages.get(random);
    }

    public char[][] loadStage() throws Exception {
        ArrayList<String> lines = new ArrayList<>();
        URL resource = getClass().getResource(this.currentStage);
        if (resource != null) {
            System.out.println("Map loading: " + resource.getFile());
            File stage = new File(resource.getFile());
            Scanner scanner = null;
            try {
                scanner = new Scanner(stage);
            } catch (Exception e) {
                System.out.println("Unable to load the stage.");
            }
            if (scanner != null) {
                for (int i = 0; scanner.hasNextLine(); i++) {
                    String line = scanner.nextLine();
                    lines.add(line.replace(" ", ""));
                }

                if (lines.isEmpty()) {
                    throw new Exception("File is empty: " + stage);
                }
                mapHeight = lines.size();
                mapWidth = lines.get(0).length();
                char[][] map = new char[mapHeight][mapWidth];

                for (int row = 0; row < mapHeight; row++) {
                    if (lines.get(row).length() != mapWidth) {
                        throw new IOException("Inconsistent line length in file: " + stage);
                    }
                    map[row] = lines.get(row).toCharArray();
                }

                return map;
            }
        } else {
            System.out.println("Unable to locate the map file.");
        }
        return null;
    }

    public String getCurrentStage() {
        return currentStage;
    }

    public static int getMapHeight() {
        return mapHeight;
    }

    public static int getMapWidth() {
        return mapWidth;
    }
}
