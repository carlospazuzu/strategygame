package com.strategygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.strategygame.fighters.Knight;
import com.strategygame.fighters.Mage;
import com.strategygame.fighters.Rogue;
import com.strategygame.handlers.CTBBController;
import com.strategygame.handlers.CameraController;
import com.strategygame.handlers.GameStateManager;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

public class GamePlay extends GameState {

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    private SpriteBatch spriteBatch;

    private Music openingMusic;
    private Music battleMusic;

    private float stateTime;

    private Knight knight;
    private Mage mage;
    private Rogue rogue;

    private CameraController cameraController;

    private CTBBController ctbbController;

    public GamePlay(GameStateManager gsm) {
        super(gsm);

        tiledMap = new TmxMapLoader().load("tmx/forest.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        spriteBatch = new SpriteBatch();
        stateTime = 0f;

        knight = new Knight();
        mage = new Mage();
        rogue = new Rogue();

        cameraController = new CameraController(camera, tiledMap, game);
        ctbbController = new CTBBController(null, hudCamera, gsm.game());
        // cameraController.moveToChar(14, 11);

        openingMusic = Gdx.audio.newMusic(Gdx.files.internal("music/opening.ogg"));
        battleMusic = Gdx.audio.newMusic(Gdx.files.internal("music/battle.ogg"));

        openingMusic.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                battleMusic.setLooping(true);
                battleMusic.play();
            }
        });

        openingMusic.setLooping(false);
        openingMusic.play();

    }

    @Override
    public void handleInput() {
        ctbbController.handleInput();
    }

    @Override
    public void update(float delta) {
        stateTime += Gdx.graphics.getDeltaTime();

        knight.update(delta);
        mage.update(delta);
        rogue.update(delta);

        ctbbController.update(delta);

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();

        knight.render(spriteBatch, 7, 4);
        mage.render(spriteBatch, 5, 5);
        rogue.render(spriteBatch, 9, 4);

        spriteBatch.end();

        cameraController.update(Gdx.graphics.getDeltaTime());

        ctbbController.render();

    }

    @Override
    public void dispose() {

    }
}
