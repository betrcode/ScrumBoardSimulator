package se.bettercode.scrum.gui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import se.bettercode.scrum.Story;

public class StoryCard extends Group {

    public static final int CARD_WIDTH = 130;
    public static final int WRAPPING_WIDTH = CARD_WIDTH - 10;
    public static final int MEDIUM_STORY_POINTS = 5;
    private Story story;

    public StoryCard(Story story) {
        this.story = story;
        getChildren().addAll(getRectangle(), getText(), getStoryPointText());
    }

    private Text getStoryPointText() {
        return new Text(CARD_WIDTH-14, getHeight()-6, story.getTotalPoints() + "");
    }

    private Text getText() {
        Text text = new Text(10, 15, story.getTitle());
        text.setWrappingWidth(WRAPPING_WIDTH);
        return text;
    }

    private Rectangle getRectangle() {
        Rectangle rectangle = new Rectangle(CARD_WIDTH, getHeight());
        rectangle.setFill(Color.LEMONCHIFFON);
        rectangle.setStroke(Color.LIGHTGREEN);
        rectangle.setStrokeWidth(4);
        rectangle.setArcHeight(10);
        rectangle.setArcWidth(10);
        return rectangle;
    }

    private int getHeight() {
        if (story.getTotalPoints() > MEDIUM_STORY_POINTS) {
            return 85;
        } else if (story.getTotalPoints() < MEDIUM_STORY_POINTS) {
            return 45;
        } else {
            return 65;
        }

    }

}
