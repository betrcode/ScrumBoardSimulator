package se.bettercode.scrum.team;

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

    public final StringProperty nameProperty() {
        return this.name;
    }

    public final void setName(String name) {
        this.name.setValue(name);
    }

    public String getName() {
        return name.getValue();
    }

    public final IntegerProperty velocityProperty() {
        return velocity;
    }

    public final void setVelocity(int velocity) {
        this.velocity.set(velocity);
    }

    @Override
    public String toString() {
        return "Team{" +
                "name=" + name.getValue() +
                ", velocity=" + velocity.get() +
                '}';
    }
}