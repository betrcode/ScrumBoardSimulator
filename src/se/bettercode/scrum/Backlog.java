package se.bettercode.scrum;

import java.util.ArrayList;
import java.util.List;

public class Backlog {

    List<Story> stories = new ArrayList<Story>();

    public Backlog() {
        stories.add(new Story(3));
        stories.add(new Story(5));
        stories.add(new Story(8));
        stories.add(new Story(5));
        stories.add(new Story(1));
    }

    public Story getStory() {
        for (Story story : stories) {
            if (story.getStatus() != Story.StoryState.FINISHED) {
                return story;
            }
        }
        return null; // BAH
    }

    public int getFinishedPoints() {
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
}
