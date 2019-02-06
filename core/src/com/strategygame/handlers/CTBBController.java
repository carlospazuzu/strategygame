package com.strategygame.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.strategygame.StrategyGame;
import com.strategygame.entities.Actor;
import com.strategygame.guieffects.GeneralPlayerSelection;
import com.strategygame.helpers.ActorPriorityComparator;

import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class CTBBController {

    private Queue<Actor> actorQueue;
    private HUDCameraController hudCameraController;

    private GeneralPlayerSelection generalPlayerSelection;

    public CTBBController(List<Actor> team, OrthographicCamera camera, OrthographicCamera hudCamera, StrategyGame game) {
        /*
        Collections.sort(team, new ActorPriorityComparator());
        Collections.reverse(team);

        for (Actor actor : team) {
            actorQueue.add(actor);
        }
        */

        generalPlayerSelection = new GeneralPlayerSelection(hudCamera, game);

    }

    public void handleInput() {
        generalPlayerSelection.handleInput();
    }

    public void update(float delta) {
        generalPlayerSelection.update(delta);
    }

    public void render() {
        generalPlayerSelection.render();
    }
}
