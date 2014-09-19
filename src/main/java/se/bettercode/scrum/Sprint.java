package se.bettercode.scrum;

public class Sprint {

    private String name;
    private int lengthInDays;
    private Team team;
    private Backlog backlog;

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
        return team.getVelocity().get() / lengthInDays;
    }

    public void runSprint() {
        assert team != null : "Team is null";
        assert backlog != null : "Backlog is null";
        System.out.println("Running Sprint simulation with team \"" + team.getName() + "\" (velocity " + team.getVelocity() + ") for Sprint \"" + name + "\" for " + lengthInDays + " days...");
        System.out.println(backlog);
        System.out.println("Total backlog size is " + backlog.getTotalPoints() + " points.");
        System.out.println("Burning through backlog at " + getDailyBurnrate() + " points per day.");

        for (int day=0; day<=lengthInDays; day++) {
            System.out.println("Day " + day + ": " + backlog.getFinishedStoriesCount() + " finished stories in total.");
            int dailyBurn = getDailyBurnrate();
            boolean haveWorkRemaining = backlog.runDay(dailyBurn);
            if (!haveWorkRemaining) {
                break;
            }
        }
        System.out.println(backlog);
        System.out.println("A total of " + backlog.calculateFinishedPoints() + " points have been finished!");
        System.out.println("Wasted " + backlog.getWorkInProgressPoints() + " points");
    }

}
