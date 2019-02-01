package com.strategygame.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetsManager {

    private static AssetsManager assetsManager = new AssetsManager();

    public final Texture UPARROW = new Texture(Gdx.files.internal("gui/up-arrow.png"));
    public final Texture DOWNARROW = new Texture(Gdx.files.internal("gui/down-arrow.png"));
    public final Texture LEFTARROW = new Texture(Gdx.files.internal("gui/left-arrow.png"));
    public final Texture RIGHTARROW = new Texture(Gdx.files.internal("gui/right-arrow.png"));

    public final Texture KNIGHT = new Texture(Gdx.files.internal("fighters/knight.png"));

    private AssetsManager() {}

    public static AssetsManager getInstance() {
        return assetsManager;
    }
}
