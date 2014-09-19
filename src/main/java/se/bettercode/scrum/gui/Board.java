package se.bettercode.scrum.gui;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import se.bettercode.scrum.Backlog;
import se.bettercode.scrum.Story;

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


    public Board() {
        columns().forEach(o -> o.minWidth(300));
        getChildren().addAll(columns());
    }

    private List<VBox> columns() {
        return Arrays.asList(todoColumn, startedColumn, doneColumn);
    }


    public void bindBacklog(Backlog backlog) {
        this.backlog = backlog;
        for (Story story : backlog.getStories()) {
            story.statusProperty().addListener((observable, oldValue, newValue) -> updateBoard());
        }
        updateBoard();
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
        todoColumn.getChildren().add(new Text("TODO:                  "));
        todoColumn.getChildren().add(new Text("----------------------"));
        startedColumn.getChildren().add(new Text("STARTED                "));
        startedColumn.getChildren().add(new Text("----------------------"));
        doneColumn.getChildren().add(new Text("DONE:                  "));
        doneColumn.getChildren().add(new Text("----------------------"));
    }
}
