package se.bettercode.scrum.gui;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import se.bettercode.scrum.Backlog;
import se.bettercode.scrum.Story;


import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Created by max on 2014-09-19.
 */
public class Board extends HBox {

    private Backlog backlog;
    private final VBox todoColumn = new VBox();
    private final VBox startedColumn = new VBox();
    private final VBox doneColumn = new VBox();
    private final AudioClip ding;

    public Board() {
        ding = new AudioClip("http://soundbible.com/grab.php?id=55&type=wav");
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
            ding.setCycleCount(story.getTotalPoints());
            ding.setRate(6.0);
            ding.play(1.0);
        }
    }

    private void updateBoard() {
        Platform.runLater(() -> {
            //AudioClip ding = new AudioClip("file://home/max/workspace/scrumboardsimulator/src/main/resources/ding.mp3");
            //AudioClip ding2 = new AudioClip("http://soundbible.com/grab.php?id=56&type=wav");
            //final URL resource = getClass().getResource("ding.mp3");
            //AudioClip ding3 = new AudioClip(resource.toString());
            //ding.play(1.0);
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
