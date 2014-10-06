package se.bettercode.scrum;

import junit.framework.TestCase;
import se.bettercode.scrum.team.Team;

public class TeamTest extends TestCase {

    public void testToString() {
        Team team = new Team("Dingos", 17);
        assertEquals("Team{name=Dingos, velocity=17, WIP limit=1}", team.toString());
    }

    public void testDefaultVelocity() {
        Team team = new Team("Rhinos", 10);
        assertEquals(1, team.getWorkInProgressLimit());
    }

    public void testInvalidWIPLimit() {
        Throwable e = null;

        try {
            Team team = new Team("Monkeys", 20, 0);
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof AssertionError);
    }
}
