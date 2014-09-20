package se.bettercode.scrum;

import junit.framework.TestCase;

public class TeamTest extends TestCase {

    public void testToString() {
        Team team = new Team("Dingos", 17);
        assertEquals("Team{name=Dingos, velocity=17}", team.toString());

    }
}
