package com.strategygame.handlers;

import com.strategygame.entities.Actor;
import com.strategygame.helpers.ActorPriorityComparator;

import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class CTBBController {

    private Queue<Actor> actorQueue;

    public CTBBController(List<Actor> team) {
        Collections.sort(team, new ActorPriorityComparator());
        Collections.reverse(team);

        for (Actor actor : team) {
            actorQueue.add(actor);
        }
    }

    public void update(float delta) {

    }
}
