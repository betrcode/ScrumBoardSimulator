package se.bettercode.scrum;


public class Story {

    private int doneDay;
    private int startedDay;

    public enum StoryState {TODO, STARTED, FINISHED;}

    private StoryPoint pointsDone = new StoryPoint(0);
    private StoryPoint remainingPoints;
    private StoryPoint totalPoints;

    private StoryStateProperty status = new StoryStateProperty();
    private String title = "";

    public Story(int points) {
        this(points, "");
    }

    public Story(int points, String title) {
        if (points < 0) {
            throw new IllegalArgumentException("Points must not be negative.");
        }
        this.title = title;
        totalPoints = new StoryPoint(points);
        remainingPoints = new StoryPoint(points);
    }

    public StoryStateProperty statusProperty() {
        return status;
    }

    public StoryPoint getTotalPoints() {
        return totalPoints;
    }

    public int getTotalPointsAsInt() {
        return totalPoints.getPoints();
    }

    public StoryPoint getPointsDone() {
        return pointsDone;
    }

    public int getPointsDoneAsInt() {
        return pointsDone.getPoints();
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
            applyPoints(pointsToApply);
            leftover = points - pointsToApply;
            status.setState(StoryState.FINISHED);
            doneDay = day;
        } else { // points < getRemainingPoints()
            applyPoints(points);
        }

        return leftover;
    }

    // TODO: extract to class
    private void applyPoints(int points) {
        pointsDone.add(points);
        remainingPoints.subtract(points);
    }

    public int getRemainingPoints() {
        return remainingPoints.getPoints();
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
