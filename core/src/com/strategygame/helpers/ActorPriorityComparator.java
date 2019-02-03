package com.strategygame.helpers;

import com.strategygame.entities.Actor;

import java.util.Comparator;

public class ActorPriorityComparator implements Comparator<Actor> {
    @Override
    public int compare(Actor a1, Actor a2) {

        int spdA1 = a1.getSpeed();
        int spdA2 = a2.getSpeed();

        if (spdA1 == spdA2) {
            return 0;
        } else if (spdA1 < spdA2) {
            return -1;
        } else {
            return 1;
        }
    }
}
