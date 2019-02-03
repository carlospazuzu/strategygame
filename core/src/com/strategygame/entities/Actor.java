package com.strategygame.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.strategygame.helpers.ActorAnimation;

public abstract class Actor {
    // world coordinates
    protected int positionX;
    protected int positionY;

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public ActorAnimation getActorAnimation() {
        return actorAnimation;
    }

    public void setActorAnimation(ActorAnimation actorAnimation) {
        this.actorAnimation = actorAnimation;
    }

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
