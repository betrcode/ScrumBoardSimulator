package se.bettercode.scrum;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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
        initScrum();

        primaryStage.setTitle("Scrum Game");
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(makeGridPane());
        borderPane.setTop(makeToolBar());
        borderPane.setBottom(makeStatusBar());
        primaryStage.setScene(new Scene(borderPane, 800, 600));
        primaryStage.show();
    }

    private void initScrum() {
        sprint = new Sprint("First sprint", 10);
        team = new Team("The Cobras", 23);
        backlog = new SmallBacklog();
        sprint.setTeam(team);
        sprint.setBacklog(backlog);
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

    private HBox makeToolBar() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        final Button loadButton = new Button("Load board");
        loadButton.setPrefSize(100, 20);

        Button startButton = new Button("Start Sprint");
        startButton.setPrefSize(100, 20);

        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addCardsToBoard();
                loadButton.setDisable(true);
            }
        });

        startButton.setOnAction((event) -> {
            sprint.runSprint();
            startButton.setDisable(true);
        });

        hbox.getChildren().addAll(loadButton, startButton);

        return hbox;
    }

    private HBox makeStatusBar() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #557799;");

        Label teamName = new Label("Team: " + team.getName());
        Label teamVelocity = new Label("Velocity: " + team.getVelocity());
        Label storyPointsDone = new Label("Story points done: " + backlog.getFinishedPoints() + "/" + backlog.getTotalPoints());
        //storyPointsDone.textProperty().bind(storiesDone);

        hbox.getChildren().addAll(teamName, teamVelocity, storyPointsDone);

        return hbox;
    }


    public void stop() {
        System.out.println("Inside stop()");
    }
}
