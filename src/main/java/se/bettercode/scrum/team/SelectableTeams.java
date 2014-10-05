package se.bettercode.scrum.team;

import se.bettercode.scrum.Selectable;

public class SelectableTeams extends Selectable<Team> {

    public SelectableTeams() {
        Team cobras = new CobraTeam();
        Team smurfs = new SmurfTeam();
        put(cobras.getName(), cobras);
        put(smurfs.getName(), smurfs);
    }

}
