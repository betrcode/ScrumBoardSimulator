package se.bettercode.scrum;

import junit.framework.TestCase;

public class SprintTest extends TestCase {

    Sprint sprint;
    Team team;
    Backlog backlog;

    public void setUp() throws Exception {
        super.setUp();
        team = new Team("Rock Stars", 23);
        backlog = new SmallBacklog();
        sprint = new Sprint("First Sprint", 10, team, backlog);
    }

    public void testGetRunning() {
        assertEquals(false, sprint.getRunning());
    }

}
