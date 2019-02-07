package com.strategygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.strategygame.handlers.GameStateManager;
import com.strategygame.handlers.PlayerInputProcessor;
import com.strategygame.helpers.Constants;

public class StrategyGame extends ApplicationAdapter {

    private SpriteBatch sb;
    private OrthographicCamera camera;
    private OrthographicCamera hudCam;

    public static final float STEP = 1 / 60f;
    private float acum;

    private final int WIDTH = Constants.GAME_WIDTH;
    private final int HEIGHT = Constants.GAME_HEIGHT;

    private GameStateManager gsm;

    @Override
    public void create () {
        Gdx.input.setInputProcessor(new PlayerInputProcessor());

        sb = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        hudCam = new OrthographicCamera();
        hudCam.setToOrtho(false, WIDTH, HEIGHT);
        gsm = new GameStateManager(this);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0x2E / 255f, 0,  0xAE / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gsm.handleInput();
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render();
    }

    @Override
    public void dispose () {

    }

    public SpriteBatch getSpriteBatch() { return sb; }
    public OrthographicCamera getCamera() { return camera; }
    public OrthographicCamera getHUDCamera() { return hudCam; }
}
