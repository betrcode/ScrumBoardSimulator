package se.bettercode.scrum;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import se.bettercode.scrum.gui.StatusBar;
import se.bettercode.scrum.gui.ToolBar;

import java.util.ArrayList;


public class ScrumGameApplication extends Application {

    GridPane gridPane;

    public Sprint getSprint() {
        return sprint;
    }

    public Team getTeam() {
        return team;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    Sprint sprint;
    Team team;
    Backlog backlog;

    StatusBar statusBar = new StatusBar();
    ToolBar toolBar = new ToolBar();

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
        borderPane.setCenter(makeGridPane());
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

    private GridPane makeGridPane() {
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setGridLinesVisible(true);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(33);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(33);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(33);
        gridPane.getColumnConstraints().addAll(column1, column2, column3);

        Label todo = new Label(Story.StoryState.TODO.toString());
        Label doing = new Label(Story.StoryState.STARTED.toString());
        Label done = new Label(Story.StoryState.FINISHED.toString());

        GridPane.setConstraints(todo, 0, 0);
        GridPane.setConstraints(doing, 1, 0);
        GridPane.setConstraints(done, 2, 0);

        gridPane.getChildren().addAll(todo, doing, done);

        return gridPane;
    }

    private void loadData() {
        initSprint();
        bindSprintDataToStatusBar();
        addCardsToBoard();
    }

    private void addCardsToBoard() {
        ArrayList listOfCards = new ArrayList();
        int i = 1;
        for (Story story: backlog.getStories()) {
            Label card = new Label(story.getTitle() + " " + "(" + story.getTotalPoints() + ")");
            //card.setTextFill(Color.web("#0076a3"));
            card.setStyle("-fx-background-color:rgba(85, 255, 68,0.7)");
            listOfCards.add(card);
            GridPane.setConstraints(card, 0, i);
            i++;
        }
        gridPane.getChildren().addAll(listOfCards);
    }

    public void stop() {
        System.out.println("Inside stop()");
    }
}
