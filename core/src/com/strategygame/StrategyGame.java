package com.strategygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.strategygame.handlers.GameStateManager;

public class StrategyGame extends ApplicationAdapter {

    private SpriteBatch sb;
    private OrthographicCamera camera;
    private OrthographicCamera hudCam;

    public static final float STEP = 1 / 60f;
    private float acum;

    public static final int WIDTH = 240; // 320
    public static final int HEIGHT = 160; // 240

    private GameStateManager gsm;

    @Override
    public void create () {

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

        acum += Gdx.graphics.getDeltaTime();
        while (acum >= STEP) {
            acum -= STEP;
            gsm.update(STEP);
            gsm.render();
        }
    }

    @Override
    public void dispose () {

    }

    public SpriteBatch getSpriteBatch() { return sb; }
    public OrthographicCamera getCamera() { return camera; }
    public OrthographicCamera getHUDCamera() { return hudCam; }
}
