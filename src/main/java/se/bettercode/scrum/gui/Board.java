package se.bettercode.scrum.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import se.bettercode.scrum.Backlog;
import se.bettercode.scrum.Story;
import se.bettercode.scrum.StoryCardController;

import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

public class Board extends GridPane {

    private Backlog backlog;
    private final VBox todoColumn = new VBox(10);
    private final VBox startedColumn = new VBox(10);
    private final VBox doneColumn = new VBox(10);
    private final AudioClip ding;

    public Board() {
        setPadding(new Insets(10));

        ding = new AudioClip(getClass().getResource("/bell.wav").toString());
        int i = 0;
        for (VBox columnBox : columns()) {
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setPercentWidth(100); // Will be treated as relative weight when sum is over 100.
            getColumnConstraints().add(constraints);
            add(columnBox, i++, 1);
        }

    }

    private List<VBox> columns() {
        return Arrays.asList(todoColumn, startedColumn, doneColumn);
    }


    public void bindBacklog(Backlog backlog) {
        this.backlog = backlog;
        for (Story story : backlog.getStories()) {
            story.statusProperty().addListener((observable, oldValue, newValue) -> {
                updateBoard();
                playDingIfDone(story);
            });
        }
        updateBoard();
    }

    private void playDingIfDone(Story story) {
        if (story.getStatus() == Story.StoryState.FINISHED) {
            for (int i=0; i < story.getTotalPoints(); i++) {
                ding.stop();
                ding.play(1.0);
                try {
                    sleep(250 + i * 20);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    private void updateBoard() {
        Platform.runLater(() -> {
            clearAllColumns();
            for (Story story : backlog.getStories()) {
                switch (story.getStatus()) {
                    case TODO:
                        todoColumn.getChildren().add(new StoryCardController(story));
                        break;
                    case STARTED:
                        startedColumn.getChildren().add(new StoryCardController(story));
                        break;
                    case FINISHED:
                        doneColumn.getChildren().add(new StoryCardController(story));
                        break;
                }
            }

        });
    }

    private void clearAllColumns() {
        todoColumn.getChildren().clear();
        startedColumn.getChildren().clear();
        doneColumn.getChildren().clear();
        addColumnHeaders();
    }

    private void addColumnHeaders() {
        todoColumn.getChildren().add(new Text("TODO"));
        startedColumn.getChildren().add(new Text("STARTED"));
        doneColumn.getChildren().add(new Text("DONE"));
    }
}
