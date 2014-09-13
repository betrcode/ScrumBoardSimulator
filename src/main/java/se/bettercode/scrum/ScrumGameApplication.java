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


public class ScrumGameApplication extends Application {

    Label response;


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
        GridPane gridPane = getGridPane();
        borderPane.setCenter(gridPane);
        borderPane.setTop(getToolbar());
        Scene myScene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(myScene);

        primaryStage.show();
    }

    private GridPane getGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setGridLinesVisible(true);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(33);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(33);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(33);
        gridPane.getColumnConstraints().addAll(column1, column2, column3);

        Label todo = new Label("TODO");
        Label doing = new Label("DOING");
        Label done = new Label("DONE");
        response = new Label("Push a button");

        Button btnAlpha = new Button("Alpha");
        Button btnBeta = new Button("Beta");

        GridPane.setConstraints(todo, 0, 0);
        GridPane.setConstraints(doing, 1, 0);
        GridPane.setConstraints(done, 2, 0);

        GridPane.setConstraints(btnAlpha, 1, 2);
        GridPane.setConstraints(btnBeta, 2, 2);
        GridPane.setConstraints(response, 1, 3);

        btnAlpha.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                response.setText("Alpha was pressed.");
            }
        });

        btnBeta.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                response.setText("Beta was pressed.");
            }
        });

        gridPane.getChildren().addAll(btnAlpha, btnBeta, response, todo, doing, done);

        return gridPane;
    }

    public HBox getToolbar() {
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
                loadButton.setDisable(true);
            }
        });

        hbox.getChildren().addAll(loadButton, startButton);

        return hbox;
    }


    public void stop() {
        System.out.println("Inside stop()");
    }
}
