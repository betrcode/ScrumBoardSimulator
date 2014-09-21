package se.bettercode.scrum;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Backlog {

    List<Story> stories = new ArrayList<Story>();
    IntegerProperty donePoints = new SimpleIntegerProperty(0);
    private DoubleProperty averageLeadTime = new SimpleDoubleProperty();

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

    public int getDonePoints() {
        return donePoints.get();
    }

    public IntegerProperty donePointsProperty() {
        return donePoints;
    }

    public DoubleProperty averageLeadTimeProperty() {
        return averageLeadTime;
    }

    public double getAverageLeadTime() {
        return averageLeadTime.get();
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
                ", average lead time=" + getAverageLeadTime() +
                '}';
    }

    boolean runDay(int dailyBurn, int day) {
        boolean haveWorkRemaining = true;
        while (dailyBurn > 0 && haveWorkRemaining) {
            Story story = getStory();
            if (story == null) {
                System.out.println("Sprint fully completed before running out of days!");
                haveWorkRemaining = false;
            } else {
                dailyBurn = story.workOnStory(dailyBurn, day);
            }
        }

        setFinishedPoints();
        setAverageLeadTime();
        return haveWorkRemaining;
    }

    private void setAverageLeadTime() {
        Platform.runLater(() -> averageLeadTime.set(calculatedAverageLeadTime()));
    }

    private double calculatedAverageLeadTime() {
        double totalLeadTime = 0.0;
        int count = 0;
        for (Story story : stories) {
            if (story.getStatus() == Story.StoryState.FINISHED) {
                totalLeadTime += story.getLeadTime();
                count++;
            }
        }
        if (count == 0) {
            return 0.0;
        } else {
            return totalLeadTime / count;
        }
    }

    private void setFinishedPoints() {
        Platform.runLater(() -> donePoints.set(calculateFinishedPoints()));
    }

}
