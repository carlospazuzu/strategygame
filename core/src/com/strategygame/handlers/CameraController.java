package com.strategygame.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.strategygame.StrategyGame;
import com.strategygame.decisions.CameraSelection;
import com.strategygame.entities.Actor;

public class CameraController {

    private OrthographicCamera camera;
    private TiledMap tiledMap;
    private StrategyGame game;

    private boolean mustMove = false;

    // 120x80 is the initial position of the camera which is based on the half of width and height of the screen
    private int destinationX = 0; // 120;
    private int destinationY = 0; // 80;

    private float movingSpeed = 1f;

    private int currentPositionX = 0;
    private int currentPositionY = 0;

    public CameraController(OrthographicCamera camera, TiledMap tiledMap, StrategyGame game) {
        this.camera = camera;
        this.tiledMap = tiledMap;
        this.game = game;
    }

    public void update(float delta) {
        move(delta);
        currentPositionX = (int) camera.position.x / 16;
        currentPositionY = (int) camera.position.y / 16;
    }

    private void move(float delta) {

        float xMove = 0, yMove = 0;

        if (mustMove) {
            if (camera.position.x < this.destinationX) {
                xMove = movingSpeed;
            } else if (camera.position.x > this.destinationX) {
                xMove = -movingSpeed;
            }
            if (camera.position.y < this.destinationY) {
                yMove = movingSpeed;

            } else if (camera.position.y > this.destinationY) {
                yMove = -movingSpeed;
            }

            camera.translate(xMove, yMove);
            camera.update();

            if (reachedDestination()) {
                this.mustMove = false;
                CameraSelection.canTouch = true;
            }

            keepInsideMapBounds();
        }

    }

    public void setMovingSpeed(float speed) {
        this.movingSpeed = speed;
    }

    public int getCurrentPositionX() {
        return currentPositionX;
    }

    public int getCurrentPositionY() {
        return currentPositionY;
    }

    private boolean reachedDestination() {
        return camera.position.x == (this.destinationX * 16) + 120 && camera.position.y == (this.destinationY * 16) + 80;
    }

    private void startMoving() {
        this.mustMove = true;
    }

    public void moveToChar(Actor target) {
        moveTo(target.getPositionX() - 7, target.getPositionY() - 3); // subtractions are made to keep target character in the middle of the screen
    }

    public void moveTo(int x, int y) {
        this.destinationX = (x * 16) + 120;
        this.destinationY = (y * 16) + 80;
        if (!mustMove) startMoving();
    }

    public void moveX(int x) {
        this.destinationX += x; // (x * 16) + 120;
        if (destinationX > getMapWidth()) destinationX = getMapWidth();

        if (!mustMove) startMoving();
    }

    public void moveY(int y) {
        this.destinationY += y; // (y * 16) + 80;
        if (destinationY > getMapHeight()) destinationY = getMapHeight();

        startMoving();
    }

    public void moveBoth(int x, int y) {
        moveX(x);
        moveY(y);
    }

    private int getNumTilesX() {
        return (Integer) tiledMap.getProperties().get("width") / (Integer) tiledMap.getProperties().get("tilewidth");
    }

    private int getNumTilesY() {
        return (Integer) tiledMap.getProperties().get("height") / (Integer) tiledMap.getProperties().get("tileheight");
    }

    private int getMapHeight() {
        return (Integer) tiledMap.getProperties().get("height") * (Integer) tiledMap.getProperties().get("tileheight");
    }

    private int getMapWidth() {
        return (Integer) tiledMap.getProperties().get("width") * (Integer) tiledMap.getProperties().get("tilewidth");
    }

    private void keepInsideMapBounds() {
        if (camera.position.x <= 120 + 1) {
            camera.position.x = 120 + 1;
        }
        if (camera.position.y <= 80 + 1) {
            camera.position.y = 80 + 1;
        }
        if (camera.position.y >= getMapHeight() - 85) {
            camera.position.y = getMapHeight() - 85;
        }
        if (camera.position.x >= getMapWidth() - 125) {
            camera.position.x = getMapWidth() - 125;
        }

    }
}
