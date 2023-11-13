module org.epitech.pacman2dgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires com.fasterxml.jackson.databind;
    requires javafx.media;

    opens org.epitech.pacman2dgame to javafx.fxml;
    exports org.epitech.pacman2dgame;
    exports org.epitech.pacman2dgame.Controller;
    exports org.epitech.pacman2dgame.Converter;
    exports org.epitech.pacman2dgame.Model;
}