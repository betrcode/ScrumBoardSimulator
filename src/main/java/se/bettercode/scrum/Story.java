package se.bettercode.scrum;


public class Story {

    private int doneDay;
    private int startedDay;

    public enum StoryState {TODO, STARTED, FINISHED;}
    private StoryPointSet storyPointSet;

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
        storyPointSet = new StoryPointSet(points);
    }

    public StoryStateProperty statusProperty() {
        return status;
    }

    public StoryPoint getTotalPoints() {
        return storyPointSet.getTotal();
    }

    public int getTotalPointsAsInt() {
        return getTotalPoints().getPoints();
    }

    public StoryPoint getPointsDone() {
        return storyPointSet.getDone();
    }

    public int getPointsDoneAsInt() {
        return getPointsDone().getPoints();
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
            storyPointSet.apply(pointsToApply);
            leftover = points - pointsToApply;
            status.setState(StoryState.FINISHED);
            doneDay = day;
        } else { // points < getRemainingPoints()
            storyPointSet.apply(points);
        }

        return leftover;
    }

    public int getRemainingPoints() {
        return storyPointSet.getRemaining().getPoints();
    }

    @Override
    public String toString() {
        return "Story{" +
                "points=" + getTotalPoints().getPoints() +
                ", pointsDone=" + getPointsDone().getPoints() +
                ", status=" + status.getValue() +
                '}';
    }
}
