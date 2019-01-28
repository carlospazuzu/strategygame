package com.strategygame.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.strategygame.handlers.GameStateManager;

public class GamePlay extends GameState {

    BitmapFont font = new BitmapFont();

    public GamePlay(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        font.draw(sb, "TESTE!", 100, 100);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
