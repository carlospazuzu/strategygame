package com.strategygame.states;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.strategygame.handlers.GameStateManager;
import com.strategygame.StrategyGame;

public abstract class GameState {

    protected StrategyGame game;
    protected GameStateManager gsm;
    protected SpriteBatch sb;
    protected OrthographicCamera camera;
    protected OrthographicCamera hudCamera;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
        game = gsm.game();
        sb = game.getSpriteBatch();
        camera = game.getCamera();
        hudCamera = game.getHUDCamera();
    }

    public abstract void handleInput();
    public abstract void update(float delta);
    public abstract void render();
    public abstract void dispose();

}
