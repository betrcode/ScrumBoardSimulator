package se.bettercode.scrum.gui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

import static javafx.beans.binding.Bindings.convert;

public class StatusBar extends HBox {

    private Label teamNameLabel = new Label();
    private Label teamVelocityLabel = new Label();
    private Label storyPointsDoneLabel = new Label();
    private Label currentDayLabel = new Label();
    private ProgressBar progressBar = new ProgressBar(0.0);
    private IntegerProperty daysInSprint;


    public StatusBar() {
        setPadding(new Insets(15, 12, 15, 12));
        setSpacing(10);
        setStyle("-fx-background-color: #557799;");
        getChildren().addAll(new Label("Team: "), teamNameLabel,
                             new Label("Velocity: "), teamVelocityLabel,
                             new Label("Day: "), currentDayLabel,
                             new Label("Story points done: "), storyPointsDoneLabel,
                             progressBar);
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
        // A bit of chain binding to convert integers to doubles
        SimpleDoubleProperty daysInSprintDouble = new SimpleDoubleProperty();
        daysInSprintDouble.bind(daysInSprint);
        SimpleDoubleProperty currentDayDouble = new SimpleDoubleProperty();
        currentDayDouble.bind(currentDay);
        progressBar.progressProperty().bind(currentDayDouble.divide(daysInSprintDouble));
    }

    public void bindDaysInSprint(IntegerProperty daysInSprint) {
        this.daysInSprint = daysInSprint;
    }

}
