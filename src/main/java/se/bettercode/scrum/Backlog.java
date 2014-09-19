package se.bettercode.scrum;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Backlog {

    List<Story> stories = new ArrayList<Story>();
    IntegerProperty donePoints = new SimpleIntegerProperty(0);

    public void addStory(Story story) {
        stories.add(story);
    }

    public Story getStory() {
        for (Story story : stories) {
            if (story.getStatus() != Story.StoryState.FINISHED) {
                return story;
            }
        }
        return null; // BAH
    }

    public List<Story> getStories() {
        return stories;
    }

    public IntegerProperty getDonePoints() {
        return donePoints;
    }

    public IntegerProperty donePointsProperty() {
        return donePoints;
    }

    public int calculateFinishedPoints() {
        int total = 0;
        for (Story story : stories) {
            if (story.getStatus() == Story.StoryState.FINISHED) {
                total += story.getTotalPoints();
            }
        }
        return total;
    }


    public int getFinishedStoriesCount() {
        int total = 0;
        for (Story story : stories) {
            if (story.getStatus() == Story.StoryState.FINISHED) {
                total += 1;
            }
        }
        return total;
    }


    public int getTotalPoints() {
        int total = 0;
        for (Story story : stories) {
            total += story.getTotalPoints();
        }
        return total;
    }

    public int getWorkInProgressPoints() {
        int total = 0;
        for (Story story : stories) {
            if (story.getStatus() == Story.StoryState.STARTED) {
                total += story.getPointsDone();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "Backlog{" +
                "stories=" + stories +
                '}';
    }

    boolean runDay(int dailyBurn) {
        boolean haveWorkRemaining = true;
        while (dailyBurn > 0 && haveWorkRemaining) {
            Story story = getStory();
            if (story == null) {
                System.out.println("Sprint fully completed before running out of days!");
                haveWorkRemaining = false;
            } else {
                dailyBurn = story.workOnStory(dailyBurn);
            }
        }

        setFinishedPoints();
        return haveWorkRemaining;
    }

    private void setFinishedPoints() {
        Platform.runLater(() -> donePoints.set(calculateFinishedPoints()));
    }
}
