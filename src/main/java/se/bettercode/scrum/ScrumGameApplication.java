package se.bettercode.scrum;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        GridPane rootNode = new GridPane();
        rootNode.setAlignment(Pos.TOP_CENTER);
        rootNode.setGridLinesVisible(true);

        Scene myScene = new Scene(rootNode, 800, 600);
        primaryStage.setScene(myScene);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(33);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(33);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(33);
        rootNode.getColumnConstraints().addAll(column1, column2, column3);


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

        rootNode.getChildren().addAll(btnAlpha, btnBeta, response, todo, doing, done);

        primaryStage.show();

    }

    public void stop() {
        System.out.println("Inside stop()");
    }
}
