package org.epitech.pacman2dgame.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.epitech.pacman2dgame.Pacman;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class InterfaceController implements Initializable {
    @FXML
    public VBox vBox;
    private boolean paused = false;
    private VBox pauseMenu = new VBox();
    private GridPane gridPane = new GridPane();
    private final Pacman pacman = new Pacman();
    private ImageView pacmanImageView;
    private Image pacmanLeft, pacmanRight, pacmanUp, pacmanDown;
    private MediaPlayer mediaPlayer = null;
    private char[][] map = null;
    private int dotCount = 0;
    private int dotEat = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Interface initialized");

        pacmanLeft = new Image(Objects
                .requireNonNull(getClass().getResourceAsStream("/org/epitech/pacman2dgame/images/pacman_left.gif")));
        pacmanRight = new Image(Objects
                .requireNonNull(getClass().getResourceAsStream("/org/epitech/pacman2dgame/images/pacman_right.gif")));
        pacmanUp = new Image(Objects
                .requireNonNull(getClass().getResourceAsStream("/org/epitech/pacman2dgame/images/pacman_up.gif")));
        pacmanDown = new Image(Objects
                .requireNonNull(getClass().getResourceAsStream("/org/epitech/pacman2dgame/images/pacman_down.gif")));

        vBox.setOnKeyPressed(this::handleEventKey);
        try {
            this.map = this.pacman.loadStage();
            this.createGameBoard(this.map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.runMusic();

        vBox.requestFocus();
    }

    public void handleEventKey(KeyEvent keyEvent) {

        switch (keyEvent.getCode()) {
            case LEFT:
                pacmanLeft();
                break;
            case RIGHT:
                pacmanRight();
                break;
            case UP:
                pacmanUp();
                break;
            case DOWN:
                pacmanDown();
                break;
            case ESCAPE:
                this.pauseMenu();
                break;
        }
    }

    private void createGameBoard(char[][] map) {
        pacmanImageView = new ImageView(new Image(Objects
                .requireNonNull(getClass().getResourceAsStream("/org/epitech/pacman2dgame/images/pacman_left.gif"))));
        GridPane.setRowIndex(pacmanImageView, 0);
        GridPane.setColumnIndex(pacmanImageView, 0);
        this.gridPane.getChildren().add(pacmanImageView);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Rectangle rect = new Rectangle();
                switch (map[i][j]) {
                    case 'W':
                        rect.setHeight(25);
                        rect.setWidth(25);
                        rect.setFill(Color.BLUE);
                        break;
                    case 'E':
                        rect.setFill(Color.TRANSPARENT);
                        break;
                    case 'S':
                        rect.setHeight(10);
                        rect.setWidth(10);
                        Image dot = new Image(Objects.requireNonNull(
                                getClass().getResourceAsStream("/org/epitech/pacman2dgame/images/dot.gif")));
                        rect.setFill(new ImagePattern(dot));
                        this.dotCount++;
                        break;
                    case 1:
                        rect.setFill(Color.ORANGE);
                        break;
                    case 2:
                        rect.setFill(Color.GREEN);
                        break;
                    case 'P':
                        pacmanImageView.setFitHeight(25);
                        pacmanImageView.setFitWidth(25);
                        pacmanImageView.setImage(new Image(Objects.requireNonNull(
                                getClass().getResourceAsStream("/org/epitech/pacman2dgame/images/pacman_left.gif"))));
                        GridPane.setRowIndex(pacmanImageView, i);
                        GridPane.setColumnIndex(pacmanImageView, j);
                        break;
                }

                GridPane.setRowIndex(rect, i);
                GridPane.setColumnIndex(rect, j);
                this.gridPane.getChildren().add(rect);
                GridPane.setHalignment(rect, HPos.CENTER);
                GridPane.setValignment(rect, VPos.CENTER);
                this.gridPane.setAlignment(Pos.CENTER);
            }
        }
        vBox.getChildren().add(this.gridPane);
        vBox.setAlignment(Pos.CENTER);
    }

    private void runMusic() {
        try {
            URL resource = getClass().getResource("/org/epitech/pacman2dgame/sounds/pacman.mp3");
            if (resource != null) {
                Media sound = new Media(resource.toString());
                System.out.println(sound.getSource());
                this.mediaPlayer = new MediaPlayer(sound);

                this.mediaPlayer.setVolume(1);

                this.mediaPlayer.play();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isWall(int row, int col) {
        return this.map != null && this.map[row][col] == 'W';
    }

    public void switchToMain() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/epitech/pacman2dgame/views/main.fxml"));
        Parent interfaceView = loader.load();
        vBox.getScene().setRoot(interfaceView);
        interfaceView.requestFocus();
    }

    private void pauseMenu() {
        if (this.paused == true) {
            vBox.getChildren().remove(this.pauseMenu);
            this.paused = false;
            this.pauseMenu = new VBox();
            this.gridPane.setOpacity(1);
            vBox.requestFocus(); 
        } else {
            Label pausedLabel = new Label("PAUSED");
            pausedLabel.setTextFill(Color.WHITE);
            pausedLabel.setStyle("-fx-font-size: 40px");

            Button resumeButton = new Button("RESUME");
            resumeButton.setOnAction(e -> {
                this.pauseMenu();
            });

            Button quitButton = new Button("QUIT");
            quitButton.setOnAction(e -> {
                try {
                    this.switchToMain();
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                }
            });

            this.pauseMenu.setAlignment(Pos.CENTER);
            this.pauseMenu.setSpacing(10);
            this.pauseMenu.getChildren().addAll(pausedLabel, resumeButton, quitButton);
            this.gridPane.setOpacity(0.2);
            this.paused = true;

            vBox.getChildren().add(0, this.pauseMenu);
        }
    }

    private void pacmanRight() {
        int currentColIndex = GridPane.getColumnIndex(pacmanImageView) != null
                ? GridPane.getColumnIndex(pacmanImageView)
                : 0;
        int currentRowIndex = GridPane.getRowIndex(pacmanImageView);

        if (this.paused == false) {
            if (currentColIndex < Pacman.getMapWidth() - 1 && !isWall(currentRowIndex, currentColIndex + 1)) {
                GridPane.setColumnIndex(pacmanImageView, currentColIndex + 1);
                pacmanImageView.setImage(pacmanRight);
            } else {
                System.out.println("Blocked by the wall !");
            }
        } else {
            System.out.println("Pause menu has open");
        }
    }

    private void pacmanUp() {
        int currentRowIndex = GridPane.getRowIndex(pacmanImageView) != null ? GridPane.getRowIndex(pacmanImageView) : 0;
        int currentColIndex = GridPane.getColumnIndex(pacmanImageView);

        if (!this.paused) {
            if (currentRowIndex > 0 && !isWall(currentRowIndex - 1, currentColIndex)) {
                GridPane.setRowIndex(pacmanImageView, currentRowIndex - 1);
                pacmanImageView.setImage(pacmanUp);
            } else {
                System.out.println("Blocked by the wall !");
            }
        } else {
            System.out.println("Pause menu has open");
        }
    }

    private void pacmanDown() {
        int currentRowIndex = GridPane.getRowIndex(pacmanImageView);
        int currentColIndex = GridPane.getColumnIndex(pacmanImageView);

        if (!this.paused) {
            if (currentRowIndex < Pacman.getMapHeight() - 1 && !isWall(currentRowIndex + 1, currentColIndex)) {
                GridPane.setRowIndex(pacmanImageView, currentRowIndex + 1);
                pacmanImageView.setImage(pacmanDown);
            } else {
                System.out.println("Blocked by the wall!");
            }
        } else {
            System.out.println("Pause menu has open");
        }
    }

    private void pacmanLeft() {
        int currentColIndex = GridPane.getColumnIndex(pacmanImageView);
        int currentRowIndex = GridPane.getRowIndex(pacmanImageView);

        if (!this.paused) {
            if (currentColIndex > 0 && !isWall(currentRowIndex, currentColIndex - 1)) {
                GridPane.setColumnIndex(pacmanImageView, currentColIndex - 1);
                pacmanImageView.setImage(pacmanLeft);
            } else {
                System.out.println("Blocked by the wall!");
            }
        } else {
            System.out.println("Pause menu has open");
        }
    }

}