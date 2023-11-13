package org.epitech.pacman2dgame.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.epitech.pacman2dgame.Configuration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public VBox vBox;
    @FXML
    public Button play;
    @FXML
    public Button settings;
    @FXML
    public Label scoreLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("MainController initialize");
        scoreLabel.setText("Last score: " + Configuration.config.getLastScore());
    }

    @FXML
    public void switchToInterface() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/epitech/pacman2dgame/views/interface.fxml"));
        Parent interfaceView = loader.load();

        Scene scene = vBox.getScene();
        scene.setRoot(interfaceView);
        interfaceView.requestFocus();

        Stage stage = (Stage) scene.getWindow();
        stage.setMaximized(true);
    }

    @FXML
    public void switchToSettings() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/epitech/pacman2dgame/views/settings.fxml"));
        Parent interfaceView = loader.load();
        vBox.getScene().setRoot(interfaceView);
        interfaceView.requestFocus();
    }

}
