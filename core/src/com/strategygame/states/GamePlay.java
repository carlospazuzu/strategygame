package com.strategygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.strategygame.handlers.GameStateManager;

public class GamePlay extends GameState {

    BitmapFont font = new BitmapFont();

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    public GamePlay(GameStateManager gsm) {
        super(gsm);

        tiledMap = new TmxMapLoader().load("tmx/forest.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

    }

    @Override
    public void dispose() {

    }
}
