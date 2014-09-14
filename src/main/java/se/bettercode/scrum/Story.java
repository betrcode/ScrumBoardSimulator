package se.bettercode.scrum;

public class Story {

    public enum StoryState {TODO, STARTED, FINISHED}
    private int totalPoints;
    private int pointsDone = 0;
    private StoryState status = StoryState.TODO;
    private String title = "";

    public Story(int points) {
        this(points, "");
    }

    public Story(int points, String title) {
        assert (points >= 0) : "Points must not be a negative value.";
        this.title = title;
        totalPoints = points;
    }

    public int getTotalPoints() {
        return totalPoints;
    }
    public int getPointsDone() {
        return pointsDone;
    }
    public StoryState getStatus() {
        return status;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @param points
     * @return any leftover points
     */
    public int workOnStory(int points) {
        System.out.println("Working on story: " + this.getTitle());
        status = StoryState.STARTED;


        pointsDone += points;

        if (pointsDone >= totalPoints) {
            status = StoryState.FINISHED;
            System.out.println("Finished story: " + this.getTitle());
        }

        if (pointsDone > totalPoints) {
            pointsDone = totalPoints;
            return points - totalPoints;
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
