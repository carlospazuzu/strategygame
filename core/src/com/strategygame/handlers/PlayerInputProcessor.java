package com.strategygame.handlers;

import com.badlogic.gdx.InputAdapter;

public class PlayerInputProcessor extends InputAdapter {

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        PlayerInput.isPressed = true;
        PlayerInput.isReleased = false;
        PlayerInput.screenX = screenX;
        PlayerInput.screenY = screenY;
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
