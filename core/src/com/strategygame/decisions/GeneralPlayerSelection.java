package com.strategygame.decisions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.strategygame.StrategyGame;
import com.strategygame.entities.GameImage;
import com.strategygame.handlers.CTBBController;
import com.strategygame.helpers.AssetsManager;
import com.strategygame.helpers.Constants;
import com.strategygame.helpers.Tween;

public class GeneralPlayerSelection extends PlayerDecisionStateMachine{

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
    private float camSelectionTimer = 0f;
    private final int CAM_SELECTION_TIMER_DELAY = 400;

    private StrategyGame game;

    public GeneralPlayerSelection(CTBBController ctbbController) {
        super(ctbbController);

        this.hudCamera = ctbbController.getGame().getHUDCamera();
        spriteBatch = new SpriteBatch();

        currentPlayerInfo = new GameImage(AssetsManager.getInstance().PLACEHOLDER);
        buttonMap = new GameImage(AssetsManager.getInstance().MAPBUTTON);
        buttonCommands = new GameImage(AssetsManager.getInstance().COMMANDSBUTTON);

        playerInfoTargetX = 0;
        buttonMapTargetX = 240 - buttonMap.getWidth();
        buttonCommandsTargetX = 240 - buttonCommands.getWidth();

        restartState();
    }

    @Override
    public void changeDecisionSM(CTBBController.PlayerDecision decision) {
        ctbbController.changeState(decision);
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
        camSelectionTimer = 0f;
        buttonMap.setCanBeTouched(true);
        buttonCommands.setCanBeTouched(true);
    }

    private void setCurrentState(State state) {
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
                Tween.positionX(currentPlayerInfo, playerInfoTargetX, moveSpeed / 2, delta);
                Tween.positionX(buttonMap, buttonMapTargetX, moveSpeed, delta);
                Tween.positionX(buttonCommands, buttonCommandsTargetX, moveSpeed, delta);

                break;

            case HAS_SELECTED_MAP:
                // hide all images but map button
                showPlayerInfo = false;
                showCommandsButton = false;
                // avoid commands button to be touched
                buttonCommands.setCanBeTouched(false);

                blinkTimer += 1000 * delta;
                camSelectionTimer += 1000 * delta;

                if (blinkTimer >= BLINK_DELAY) {
                    blinkTimer = 0f;
                    blinkMapButton = !blinkMapButton;
                }

                if (camSelectionTimer >= CAM_SELECTION_TIMER_DELAY) {
                    this.ctbbController.changeState(CTBBController.PlayerDecision.CAMERA);
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

    @Override
    public void dispose() {
        /*
        currentPlayerInfo.getImage().dispose();
        buttonMap.getImage().dispose();
        buttonCommands.getImage().dispose();
        */
    }
}
