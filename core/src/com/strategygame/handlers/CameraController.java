package com.strategygame.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.strategygame.StrategyGame;

public class CameraController {

    private OrthographicCamera camera;
    private TiledMap tiledMap;
    private StrategyGame game;

    private boolean mustMove = false;

    private int destinationX = 0;
    private int destinationY = 0;

    private float movingSpeed = 1f;


    public CameraController(OrthographicCamera camera, TiledMap tiledMap, StrategyGame game) {
        this.camera = camera;
        this.tiledMap = tiledMap;
        this.game = game;
    }

    public void update(float delta) {

        float xMove = 0, yMove = 0;

        if (mustMove) {
            if (camera.position.x <= this.destinationX) {
                xMove = movingSpeed;
            } else if (camera.position.x >= this.destinationX) {
                xMove = -movingSpeed;
            }
            if (camera.position.y <= this.destinationY) {
                yMove = movingSpeed;

            } else  if (camera.position.y >= this.destinationY) {
                yMove = -movingSpeed;
            }

            camera.translate(xMove, yMove);
            camera.update();

            // keepInsideMapBounds();
            if (camera.position.x >= this.destinationX) this.mustMove = false;
            if (camera.position.y >= this.destinationY) this.mustMove = false;
        }

    }

    public void setMovingSpeed(float speed) {
        this.movingSpeed = speed;
    }

    private void startMoving() {
        this.mustMove = true;
    }

    public void moveTo(int x, int y) {
        this.destinationX = x * 16;
        this.destinationY = y * 16;
        startMoving();
    }

    private void keepInsideMapBounds() {
        if (camera.position.x <= 120) {
            camera.position.x = 120;
            mustMove = false;
        } else if (camera.position.y <= 80) {
            camera.position.y = 80;
            mustMove = false;
        } else if (camera.position.y >= (Integer) tiledMap.getProperties().get("height") - game.getCamera().viewportHeight) {
            camera.position.y = (Integer) tiledMap.getProperties().get("height") - game.getCamera().viewportHeight;
            mustMove = false;
        } else if (camera.position.y >= (Integer) tiledMap.getProperties().get("width") - game.getCamera().viewportWidth) {
            camera.position.x = (Integer) tiledMap.getProperties().get("width") - game.getCamera().viewportWidth;
            mustMove = false;
        }
    }



}
