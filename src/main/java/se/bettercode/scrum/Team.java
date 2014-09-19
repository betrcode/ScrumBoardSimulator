package se.bettercode.scrum;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Team {

    private StringProperty name;
    private IntegerProperty velocity;

    public Team(String name, int velocity) {
        this.name = new SimpleStringProperty(name);
        this.velocity = new SimpleIntegerProperty(velocity);
    }

    public final StringProperty getName() {
        return this.name;
    }

    public final void setName(String name) {
        this.name.setValue(name);
    }

    public final IntegerProperty getVelocity() {
        return velocity;
    }

    public final void setVelocity(int velocity) {
        this.velocity.set(velocity);
    }
}
