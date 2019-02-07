package com.strategygame.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.strategygame.StrategyGame;
import com.strategygame.decisions.CommandSelection;
import com.strategygame.decisions.PlayerDecisionStateMachine;
import com.strategygame.entities.Actor;
import com.strategygame.decisions.GeneralPlayerSelection;

import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class CTBBController {

    private Queue<Actor> actorQueue;
    private HUDCameraController hudCameraController;

    private OrthographicCamera hudCamera;

    private Stack<PlayerDecisionStateMachine> stateMachines;

    public enum PlayerDecision {GENERAL, COMMANDS, CAMERA};

    private PlayerDecision currentDecisionState;

    private StrategyGame game;

    public CTBBController(List<Actor> team, OrthographicCamera camera, StrategyGame game) {
        /*
        Collections.sort(team, new ActorPriorityComparator());
        Collections.reverse(team);

        for (Actor actor : team) {
            actorQueue.add(actor);
        }
        */

        stateMachines = new Stack<PlayerDecisionStateMachine>();

        this.game = game;
        this.hudCamera = game.getHUDCamera();

        currentDecisionState = PlayerDecision.GENERAL;

        pushState(PlayerDecision.GENERAL);
    }

    public StrategyGame getGame() {
        return game;
    }

    public PlayerDecision getCurrentDecisionState() {
        return currentDecisionState;
    }

    public void changeState(PlayerDecision decision) {
        popCurrentState();
        pushState(decision);
        currentDecisionState = decision;
    }

    private void popCurrentState() {
        stateMachines.pop().dispose();
    }

    private void pushState(PlayerDecision decision) {
        stateMachines.push(getState(decision));
    }

    private PlayerDecisionStateMachine getState(PlayerDecision decision) {
        switch (decision) {
            case GENERAL:
                return new GeneralPlayerSelection(this);
            case COMMANDS:
                return new CommandSelection(this);
            case CAMERA:
                break;
        }

        return null;
    }

    public void handleInput() {
        stateMachines.peek().handleInput();
    }

    public void update(float delta) {
        stateMachines.peek().update(delta);
    }

    public void render() {
        stateMachines.peek().render();
    }
}
