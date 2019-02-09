package com.strategygame.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.strategygame.helpers.Constants;

public class PlayerInputProcessor extends InputAdapter {

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        PlayerInput.isPressed = true;
        PlayerInput.isReleased = false;
        PlayerInput.screenX = screenX / (float) (Gdx.graphics.getWidth() / Constants.GAME_WIDTH);
        PlayerInput.screenY = screenY / (float) (Gdx.graphics.getHeight() / Constants.GAME_HEIGHT);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        PlayerInput.isPressed = false;
        PlayerInput.isReleased = true;
        PlayerInput.screenX = screenX;
        PlayerInput.screenY = screenY;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }
}
