package com.strategygame.fighters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.strategygame.entities.Actor;
import com.strategygame.helpers.ActorAnimation;
import com.strategygame.helpers.AssetsManager;

public class Rogue extends Actor {

    public Rogue() {
        healthPoints = 9;
        movement = 5;
        attack = 2;
        defense = 1;
        attackRange = 1;
        speed = 5;

        actorAnimation = new ActorAnimation(AssetsManager.getInstance().ROGUE, 1, 2);
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
