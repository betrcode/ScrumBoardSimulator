package se.bettercode.scrum;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import se.bettercode.scrum.backlog.Backlog;
import se.bettercode.scrum.backlog.SmallBacklog;
import se.bettercode.scrum.backlog.WellSlicedBacklog;
import se.bettercode.scrum.gui.Board;
import se.bettercode.scrum.gui.StatusBar;
import se.bettercode.scrum.gui.ToolBar;
import se.bettercode.scrum.prefs.StageUserPrefs;

import java.util.HashMap;
import java.util.Map;


public class ScrumGameApplication extends Application {

    private Board board = new Board();
    private Sprint sprint;
    private Team team;
    private Backlog backlog;
    private StatusBar statusBar = new StatusBar();
    //private Map<String, Backlog> backlogMap = new HashMap<>();
    private String[] backlogNames = new String[]{"Small", "Well sliced"};
    private ToolBar toolBar = new ToolBar(backlogNames);
    private Stage primaryStage;
    private StageUserPrefs prefs;

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
        this.primaryStage = primaryStage;
        prefs = new StageUserPrefs(primaryStage);
        prefs.load();
        setStage();
        bindActionsToToolBar();
        primaryStage.show();
    }

    private void setStage() {
        primaryStage.setTitle("Scrum Game");
        BorderPane borderPane = new BorderPane();
        board.prefWidthProperty().bind(primaryStage.widthProperty());
        borderPane.setCenter(board);
        borderPane.setTop(toolBar);
        borderPane.setBottom(statusBar);
        primaryStage.setScene(new Scene(borderPane, 800, 600));
    }

    private void initSprint(String backlogName) {
        team = new Team("The Cobras", 23);

        switch (backlogName) {
            case "Small":
                backlog = new SmallBacklog();
                break;
            case "Well sliced":
                backlog = new WellSlicedBacklog();
                break;
        }

        sprint = new Sprint("First sprint", 10, team, backlog);

        board.bindBacklog(backlog);
        toolBar.bindRunningProperty(sprint.runningProperty());
    }

    private void bindSprintDataToStatusBar() {
        statusBar.bindTeamName(team.nameProperty());
        statusBar.bindTeamVelocity(team.velocityProperty());
        statusBar.bindStoryPointsDone(backlog.donePointsProperty());
        statusBar.bindDaysInSprint(sprint.lengthInDaysProperty());
        statusBar.bindCurrentDay(sprint.currentDayProperty());
        statusBar.bindLeadTime(backlog.averageLeadTimeProperty());
    }

    private void bindActionsToToolBar() {
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                loadData(newValue.toString());
            }
        };

        toolBar.setChangeListener(changeListener);
        toolBar.setLoadButtonAction(event -> loadData("Small"));
        toolBar.setStartButtonAction((event) -> sprint.runSprint());
    }

    private void loadData(String backlogName) {
        initSprint(backlogName);
        bindSprintDataToStatusBar();
    }

    public void stop() {
        System.out.println("Inside stop()");
        prefs.save();
    }

}
