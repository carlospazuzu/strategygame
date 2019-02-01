package com.strategygame.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.strategygame.helpers.ActorAnimation;
import com.strategygame.helpers.AssetsManager;

public class Knight extends Actor{

    public Knight() {
        healthPoints = 10;
        movement = 3;
        attack = 3;
        defense = 1;
        attackRange = 1;
        speed = 2;

        actorAnimation = new ActorAnimation(AssetsManager.getInstance().KNIGHT, 1, 2);
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
