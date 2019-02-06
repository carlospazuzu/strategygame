package com.strategygame.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetsManager {

    private static AssetsManager assetsManager = new AssetsManager();

    // GUI
    public final Texture UPARROW = new Texture(Gdx.files.internal("gui/up-arrow.png"));
    public final Texture DOWNARROW = new Texture(Gdx.files.internal("gui/down-arrow.png"));
    public final Texture LEFTARROW = new Texture(Gdx.files.internal("gui/left-arrow.png"));
    public final Texture RIGHTARROW = new Texture(Gdx.files.internal("gui/right-arrow.png"));

    // fighters
    public final Texture KNIGHT = new Texture(Gdx.files.internal("fighters/knight.png"));
    public final Texture ROGUE = new Texture(Gdx.files.internal("fighters/rogue.png"));
    public final Texture MAGE = new Texture(Gdx.files.internal("fighters/mage.png"));
    public final Texture BARBARIAN = new Texture(Gdx.files.internal("fighters/barbarian.png"));

    // monsters
    public final Texture RAT = new Texture(Gdx.files.internal("monsters/rat.png"));
    public final Texture SNAKE = new Texture(Gdx.files.internal("monsters/snake.png"));
    public final Texture EYE = new Texture(Gdx.files.internal("monsters/eye.png"));
    public final Texture SLIME = new Texture(Gdx.files.internal("monsters/slime.png"));

    // GUI elements
    public final Texture MAPBUTTON = new Texture(Gdx.files.internal("gui/buttons/button_map.png"));
    public final Texture COMMANDSBUTTON = new Texture(Gdx.files.internal("gui/buttons/button_commands.png"));
    public final Texture PLACEHOLDER = new Texture(Gdx.files.internal("gui/mockups/player_info.png"));

    private AssetsManager() {}

    public static AssetsManager getInstance() {
        return assetsManager;
    }
}
