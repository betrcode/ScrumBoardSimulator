package se.bettercode.scrum.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import static javafx.beans.binding.Bindings.convert;

public class StatusBar extends HBox {

    private Label teamNameLabel = new Label();
    private Label teamVelocityLabel = new Label();
    private Label storyPointsDoneLabel = new Label();
    private Label currentDayLabel = new Label();


    public StatusBar() {
        setPadding(new Insets(15, 12, 15, 12));
        setSpacing(10);
        setStyle("-fx-background-color: #557799;");
        getChildren().addAll(new Label("Team: "), teamNameLabel,
                             new Label("Velocity: "), teamVelocityLabel,
                             new Label("Day: "), currentDayLabel,
                             new Label("Story points done: "), storyPointsDoneLabel);
    }

    public void bindTeamName(StringProperty teamName) {
        teamNameLabel.textProperty().bind(teamName);
    }

    public void bindTeamVelocity(IntegerProperty teamVelocity) {
        teamVelocityLabel.textProperty().bind(convert(teamVelocity));

    }

    public void bindStoryPointsDone(IntegerProperty storyPointsDone) {
        storyPointsDoneLabel.textProperty().bind(convert(storyPointsDone));
    }

    public void bindCurrentDay(IntegerProperty currentDay) {
        currentDayLabel.textProperty().bind(convert(currentDay));
    }

}
