package se.bettercode.scrum.team;

import java.util.HashMap;

public class SelectableTeams {

    private HashMap<String, Team> teamHashMap = new HashMap<>();

    public SelectableTeams() {
        Team cobras = new CobraTeam();
        Team smurfs = new SmurfTeam();
        teamHashMap.put(cobras.getName(), cobras);
        teamHashMap.put(smurfs.getName(), smurfs);
    }

    public Team get(String teamName) {
        return teamHashMap.get(teamName);
    }

    public String[] getNames() {
        return teamHashMap.keySet().toArray(new String[teamHashMap.size()]);
    }
}
