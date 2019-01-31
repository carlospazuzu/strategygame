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

    Animation<TextureRegion> knightAnimation, mageAnimation, rogueAnimation;
    Texture knightSpriteSheet, mageSpriteSheet, rogueSpriteSheet;
    SpriteBatch spriteBatch;

    float stateTime;

    private static final int FRAME_COLS = 2, FRAME_ROWS = 1;

    public GamePlay(GameStateManager gsm) {
        super(gsm);

        tiledMap = new TmxMapLoader().load("tmx/forest.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        knightSpriteSheet = new Texture(Gdx.files.internal("fighters/knight.png"));
        mageSpriteSheet = new Texture(Gdx.files.internal("fighters/mage.png"));
        rogueSpriteSheet = new Texture(Gdx.files.internal("fighters/rogue.png"));

        TextureRegion[][] tmp =
                TextureRegion.split(knightSpriteSheet,
                        knightSpriteSheet.getWidth() / FRAME_COLS,
                        knightSpriteSheet.getHeight() / FRAME_ROWS);
        TextureRegion[][] tmp2 =
                TextureRegion.split(mageSpriteSheet,
                        mageSpriteSheet.getWidth() / FRAME_COLS,
                        mageSpriteSheet.getHeight() / FRAME_ROWS);
        TextureRegion[][] tmp3 =
                TextureRegion.split(rogueSpriteSheet,
                        rogueSpriteSheet.getWidth() / FRAME_COLS,
                        rogueSpriteSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] knightFrames = new TextureRegion[FRAME_ROWS * FRAME_COLS];
        TextureRegion[] mageFrames = new TextureRegion[FRAME_ROWS * FRAME_COLS];
        TextureRegion[] rogueFrames = new TextureRegion[FRAME_ROWS * FRAME_COLS];

        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i ++)
        {
            for (int j = 0; j < FRAME_COLS; j ++)
            {
                knightFrames[index] = tmp[i][j];
                mageFrames[index] = tmp2[i][j];
                rogueFrames[index++] = tmp3[i][j];
            }
        }

        knightAnimation = new Animation<TextureRegion>(0.5f, knightFrames);
        mageAnimation = new Animation<TextureRegion>(0.5f, mageFrames);
        rogueAnimation = new Animation<TextureRegion>(0.5f, rogueFrames);

        spriteBatch = new SpriteBatch();
        stateTime = 0f;

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
        TextureRegion currentMageFrame = mageAnimation.getKeyFrame(stateTime, true);
        TextureRegion currentRogueFrame = rogueAnimation.getKeyFrame(stateTime, true);

        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
        spriteBatch.draw(currentFrame,
                10 * 16, 16 * 4,
                currentFrame.getRegionWidth() / 2,  currentFrame.getRegionHeight() / 2);
        spriteBatch.draw(currentMageFrame,
                11 * 16, 16 * 5,
                currentMageFrame.getRegionWidth() / 2,  currentMageFrame.getRegionHeight() / 2);
        spriteBatch.draw(currentRogueFrame,
                8 * 16, 16 * 3,
                currentRogueFrame.getRegionWidth() / 2,  currentRogueFrame.getRegionHeight() / 2);

        spriteBatch.end();

    }

    @Override
    public void dispose() {

    }
}
