package org.epitech.pacman2dgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/main.fxml"));
        Parent root = fxmlLoader.load();

        stage.setTitle("T-JAV-501 - Pacman 2D Game");

        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("images/logo.png"))));
        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth()/2);
        stage.setHeight(primaryScreenBounds.getHeight()/2);

        stage.centerOnScreen();

        stage.show();
        root.requestFocus();
    }

    public static void run() {
        Configuration.loadConfig();
        launch();
    }
}
