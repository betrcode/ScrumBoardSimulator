package se.bettercode.scrum.backlog;

import junit.framework.TestCase;
import se.bettercode.scrum.Story;

import java.util.NoSuchElementException;

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
        boolean exceptionHit = false;
        try {
            story = backlog.getStory();
        } catch (NoSuchElementException e) {
            exceptionHit = true;
        }
        assertTrue(exceptionHit);
    }

    public void testGetFinishedPoints() throws Exception {
        backlog.addStory(new Story(5));
        backlog.addStory(new Story(7));
        Story story = backlog.getStory();
        int work = story.getTotalPoints();
        story.workOnStory(work, 1);
        assertEquals(work, backlog.getFinishedPoints());
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
        assertEquals(0, backlog.getFinishedPoints());
    }

    public void testIsFinished() {
        backlog = new Backlog("Mah backlog");
        Story story1 = new Story(1);
        Story story2 = new Story(2);
        Story story3 = new Story(3);
        backlog.addStory(story1);
        backlog.addStory(story2);
        backlog.addStory(story3);
        story1.workOnStory(1, 1);
        story2.workOnStory(2, 1);
        assertEquals(false, backlog.isFinished());
        story3.workOnStory(3, 1);
        assertEquals(true, backlog.isFinished());
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