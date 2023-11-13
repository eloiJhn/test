package org.epitech.pacman2dgame.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.epitech.pacman2dgame.Configuration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class SettingsController implements Initializable {
    @FXML
    public VBox vBox;
    @FXML
    public GridPane gridPane;
    @FXML
    public TextField username;
    @FXML
    public Button save;
    @FXML
    public Button resetScore;
    @FXML
    public Button switchToMain;

    private final Label notificationLabel = new Label();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("SettingsController initialize");
        username.setText(Configuration.config.getUsername());
    }

    @FXML
    public void save() {
        gridPane.getChildren().remove(notificationLabel);
        if (username.getText().isEmpty() || username.getText().trim().isEmpty()) {
            notificationLabel.setText("Username cannot be empty");
            notificationLabel.setStyle("-fx-text-fill: red;-fx-font-size: 12px");
            GridPane.setColumnIndex(notificationLabel, 1);
            GridPane.setRowIndex(notificationLabel, 1);
            gridPane.getChildren().add(notificationLabel);
            return;
        }

        notificationLabel.setText("The information has been saved !");
        notificationLabel.setStyle("-fx-text-fill: green;-fx-font-size: 12px");
        GridPane.setColumnIndex(notificationLabel, 1);
        GridPane.setRowIndex(notificationLabel, 1);
        gridPane.getChildren().add(notificationLabel);

        Configuration.config.setUsername(username.getText());
        Configuration.save();
    }

    @FXML
    public void resetScore() {
        Configuration.config.setLastScore(0);
        Configuration.save();

        notificationLabel.setText("Your score has been reset");
        notificationLabel.setStyle("-fx-text-fill: green;-fx-font-size: 12px");
        GridPane.setColumnIndex(notificationLabel, 1);
        GridPane.setRowIndex(notificationLabel, 1);
        gridPane.getChildren().add(notificationLabel);

        resetScore.setDisable(true);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                resetScore.setDisable(false);
            }
        }, 2500);
    }

    @FXML
    public void switchToMain() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/epitech/pacman2dgame/views/main.fxml"));
        Parent interfaceView = loader.load();
        vBox.getScene().setRoot(interfaceView);
        interfaceView.requestFocus();
    }
}
