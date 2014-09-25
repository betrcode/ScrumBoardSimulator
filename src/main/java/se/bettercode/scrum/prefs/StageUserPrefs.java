package se.bettercode.scrum.prefs;


import javafx.stage.Stage;

import java.util.prefs.Preferences;

public class StageUserPrefs {

    private final Stage stage;
    private final Preferences userPrefs = Preferences.userNodeForPackage(getClass());

    public StageUserPrefs(Stage stage) {
        this.stage = stage;
    }

    public void load() {
        stage.setX(userPrefs.getDouble("stage.x", 100));
        stage.setY(userPrefs.getDouble("stage.x", 100));
        stage.setWidth(userPrefs.getDouble("stage.width", 800));
        stage.setHeight(userPrefs.getDouble("stage.height", 600));

    }

    public void save() {
        userPrefs.putDouble("stage.x", stage.getX());
        userPrefs.putDouble("stage.y", stage.getY());
        userPrefs.putDouble("stage.width", stage.getWidth());
        userPrefs.putDouble("stage.height", stage.getHeight());
    }
}
