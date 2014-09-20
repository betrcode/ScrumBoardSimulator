package se.bettercode.scrum;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class StoryCardController {

    @FXML
    private Text storyPoints;

    @FXML
    private Text storyTitle;

    @FXML
    private BorderPane storyCard;


    public StoryCardController() {
        System.out.println("Controller created");
    }
}
