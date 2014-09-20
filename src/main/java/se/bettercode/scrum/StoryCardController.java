package se.bettercode.scrum;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;

public class StoryCardController extends BorderPane {

    @FXML
    private Text storyPoints;

    @FXML
    private Text storyTitle;

    @FXML
    private BorderPane storyCard;


    public StoryCardController(Story story) {
        URL location = getClass().getResource("StoryCard.fxml");
        //ResourceBundle resourceBundle = ResourceBundle.getBundle("se.bettercode.scrum");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        storyTitle.setText(story.getTitle());
        storyPoints.setText(Integer.toString(story.getTotalPoints()));
    }

    public void bindStoryTitle(StringProperty title) {
        storyTitle.textProperty().bind(title);
    }

    public void bindStoryPoints(IntegerProperty points) {
        storyPoints.textProperty().bind(Bindings.convert(points));
    }
}
