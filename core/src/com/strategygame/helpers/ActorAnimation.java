package com.strategygame.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ActorAnimation{

    private Animation<TextureRegion> animation;
    private float stateTime;

    public ActorAnimation(Texture spriteSheet, int rows, int cols) {
        TextureRegion[][] textureRegions =
                TextureRegion.split(spriteSheet,
                                    spriteSheet.getWidth() / cols,
                                             spriteSheet.getHeight() / rows);

        TextureRegion[] frames = new TextureRegion[rows * cols];

        int index = 0;
        for (int i = 0; i < rows; i ++) {
            for (int j = 0; j < cols; j ++) {
                frames[index++] = textureRegions[i][j];
            }
        }

        animation = new Animation<TextureRegion>(0.5f, frames);
        stateTime = 0f;
    }

    public void update(float delta) {
        stateTime += delta;
    }

    public TextureRegion getCurrentFrame() {
        return animation.getKeyFrame(stateTime, true);
    }
}
