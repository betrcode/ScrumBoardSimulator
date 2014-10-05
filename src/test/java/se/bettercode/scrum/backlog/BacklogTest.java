package se.bettercode.scrum.backlog;

import junit.framework.TestCase;
import se.bettercode.scrum.Story;
import se.bettercode.scrum.backlog.Backlog;

public class BacklogTest extends TestCase {

    Backlog backlog;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        backlog = new Backlog("Test");
        backlog.addStory(new Story(3));
    }

    public void testGetName() {
        assertEquals("Test", backlog.getName());
    }

    public void testAddStory() throws Exception {
        assertEquals(1, backlog.stories.size());
        backlog.addStory(new Story(5));
        assertEquals(2, backlog.stories.size());
    }

    public void testGetStory() throws Exception {
        Story story = backlog.getStory();
        assertNotNull(story);
    }

    public void testGetStoryWhenAllAreFinished() throws Exception {
        Story story = backlog.getStory();
        story.workOnStory(3, 1);
        story = backlog.getStory();
        assertNull(story);
    }

    public void testGetFinishedPoints() throws Exception {
        backlog.addStory(new Story(5));
        backlog.addStory(new Story(7));
        Story story = backlog.getStory();
        int work = story.getTotalPoints();
        story.workOnStory(work, 1);
        assertEquals(work, backlog.calculateFinishedPoints());
    }

    public void testGetFinishedStoriesCount() throws Exception {
        assertEquals(0, backlog.getFinishedStoriesCount());
        Story story = backlog.getStory();
        story.workOnStory(3, 1);
        assertEquals(1, backlog.getFinishedStoriesCount());
    }

    public void testGetTotalPoints() throws Exception {
        backlog.addStory(new Story(5));
        backlog.addStory(new Story(7));
        assertEquals(15, backlog.getTotalPoints());
    }

    public void testGetWorkInProgressPoints() throws Exception {
        backlog.addStory(new Story(5));
        backlog.addStory(new Story(7));
        Story story = backlog.getStory();
        int work = story.getTotalPoints() - 1;
        story.workOnStory(work, 1);
        assertEquals(work, backlog.getWorkInProgressPoints());
        assertEquals(0, backlog.calculateFinishedPoints());
    }

    //TODO: Cant use runDay in test because of IllegalStateException: Toolkit not initialized
    /*
    public void testAverageLeadTime() {
        backlog.addStory(new Story(8));
        backlog.addStory(new Story(10));
        backlog.addStory(new Story(5));
        int dailyBurn = 2;
        for (int day=1; day<=20; day++) {
            backlog.runDay(dailyBurn, day);
        }
        assertEquals(2.6, backlog.getAverageLeadTime());
    }
    */

    public void testToString() throws Exception {
        backlog.addStory(new Story(1));
        assertEquals("Backlog{stories=[Story{points=3, pointsDone=0, status=TODO}, Story{points=1, pointsDone=0, status=TODO}], average lead time=0.0}", backlog.toString());
    }
}