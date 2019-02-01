package com.strategygame.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.strategygame.helpers.ActorAnimation;

public abstract class Actor {
    // world coordinates
    protected int positionX;
    protected int positionY;

    // movement the actor may move
    protected int movement;

    // range of the attack
    protected int attackRange;

    // actor stats
    protected int healthPoints;
    protected int attack;
    protected int defense;
    protected int speed;

    protected ActorAnimation actorAnimation;

    public Actor() {}

    public void takeDamage(int damage) {
        healthPoints -= damage;

        if (healthPoints <= 0) kill();
    }

    public abstract void kill();
    public abstract void moveTo(int positionX, int positionY);
    public abstract void attack();

    public void update(float delta) {
        actorAnimation.update(delta);
    }

    public abstract void render(SpriteBatch spriteBatch, int x, int y);

}
