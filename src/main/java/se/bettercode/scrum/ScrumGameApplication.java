package se.bettercode.scrum;/**
 * Created by max on 9/13/14.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class ScrumGameApplication extends Application {

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
        FlowPane rootNode = new FlowPane();
        Scene myScene = new Scene(rootNode, 300, 200);
        primaryStage.setScene(myScene);

        Label myLabel = new Label("This is the Scrum Game");
        rootNode.getChildren().add(myLabel);

        primaryStage.show();

    }

    public void stop() {
        System.out.println("Inside stop()");
    }
}
