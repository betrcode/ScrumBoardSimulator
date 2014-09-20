package se.bettercode.scrum.gui;

import javafx.beans.property.BooleanProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ToolBar extends HBox {

    private final Button loadButton = new Button("Load board");
    private final Button startButton = new Button("Start Sprint");

    public ToolBar() {
        setPadding(new Insets(15, 12, 15, 12));
        setSpacing(10);
        setStyle("-fx-background-color: #336699;");

        loadButton.setPrefSize(100, 20);
        startButton.setPrefSize(100, 20);

        getChildren().addAll(loadButton, startButton);
    }

    public void setLoadButtonAction(EventHandler eventHandler) {
        loadButton.setOnAction(eventHandler);
    }

    public void setStartButtonAction(EventHandler eventHandler) {
        startButton.setOnAction(eventHandler);
    }

    public void bindRunningProperty(BooleanProperty booleanProperty) {
        loadButton.disableProperty().bind(booleanProperty);
        startButton.disableProperty().bind(booleanProperty);
    }
}