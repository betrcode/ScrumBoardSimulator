package se.bettercode.scrum;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Sprint {

    private String name;
    private int lengthInDays;
    private Team team;
    private Backlog backlog;
    private IntegerProperty currentDay = new SimpleIntegerProperty(0);

    public Sprint(String name, int lengthInDays) {
        this.name = name;
        this.lengthInDays = lengthInDays;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    public int getDailyBurnrate() {
        return team.velocityProperty().get() / lengthInDays;
    }

    public int getCurrentDay() {
        return currentDay.get();
    }

    public IntegerProperty currentDayProperty() {
        return currentDay;
    }

    public void runSprint() {
        new Thread() {

            @Override
            public void run() {
                assert team != null : "Team is null";
                assert backlog != null : "Backlog is null";
                System.out.println("Running Sprint simulation with team \"" + team.nameProperty() + "\" (velocity " + team.velocityProperty() + ") for Sprint \"" + name + "\" for " + lengthInDays + " days...");
                System.out.println(backlog);
                System.out.println("Total backlog size is " + backlog.getTotalPoints() + " points.");
                System.out.println("Burning through backlog at " + getDailyBurnrate() + " points per day.");

                for (int day=0; day<=lengthInDays; day++) {
                    setCurrentDay(day);
                    System.out.println("Day " + day + ": " + backlog.getFinishedStoriesCount() + " finished stories in total.");
                    int dailyBurn = getDailyBurnrate();
                    boolean haveWorkRemaining = backlog.runDay(dailyBurn);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!haveWorkRemaining) {
                        break;
                    }
                }
                System.out.println(backlog);
                System.out.println("A total of " + backlog.calculateFinishedPoints() + " points have been finished!");
                System.out.println("Wasted " + backlog.getWorkInProgressPoints() + " points");
            }

        }.start();
    }

    private void setCurrentDay(int day) {
        Platform.runLater(() -> {currentDay.set(day);});
    }

}
