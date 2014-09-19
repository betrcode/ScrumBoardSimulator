package se.bettercode.scrum;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import se.bettercode.scrum.gui.Board;
import se.bettercode.scrum.gui.StatusBar;
import se.bettercode.scrum.gui.ToolBar;

import java.util.ArrayList;


public class ScrumGameApplication extends Application {

    Board board = new Board();

    Sprint sprint;
    Team team;
    Backlog backlog;

    StatusBar statusBar = new StatusBar();
    ToolBar toolBar = new ToolBar();

    public Sprint getSprint() {
        return sprint;
    }

    public Team getTeam() {
        return team;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public static void main(String[] args) {
        System.out.println("Launching JavaFX application.");
        launch(args);
    }

    @Override
    public void init() {
        System.out.println("Inside init()");
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Inside start()");
        primaryStage.setTitle("Scrum Game");
        BorderPane borderPane = new BorderPane();
        board.prefWidthProperty().bind(primaryStage.widthProperty());
        borderPane.setCenter(board);
        borderPane.setTop(toolBar);
        borderPane.setBottom(statusBar);
        primaryStage.setScene(new Scene(borderPane, 800, 600));

        bindActionsToToolBar();

        primaryStage.show();
    }

    private void initSprint() {
        sprint = new Sprint("First sprint", 10);
        team = new Team("The Cobras", 23);
        backlog = new SmallBacklog();
        sprint.setTeam(team);
        sprint.setBacklog(backlog);

        board.bindBacklog(backlog);
        toolBar.bindRunningProperty(sprint.runningProperty());
    }

    private void bindSprintDataToStatusBar() {
        statusBar.bindTeamName(team.nameProperty());
        statusBar.bindTeamVelocity(team.velocityProperty());
        statusBar.bindStoryPointsDone(backlog.donePointsProperty());
        statusBar.bindDaysInSprint(sprint.lengthInDaysProperty());
        statusBar.bindCurrentDay(sprint.currentDayProperty());
    }

    private void bindActionsToToolBar() {
        toolBar.setLoadButtonAction(event -> loadData());
        toolBar.setStartButtonAction((event) -> sprint.runSprint());
    }



    private void loadData() {
        initSprint();
        bindSprintDataToStatusBar();
    }


    public void stop() {
        System.out.println("Inside stop()");
    }
}
