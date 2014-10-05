package se.bettercode.scrum.backlog;

import java.util.HashMap;

public class SelectableBacklogs {

    private HashMap<String, Backlog> backlogHashMap = new HashMap<>();

    public SelectableBacklogs() {
        Backlog smallBacklog = new SmallBacklog();
        Backlog wellSlicedBacklog = new WellSlicedBacklog();
        backlogHashMap.put(smallBacklog.getName(), smallBacklog);
        backlogHashMap.put(wellSlicedBacklog.getName(), wellSlicedBacklog);
    }

    public Backlog get(String backlogName) {
        return backlogHashMap.get(backlogName);
    }

    public String[] getNames() {
        return backlogHashMap.keySet().toArray(new String[backlogHashMap.size()]);
    }

}
