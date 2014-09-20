package se.bettercode;

import se.bettercode.scrum.Backlog;
import se.bettercode.scrum.SmallBacklog;
import se.bettercode.scrum.Sprint;
import se.bettercode.scrum.Team;

public class Main {

    public static void main(String[] args) {
        Team team = new Team("The Cobras", 23);
        Backlog backlog = new SmallBacklog();
        Sprint sprint = new Sprint("First sprint", 10, team, backlog);
        sprint.runSprint();
    }
}
