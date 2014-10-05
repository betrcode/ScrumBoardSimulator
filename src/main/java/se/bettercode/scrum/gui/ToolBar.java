package se.bettercode.scrum.gui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class ToolBar extends HBox {

    private final Button loadButton = new Button("Load board");
    private final Button startButton = new Button("Start Sprint");
    private ChoiceBox choiceBox = new ChoiceBox();

    public void setChangeListener(ChangeListener changeListener) {
        choiceBox.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }

    public ToolBar(String[] backlogs) {
        setPadding(new Insets(15, 12, 15, 12));
        setSpacing(10);
        setStyle("-fx-background-color: #336699;");

        choiceBox.setItems(FXCollections.observableArrayList(backlogs));
        choiceBox.setTooltip(new Tooltip("Select backlog"));
        loadButton.setPrefSize(100, 20);
        startButton.setPrefSize(100, 20);

        getChildren().addAll(choiceBox, loadButton, startButton);
    }

    public void setLoadButtonAction(EventHandler<ActionEvent> eventHandler) {
        loadButton.setOnAction(eventHandler);
    }

    public void setStartButtonAction(EventHandler<ActionEvent> eventHandler) {
        startButton.setOnAction(eventHandler);
    }

    public void bindRunningProperty(BooleanProperty booleanProperty) {
        loadButton.disableProperty().bind(booleanProperty);
        startButton.disableProperty().bind(booleanProperty);
    }

}
