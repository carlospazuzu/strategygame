package com.strategygame.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.strategygame.StrategyGame;
import com.strategygame.entities.Actor;

public class CameraController {

    private OrthographicCamera camera;
    private TiledMap tiledMap;
    private StrategyGame game;

    private boolean mustMove = false;

    // 120x80 is the initial position of the camera which is based on the half of width and height of the screen
    private int destinationX = 120;
    private int destinationY = 80;

    private float movingSpeed = 1f;


    public CameraController(OrthographicCamera camera, TiledMap tiledMap, StrategyGame game) {
        this.camera = camera;
        this.tiledMap = tiledMap;
        this.game = game;
    }

    public void update(float delta) {
        move(delta);
    }

    public void move(float delta) {

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
            }

            keepInsideMapBounds();
        }

    }

    public void setMovingSpeed(float speed) {
        this.movingSpeed = speed;
    }

    private boolean reachedDestination() {
        return camera.position.x == this.destinationX && camera.position.y == this.destinationY;
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
        startMoving();
    }

    private int getMapHeight() {
        return (Integer) tiledMap.getProperties().get("height") * (Integer) tiledMap.getProperties().get("tileheight");
    }

    private int getMapWidth() {
        return (Integer) tiledMap.getProperties().get("width") * (Integer) tiledMap.getProperties().get("tilewidth");
    }

    private void keepInsideMapBounds() {
        if (camera.position.x <= 120) {
            camera.position.x = 120;
            mustMove = false;
        } else if (camera.position.y <= 80) {
            camera.position.y = 80;
            mustMove = false;
        } else if (camera.position.y >= getMapHeight() - 80) {
            camera.position.y = getMapHeight() - 80;
            mustMove = false;
        } else if (camera.position.x >= getMapWidth() - 120) {
            camera.position.x = getMapWidth() - 120;
            mustMove = false;
        }
    }
}
