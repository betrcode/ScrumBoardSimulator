package se.bettercode.scrum.gui;

import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import se.bettercode.scrum.Backlog;
import se.bettercode.scrum.Story;

import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

public class Board extends HBox {

    private Backlog backlog;
    private final VBox todoColumn = new VBox();
    private final VBox startedColumn = new VBox();
    private final VBox doneColumn = new VBox();
    private final AudioClip ding;

    public Board() {
        ding = new AudioClip(getClass().getResource("/bell.wav").toString());
        columns().forEach(o -> o.minWidth(300));
        columns().forEach(o -> o.setPrefWidth(1000));
        getChildren().addAll(columns());
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
                        todoColumn.getChildren().add(new StoryCard(story));
                        break;
                    case STARTED:
                        startedColumn.getChildren().add(new StoryCard(story));
                        break;
                    case FINISHED:
                        doneColumn.getChildren().add(new StoryCard(story));
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
