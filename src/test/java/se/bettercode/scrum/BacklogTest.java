package se.bettercode.scrum;

import junit.framework.TestCase;

public class BacklogTest extends TestCase {

    Backlog backlog;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        backlog = new Backlog();
        backlog.addStory(new Story(3));
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
        story.workOnStory(3);
        story = backlog.getStory();
        assertNull(story);
    }

    public void testGetFinishedPoints() throws Exception {
        backlog.addStory(new Story(5));
        backlog.addStory(new Story(7));
        Story story = backlog.getStory();
        int work = story.getTotalPoints();
        story.workOnStory(work);
        assertEquals(work, backlog.calculateFinishedPoints());
    }

    public void testGetFinishedStoriesCount() throws Exception {
        assertEquals(0, backlog.getFinishedStoriesCount());
        Story story = backlog.getStory();
        story.workOnStory(3);
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
        story.workOnStory(work);
        assertEquals(work, backlog.getWorkInProgressPoints());
        assertEquals(0, backlog.calculateFinishedPoints());
    }

    public void testToString() throws Exception {
        backlog.addStory(new Story(1));
        assertEquals("Backlog{stories=[Story{points=3, pointsDone=0, status=TODO}, Story{points=1, pointsDone=0, status=TODO}]}", backlog.toString());
    }
}