package se.bettercode.scrum.gui;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.text.Text;
import se.bettercode.scrum.Story;

/**
 * Created by max on 2014-09-19.
 */
public class StoryCard extends Group {
    public StoryCard(Story story) {
        Text text = new Text(story.getTitle());
        getChildren().add(text);
    }
}
