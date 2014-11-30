package se.bettercode.scrum.team;

import se.bettercode.utils.Selectable;

public class SelectableTeams extends Selectable<TeamImpl> {

    public SelectableTeams() {
        TeamImpl cobras = new CobraTeam();
        TeamImpl smurfs = new SmurfTeam();
        put(cobras.getName(), cobras);
        put(smurfs.getName(), smurfs);
    }

}
