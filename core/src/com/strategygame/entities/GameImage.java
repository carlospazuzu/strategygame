package com.strategygame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.strategygame.handlers.PlayerInput;
import com.strategygame.helpers.Constants;

public class GameImage {

    private float positionX;
    private float positionY;

    private Texture image;

    private boolean canBeTouched;

    public GameImage(Texture img) {
        this(img, 0, 0);
    }

    public GameImage(Texture img, int x, int y) {
        this.image = img;
        positionX = x;
        positionY = y;

        canBeTouched = true;
    }

    public void setCanBeTouched(boolean decision) {
        canBeTouched = decision;
    }

    public boolean hasJustBeenTouched() {
        return PlayerInput.isPressed && canBeTouched &&
                new Rectangle(
                positionX,
                positionY,
                positionX + this.getWidth(),
                positionY + this.getHeight())
                .contains(new Vector2(
                        PlayerInput.screenX / (float) (Gdx.graphics.getWidth() / Constants.GAME_WIDTH),
                        Constants.GAME_HEIGHT - (PlayerInput.screenY / (float) (Gdx.graphics.getHeight() / Constants.GAME_HEIGHT))));
    }

    public int getWidth() {
        return getImage().getWidth();
    }

    public int getHeight() {
        return getImage().getHeight();
    }

    public void setPosition(Vector2 position) {
        positionX = position.x;
        positionY = position.y;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }
}
