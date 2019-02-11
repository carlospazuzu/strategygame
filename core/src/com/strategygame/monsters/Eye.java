package com.strategygame.monsters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.strategygame.entities.Actor;
import com.strategygame.helpers.ActorAnimation;
import com.strategygame.helpers.AssetsManager;

public class Eye extends Actor {

    public Eye() {
        healthPoints = 4;
        attack = 4;
        attackRange = 4;
        defense = 0;
        movement = 2;
        speed = 3;

        actorAnimation = new ActorAnimation(AssetsManager.getInstance().EYE, 1, 2);
    }

    @Override
    public void kill() {

    }

    @Override
    public void moveTo(int positionX, int positionY) {

    }

    @Override
    public void attack() {

    }

    @Override
    public void render(SpriteBatch spriteBatch, int x, int y) {
        spriteBatch.draw(actorAnimation.getCurrentFrame(), 16 * x, 16 * y, 16, 16);
    }
}
