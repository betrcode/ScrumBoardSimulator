package se.bettercode.scrum.backlog;


import java.util.ArrayList;

class BacklogBurnup extends ArrayList<BurnupDay> {

    void addDay(BurnupDay burnupDay) {
        add(burnupDay);
    }

}
