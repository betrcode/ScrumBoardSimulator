package se.bettercode.scrum.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class StatusBar extends HBox {

    private Label teamNameLabel = new Label("(dummy)");
    private Label teamVelocityLabel = new Label("");
    private Label storyPointsDoneLabel = new Label("");


    public StatusBar() {
        setPadding(new Insets(15, 12, 15, 12));
        setSpacing(10);
        setStyle("-fx-background-color: #557799;");

        //storyPointsDone.textProperty().bind(storiesDone);
        //teamName.textProperty().bind(team.getName());
        //StringBinding label = team.name.;

        getChildren().addAll(new Label("Team: "), teamNameLabel, new Label("Velocity: "), teamVelocityLabel, new Label("Story points done: "), storyPointsDoneLabel);
    }

    public void bindTeamName(StringProperty teamName) {
        teamNameLabel.textProperty().bind(teamName);
    }

    public void bindTeamVelocity(IntegerProperty teamVelocity) {
        teamVelocityLabel.textProperty().bind(Bindings.convert(teamVelocity));

    }

    public void bindStoryPointsDone(IntegerProperty storyPointsDone) {
        storyPointsDoneLabel.textProperty().bind(Bindings.convert(storyPointsDone));
    }
}
