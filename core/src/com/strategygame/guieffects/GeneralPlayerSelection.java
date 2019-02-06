package com.strategygame.guieffects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.strategygame.StrategyGame;
import com.strategygame.entities.GameImage;
import com.strategygame.handlers.PlayerInput;
import com.strategygame.helpers.AssetsManager;

public class GeneralPlayerSelection {

    enum State {SHOW_GUI, HAS_SELECTED_MAP, HAS_SELECTED_COMMANDS};

    private OrthographicCamera hudCamera;
    private SpriteBatch spriteBatch;

    private GameImage currentPlayerInfo;
    private GameImage buttonMap;
    private GameImage buttonCommands;

    private float moveSpeed = 120f;

    private State currentState = State.SHOW_GUI;

    private boolean showPlayerInfo = true;
    private boolean showMapButton = true;
    private boolean showCommandsButton = true;

    private int playerInfoTargetX;
    private int buttonMapTargetX;
    private int buttonCommandsTargetX;

    private StrategyGame game;

    public GeneralPlayerSelection(OrthographicCamera hudCamera, StrategyGame game) {
        this.hudCamera = hudCamera;
        spriteBatch = new SpriteBatch();

        this.game = game;

        currentPlayerInfo = new GameImage(AssetsManager.getInstance().PLACEHOLDER);
        buttonMap = new GameImage(AssetsManager.getInstance().MAPBUTTON);
        buttonCommands = new GameImage(AssetsManager.getInstance().COMMANDSBUTTON);

        playerInfoTargetX = 0;
        buttonMapTargetX = 240 - buttonMap.getWidth();
        buttonCommandsTargetX = 240 - buttonCommands.getWidth();

        restartPosition();
    }

    private void restartPosition() {
        currentPlayerInfo.setPosition(new Vector2(-currentPlayerInfo.getWidth(), 8));
        buttonMap.setPosition(new Vector2(buttonMap.getWidth() + 240, 160 - buttonMap.getHeight() - 8));
        buttonCommands.setPosition(new Vector2(buttonCommands.getWidth() + 240, 160 - buttonMap.getHeight() - 8 - buttonCommands.getHeight() - 8));
    }

    public void setCurrentState(State state) {
        currentState = state;
    }

    public void handleInput() {
        Gdx.app.log("POS X", "" + PlayerInput.screenX);
        Gdx.app.log("POS Y", "" + PlayerInput.screenY);
        Gdx.app.log("BTN POS X", "" + buttonMap.getPositionX());
        Gdx.app.log("BTN POS Y", "" + buttonMap.getPositionY());
        Gdx.app.log("SCREEN WIDTH", "" + Gdx.graphics.getWidth());
        Gdx.app.log("SCREEN HEIGHT", "" + Gdx.graphics.getHeight());
        Gdx.app.log("CLICK POS X", "" + PlayerInput.screenX / (Gdx.graphics.getWidth() / 240.0f));
        Gdx.app.log("CLICK POS Y", "" + PlayerInput.screenY / (Gdx.graphics.getHeight() / 160.0f));
        if (PlayerInput.isPressed) {
            if (new Rectangle(buttonMap.getPositionX(), buttonMap.getPositionY(),
                    buttonMap.getPositionX() + buttonMap.getWidth(), buttonMap.getPositionY() + buttonMap.getHeight())
                    .contains(new Vector2(PlayerInput.screenX / (Gdx.graphics.getWidth() / 240.0f), 160 - (PlayerInput.screenY / (Gdx.graphics.getHeight() / 160.0f))))) {
                Gdx.app.log("TOUCH", "PLAYER HAS JUST TOUCHED MAP BUTTON!");
            }
        }
    }

    public void update(float delta) {

        switch (currentState) {
            case SHOW_GUI:
                // move images to their respective places
                if (currentPlayerInfo.getPositionX() < playerInfoTargetX) {
                    currentPlayerInfo.setPositionX(currentPlayerInfo.getPositionX() + ((moveSpeed / 2) * delta));
                }
                if (buttonMap.getPositionX() > buttonMapTargetX) {
                    buttonMap.setPositionX(buttonMap.getPositionX() - (moveSpeed * delta));
                }
                if (buttonCommands.getPositionX() > buttonCommandsTargetX) {
                    buttonCommands.setPositionX(buttonCommands.getPositionX() - (moveSpeed * delta));
                }
                // force images to be on right place
                if (currentPlayerInfo.getPositionX() > playerInfoTargetX) {
                    currentPlayerInfo.setPositionX(playerInfoTargetX);
                }
                if (buttonMap.getPositionX() < buttonMapTargetX) {
                    buttonMap.setPositionX(buttonMapTargetX);
                }
                if (buttonCommands.getPositionX() < buttonCommandsTargetX) {
                    buttonCommands.setPositionX(buttonCommandsTargetX);
                }
                break;
            case HAS_SELECTED_MAP:
                break;
            case HAS_SELECTED_COMMANDS:
                break;
        }

    }

    public void render() {
        spriteBatch.setProjectionMatrix(hudCamera.combined);
        spriteBatch.begin();
        if (showPlayerInfo)
            spriteBatch.draw(currentPlayerInfo.getImage(), currentPlayerInfo.getPositionX(), currentPlayerInfo.getPositionY());
        if (showMapButton)
            spriteBatch.draw(buttonMap.getImage(), buttonMap.getPositionX(), buttonMap.getPositionY());
        if (showCommandsButton)
            spriteBatch.draw(buttonCommands.getImage(), buttonCommands.getPositionX(), buttonCommands.getPositionY());
        spriteBatch.end();
    }
}
