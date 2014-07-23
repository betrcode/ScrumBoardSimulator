package se.bettercode.scrum;

public class Team {

    private String name = "No name";
    private int velocity = 0;

    public Team(String name, int velocity) {
        this.name = name;
        this.velocity = velocity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}
