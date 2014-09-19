package se.bettercode.scrum;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by max on 2014-09-19.
 */
public class StoryStateProperty extends SimpleStringProperty {


    public StoryStateProperty() {
        setState(Story.StoryState.TODO);
    }
    
    public void setState(Story.StoryState storyState) {
        setValue(storyState.name());
    }

    public Story.StoryState getState() {
        return Story.StoryState.valueOf(get());
    }
}
