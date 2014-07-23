package se.bettercode.scrum;

public class Story {

    public enum StoryState {TODO, STARTED, FINISHED}

    public int getTotalPoints() {
        return totalPoints;
    }

    private int totalPoints;

    public int getPointsDone() {
        return pointsDone;
    }

    private int pointsDone = 0;

    public StoryState getStatus() {
        return status;
    }

    private StoryState status = StoryState.TODO;

    public Story(int points) {
        totalPoints = points;
    }

    /**
     *
     * @param points
     * @return any leftover points
     */
    public int workOnStory(int points) {
        status = StoryState.STARTED;


        pointsDone += points;

        if (pointsDone >= totalPoints) {
            status = StoryState.FINISHED;
        }

        if (pointsDone > totalPoints) {
            pointsDone = totalPoints;
            return pointsDone - totalPoints;
        } else {
            return 0;
        }
    }

    public int getRemainingPoints() {
        return totalPoints - pointsDone;
    }

    @Override
    public String toString() {
        return "Story{" +
                "points=" + totalPoints +
                ", pointsDone=" + pointsDone +
                ", status=" + status +
                '}';
    }
}
