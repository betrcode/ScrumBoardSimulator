package se.bettercode.scrum.gui;

import javafx.scene.media.AudioClip;
import se.bettercode.scrum.Story;

import static java.lang.Thread.sleep;

public class DingAudioClip {

    public static final String BELL_WAV = "/bell.wav";
    public static final int MIN_SLEEP_MILLIS = 250;
    public static final int SLEEP_INCREASE_MILLIS = 20;
    private final AudioClip ding;

    public DingAudioClip() {
        this.ding = new AudioClip(getClass().getResource(BELL_WAV).toString());
    }

    protected void playIfDone(Story story) {
        if (story.getStatus() == Story.StoryState.FINISHED) {
            for (int i=0; i < story.getTotalPoints(); i++) {
                ding.stop();
                ding.play(1.0);
                sleeper(i);
            }
        }
    }

    private void sleeper(int sleepIncreaseMultiplier) {
        try {
            sleep(MIN_SLEEP_MILLIS + sleepIncreaseMultiplier * SLEEP_INCREASE_MILLIS);
        } catch (InterruptedException e) {

        }
    }
}
