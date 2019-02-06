package com.strategygame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class GameImage {

    private float positionX;
    private float positionY;

    private Texture image;

    public GameImage(Texture img) {
        this(img, 0, 0);
    }

    public GameImage(Texture img, int x, int y) {
        this.image = img;
        positionX = x;
        positionY = y;
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
