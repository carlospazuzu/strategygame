package com.strategygame.helpers;

import com.badlogic.gdx.math.Vector2;
import com.strategygame.entities.GameImage;

public class Tween {
    
    public static void positionX(GameImage image, float destinationX, float speed, float delta) {
        if (image.getPositionX() < destinationX) {
            image.setPositionX(image.getPositionX() + (speed * delta));

            if (image.getPositionX() > destinationX) Tween.forceDesiredPositionX(image, destinationX);
        }
        else if (image.getPositionX() > destinationX) {
            image.setPositionX(image.getPositionX() - (speed * delta));

            if (image.getPositionX() < destinationX) Tween.forceDesiredPositionX(image, destinationX);
        }
    }

    public static void positionY(GameImage image, float destinationY, float speed, float delta) {
        if (image.getPositionX() < destinationY) {
            image.setPositionX(image.getPositionX() + (speed * delta));

            if (image.getPositionX() > destinationY) Tween.forceDesiredPositionY(image, destinationY);
        } else if (image.getPositionX() > destinationY) {
            image.setPositionX(image.getPositionX() - (speed * delta));

            if (image.getPositionX() < destinationY) Tween.forceDesiredPositionY(image, destinationY);
        }
    }

    public static void position(GameImage image, Vector2 position, float speed, float delta) {
        positionX(image, position.x, speed, delta);
        positionY(image, position.y, speed, delta);
    }

    public static void warningArrowX(GameImage arrow, float initialPositionX, float finalPositionX, float speed, float delta) {
        if (arrow.getPositionX() < finalPositionX) {
            arrow.setPositionX(arrow.getPositionX() + (speed * delta));

            if (arrow.getPositionX() >= finalPositionX) arrow.setPositionX(initialPositionX);
        } else if (arrow.getPositionX() > finalPositionX) {
            arrow.setPositionX(arrow.getPositionX() - (speed * delta));

            if (arrow.getPositionX() <= finalPositionX) arrow.setPositionX(initialPositionX);
        }
    }

    public static void warningArrowY(GameImage arrow, float initialPositionY, float finalPositionY, float speed, float delta) {
        if (arrow.getPositionY() < finalPositionY) {
            arrow.setPositionY(arrow.getPositionY() + (speed * delta));

            if (arrow.getPositionY() >= finalPositionY) arrow.setPositionY(initialPositionY);
        } else if (arrow.getPositionY() > finalPositionY) {
            arrow.setPositionY(arrow.getPositionY() - (speed * delta));

            if (arrow.getPositionY() <= finalPositionY) arrow.setPositionY(initialPositionY);
        }
    }

    private boolean reachedDestinyX(float actualPositionX, float destinationPositionX) {
        return actualPositionX == destinationPositionX;
    }

    private boolean reachedDestinyY(float actualPositionY, float destinationPositionY) {
        return actualPositionY == destinationPositionY;
    }

    private static void forceDesiredPositionX(GameImage image, float desiredPositionX) {
        image.setPositionX(desiredPositionX);
    }

    private static void forceDesiredPositionY(GameImage image, float desiredPositionY) {
        image.setPositionY(desiredPositionY);
    }

    private static void forceDesiredPosition(GameImage image, Vector2 desiredPosition) {
        image.setPosition(desiredPosition);
    }
}
