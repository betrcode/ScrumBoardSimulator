package se.bettercode.scrum;

import org.junit.Test;
import se.bettercode.scrum.team.Team;

import static org.junit.Assert.assertEquals;

public class TeamTest {

    @Test
    public void testToString() {
        Team team = new Team("Dingos", 17);
        assertEquals("Team{name=Dingos, velocity=17, WIP limit=1}", team.toString());
    }

    @Test
    public void testDefaultVelocity() {
        Team team = new Team("Rhinos", 10);
        assertEquals(1, team.getWorkInProgressLimit());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidWIPLimit() {
        new Team("Monkeys", 20, 0);
    }
}
