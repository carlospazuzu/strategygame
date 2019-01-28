package com.strategygame.handlers;

import com.strategygame.StrategyGame;
import com.strategygame.states.GamePlay;
import com.strategygame.states.GameState;

import java.util.Stack;


public class GameStateManager {

    private StrategyGame game;
    private Stack<GameState> gameStates;

    public static final int GAMEPLAY = 0xffff;

    public GameStateManager(StrategyGame game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(GAMEPLAY);
    }

    public StrategyGame game() { return game; }

    public void update(float delta) {
        gameStates.peek().update(delta);
    }

    public void render() {
        gameStates.peek().render();
    }

    public void setState(int state) {
        popState();
        pushState(state);
    }

    public void popState() {
        GameState gs = gameStates.pop();
        gs.dispose();
    }

    public void pushState(int state) {
        gameStates.push(getState(state));
    }

    private GameState getState(int state) {
        if (state == GAMEPLAY) return new GamePlay(this);
        return null;
    }


}
