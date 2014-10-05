package se.bettercode.scrum;


public class Story {

    private int doneDay;
    private int startedDay;

    public enum StoryState {TODO, STARTED, FINISHED;}
    private int totalPoints;
    private int pointsDone = 0;
    private StoryStateProperty status = new StoryStateProperty();
    private String title = "";

    public Story(int points) {
        this(points, "");
    }

    public Story(int points, String title) {
        assert (points >= 0) : "Points must not be a negative value.";
        this.title = title;
        totalPoints = points;
    }

    public StoryStateProperty statusProperty() {
        return status;
    }

    public int getTotalPoints() {
        return totalPoints;
    }
    public int getPointsDone() {
        return pointsDone;
    }
    public StoryState getStatus() {
        return status.getState();
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLeadTime() {
        return doneDay - startedDay;
    }

    /**
     *
     * @param points
     * @return any leftover points
     */
    public int workOnStory(int points, int day) {
        if (status.getState() == StoryState.TODO) {
            status.setState(StoryState.STARTED);
            startedDay = day;
        }

        int leftover = 0;
        int pointsToApply = 0;

        if (points >= getRemainingPoints()) {
            pointsToApply = getRemainingPoints();
            pointsDone += pointsToApply;
            leftover = points - pointsToApply;
            status.setState(StoryState.FINISHED);
            doneDay = day;
        } else { // points < getRemainingPoints()
            pointsDone += points;
        }

        return leftover;
    }

    public int getRemainingPoints() {
        return totalPoints - pointsDone;
    }

    @Override
    public String toString() {
        return "Story{" +
                "points=" + totalPoints +
                ", pointsDone=" + pointsDone +
                ", status=" + status.getValue() +
                '}';
    }
}
