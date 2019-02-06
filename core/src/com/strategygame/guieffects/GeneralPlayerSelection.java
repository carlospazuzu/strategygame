package com.strategygame.guieffects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.strategygame.entities.GameImage;
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

    public GeneralPlayerSelection(OrthographicCamera hudCamera) {
        this.hudCamera = hudCamera;
        spriteBatch = new SpriteBatch();

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
