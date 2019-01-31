package com.strategygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.strategygame.handlers.GameStateManager;

public class GamePlay extends GameState {

    // BitmapFont font = new BitmapFont();

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    Animation<TextureRegion> knightAnimation;
    Texture knightSpriteSheet;
    SpriteBatch spriteBatch;

    float stateTime;

    private static final int FRAME_COLS = 2, FRAME_ROWS = 1;

    public GamePlay(GameStateManager gsm) {
        super(gsm);

        tiledMap = new TmxMapLoader().load("tmx/forest.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        knightSpriteSheet = new Texture(Gdx.files.internal("fighters/knight.png"));
        TextureRegion[][] tmp =
                TextureRegion.split(knightSpriteSheet,
                        knightSpriteSheet.getWidth() / FRAME_COLS,
                        knightSpriteSheet.getHeight() / FRAME_ROWS);
        TextureRegion[] knightFrames = new TextureRegion[FRAME_ROWS * FRAME_COLS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i ++)
        {
            for (int j = 0; j < FRAME_COLS; j ++)
            {
                knightFrames[index++] = tmp[i][j];
            }
        }

        knightAnimation = new Animation<TextureRegion>(0.5f, knightFrames);

        spriteBatch = new SpriteBatch();
        stateTime = 0f;

        Gdx.app.log("SCREEN WIDTH", "" + camera.viewportWidth);
        Gdx.app.log("SCREEN HEIGHT", "" + camera.viewportHeight);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float delta) {
        stateTime += Gdx.graphics.getDeltaTime();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        TextureRegion currentFrame = knightAnimation.getKeyFrame(stateTime, true);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame,
                (currentFrame.getRegionWidth() * 2 * 11) - 24, (currentFrame.getRegionWidth() * 2 * 5),
                currentFrame.getRegionWidth() * 3, currentFrame.getRegionHeight() * 3);

        spriteBatch.end();

    }

    @Override
    public void dispose() {

    }
}
