package se.bettercode.scrum;

import junit.framework.TestCase;

public class StoryTest extends TestCase {

    Story story;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        story = new Story(5);
    }

    public void testGetTotalPoints() throws Exception {
        assertEquals(5, story.getTotalPoints());
    }

    public void testGetPointsDone() throws Exception {
        assertEquals(0, story.getPointsDone());
    }

    public void testGetStatus() throws Exception {
        assertEquals(Story.StoryState.TODO, story.getStatus());
    }

    public void testWorkOnStoryWhenOnlyPartiallyCompleted() throws Exception {
        int remainingPoints = story.workOnStory(3, 1);
        assertEquals(0, remainingPoints);
        assertEquals(Story.StoryState.STARTED, story.getStatus());
        assertEquals(3, story.getPointsDone());
        assertEquals(2, story.getRemainingPoints());
    }

    public void testWorkOnStoryUntilExactCompletion() throws Exception {
        int remainingPoints = story.workOnStory(5, 1);
        assertEquals(0, remainingPoints);
        assertEquals(Story.StoryState.FINISHED, story.getStatus());
        assertEquals(5, story.getPointsDone());
        assertEquals(story.getTotalPoints(), story.getPointsDone());
        assertEquals(0, story.getRemainingPoints());
    }

    public void testWorkOnStoryUntilOverCompleted() throws Exception {
        int remainingPoints = story.workOnStory(7, 1);
        assertEquals(Story.StoryState.FINISHED, story.getStatus());
        assertEquals(5, story.getPointsDone());
        assertEquals(story.getTotalPoints(), story.getPointsDone());
        assertEquals(0, story.getRemainingPoints());
        assertEquals(2, remainingPoints);
    }

    public void testWorkOnStorySeveralTimes() throws Exception {
        story.workOnStory(3, 1);
        story.workOnStory(1, 2);
        assertEquals(Story.StoryState.STARTED, story.getStatus());
        assertEquals(4, story.getPointsDone());
        assertEquals(1, story.getRemainingPoints());
    }

    public void testLeadTime() {
        final int POINTS_PER_DAY = 2;
        for (int day=1; day<=3; day++) {
            story.workOnStory(POINTS_PER_DAY, day);
        }
        assertEquals(Story.StoryState.FINISHED, story.getStatus());
        assertEquals(2.0, story.getLeadTime());
    }

    public void testGetRemainingPoints() throws Exception {
        assertEquals(5, story.getRemainingPoints());
    }

    public void testToString() throws Exception {
        assertEquals("Story{points=5, pointsDone=0, status=TODO}", story.toString());
    }

    public void testStoryWithNegativePoints() {
        Throwable e = null;

        try {
            story = new Story(-1);
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof AssertionError);
    }

}
