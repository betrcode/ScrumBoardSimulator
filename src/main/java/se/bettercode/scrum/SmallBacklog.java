package se.bettercode.scrum;


public class SmallBacklog extends Backlog {

    public SmallBacklog() {
        addStory(new Story(3));
        addStory(new Story(5));
        addStory(new Story(8));
        addStory(new Story(5));
        addStory(new Story(1));
    }
}
