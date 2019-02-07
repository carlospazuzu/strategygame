package com.strategygame.guieffects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.strategygame.StrategyGame;
import com.strategygame.entities.GameImage;
import com.strategygame.helpers.AssetsManager;
import com.strategygame.helpers.Constants;

public class GeneralPlayerSelection {

    enum State {SHOW_GUI, HAS_SELECTED_MAP, HAS_SELECTED_COMMANDS};

    private OrthographicCamera hudCamera;
    private SpriteBatch spriteBatch;

    private GameImage currentPlayerInfo;
    private GameImage buttonMap;
    private GameImage buttonCommands;

    private float moveSpeed = 120f;
    private float alphaStrength = .65f;

    private State currentState = State.SHOW_GUI;

    private boolean showPlayerInfo;
    private boolean showMapButton;
    private boolean showCommandsButton;
    private boolean blinkMapButton;
    private boolean blinkCommandsButton;

    private int playerInfoTargetX;
    private int buttonMapTargetX;
    private int buttonCommandsTargetX;

    private float blinkTimer = 0f;
    private final int BLINK_DELAY = 50;

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

        restartState();
    }

    public void restartState() {
        currentPlayerInfo.setPosition(new Vector2(-currentPlayerInfo.getWidth(), 8));
        buttonMap.setPosition(new Vector2(
                buttonMap.getWidth() + Constants.GAME_WIDTH,
                Constants.GAME_HEIGHT - buttonMap.getHeight() - 8));
        buttonCommands.setPosition(new Vector2(
                buttonCommands.getWidth() + Constants.GAME_WIDTH,
                Constants.GAME_HEIGHT - buttonMap.getHeight() - 8 - buttonCommands.getHeight() - 8));
        showPlayerInfo = true;
        showMapButton = true;
        showCommandsButton = true;
        blinkMapButton = false;
        blinkCommandsButton = false;
        blinkTimer = 0f;
        buttonMap.setCanBeTouched(true);
        buttonCommands.setCanBeTouched(true);
    }

    public void setCurrentState(State state) {
        currentState = state;
    }

    public void handleInput() {
        if (buttonMap.hasJustBeenTouched())
            setCurrentState(State.HAS_SELECTED_MAP);
        else if (buttonCommands.hasJustBeenTouched())
            setCurrentState(State.HAS_SELECTED_COMMANDS);
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
                // force images to be on the target place
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
                // hide all images but map button
                showPlayerInfo = false;
                showCommandsButton = false;
                // avoid commands button to be touched
                buttonCommands.setCanBeTouched(false);

                blinkTimer += 1000 * delta;

                if (blinkTimer >= BLINK_DELAY) {
                    blinkTimer = 0f;
                    blinkMapButton = !blinkMapButton;
                }

                break;
            case HAS_SELECTED_COMMANDS:
                // hide all images but commands button
                showPlayerInfo = false;
                showMapButton = false;
                // avoid map button to be touched
                buttonMap.setCanBeTouched(false);

                blinkTimer += 1000 * delta;

                if (blinkTimer >= BLINK_DELAY) {
                    blinkTimer = 0f;
                    blinkCommandsButton = !blinkCommandsButton;
                }

                break;
        }

    }

    public void render() {
        spriteBatch.setProjectionMatrix(hudCamera.combined);
        spriteBatch.begin();
        Color c = spriteBatch.getColor();
        // player info
        if (showPlayerInfo)
            spriteBatch.draw(currentPlayerInfo.getImage(), currentPlayerInfo.getPositionX(), currentPlayerInfo.getPositionY());
        // map button routines
        if (showMapButton) {
            if (blinkMapButton)
                spriteBatch.setColor(c.r, c.g, c.b, alphaStrength);
            spriteBatch.draw(buttonMap.getImage(), buttonMap.getPositionX(), buttonMap.getPositionY());
        }
        spriteBatch.setColor(c.r, c.g, c.b, 1f);

        // commands button routines
        if (showCommandsButton) {
            if (blinkCommandsButton)
                spriteBatch.setColor(c.r, c.g, c.b, alphaStrength);
            spriteBatch.draw(buttonCommands.getImage(), buttonCommands.getPositionX(), buttonCommands.getPositionY());
        }
        spriteBatch.setColor(c.r, c.g, c.b, 1f);
        spriteBatch.end();
    }
}
