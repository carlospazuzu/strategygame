package com.strategygame.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUDCameraController {

    private OrthographicCamera hudCamera;
    private SpriteBatch spriteBatch;

    public HUDCameraController(OrthographicCamera hudCamera) {
        this.hudCamera = hudCamera;
        spriteBatch = new SpriteBatch();
    }

    public void update(float delta) {

    }

    public void render() {

    }
}
