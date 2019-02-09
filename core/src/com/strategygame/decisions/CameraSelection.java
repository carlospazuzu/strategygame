package com.strategygame.decisions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.strategygame.entities.GameImage;
import com.strategygame.handlers.CTBBController;
import com.strategygame.handlers.PlayerInput;
import com.strategygame.helpers.AssetsManager;
import com.strategygame.helpers.Constants;
import com.strategygame.helpers.Tween;

import static com.strategygame.helpers.Constants.*;

public class CameraSelection extends PlayerDecisionStateMachine{

    private GameImage leftArrow;
    private GameImage rightArrow;
    private GameImage upArrow;
    private GameImage downArrow;

    private float leftArrowInitialX;
    private float leftArrowInitialY;
    private float rightArrowInitialX;
    private float rightArrowInitialY;
    private float upArrowInitialX;
    private float upArrowInitialY;
    private float downArrowInitialX;
    private float downArrowInitialY;

    private Rectangle leftHitBox;
    private Rectangle rightHitBox;
    private Rectangle upHitBox;
    private Rectangle downHitBox;
    private Rectangle centerHitBox;

    private BitmapFont bitmapFont;

    private float arrowSpeed = 20f;

    public static boolean canTouch;

    private SpriteBatch spriteBatch;

    private final String guiMsg = "APERTE E SEGURE PARA MOVER";
    private final String finishMsg = "CLIQUE AQUI PARA ENCERRAR";

    public CameraSelection(CTBBController ctbbController) {
        super(ctbbController);

        leftArrow = new GameImage(AssetsManager.getInstance().LEFTARROW);
        rightArrow = new GameImage(AssetsManager.getInstance().RIGHTARROW);
        upArrow = new GameImage(AssetsManager.getInstance().UPARROW);
        downArrow = new GameImage(AssetsManager.getInstance().DOWNARROW);

        spriteBatch = new SpriteBatch();

        leftArrowInitialX = 10;
        leftArrowInitialY = GAME_HEIGHT / 2f - leftArrow.getHeight();
        rightArrowInitialX = GAME_WIDTH - rightArrow.getWidth() - 8;
        rightArrowInitialY = GAME_HEIGHT / 2f - rightArrow.getHeight() / 2f;
        upArrowInitialX = GAME_WIDTH / 2f - upArrow.getWidth() / 2f;
        upArrowInitialY = 160 - upArrow.getHeight() - 10;
        downArrowInitialX = GAME_WIDTH / 2f - downArrow.getWidth() / 2f;
        downArrowInitialY = 10;

        leftHitBox = new Rectangle(0, 0, 24, GAME_HEIGHT);
        rightHitBox = new Rectangle(GAME_WIDTH, 0, 24, GAME_HEIGHT);
        upHitBox = new Rectangle(24, 0, GAME_WIDTH - 24, 24);
        downHitBox = new Rectangle(24, GAME_HEIGHT, GAME_WIDTH - 24, 24);
        centerHitBox = new Rectangle(32, 32, GAME_WIDTH - 32, GAME_HEIGHT - 32);

        canTouch = true;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/slkscr.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 8;
        bitmapFont = generator.generateFont(parameter);
        generator.dispose();
        bitmapFont.setColor(new Color(0xff / 255f, 0xff / 255f, 0xff / 255f, 1f));

        restartState();
    }

    @Override
    public void restartState() {
        leftArrow.setPosition(new Vector2(leftArrowInitialX, leftArrowInitialY));
        rightArrow.setPosition(new Vector2(rightArrowInitialX, rightArrowInitialY));
        upArrow.setPosition(new Vector2(upArrowInitialX, upArrowInitialY));
        downArrow.setPosition(new Vector2(downArrowInitialX, downArrowInitialY));
    }

    @Override
    public void changeDecisionSM(CTBBController.PlayerDecision decision) {
        ctbbController.changeState(decision);
    }

    @Override
    public void handleInput() {
        if (PlayerInput.isPressed) {
            Gdx.app.log("X = ", "" + PlayerInput.screenX);
            Gdx.app.log("Y = ", "" + PlayerInput.screenY);
        }
        if (PlayerInput.isPressed && canTouch && rightHitBox.contains(PlayerInput.screenX, PlayerInput.screenY)) {
            ctbbController
                    .getCameraController()
                    .moveX(1);
        } else if (PlayerInput.isPressed && canTouch && leftHitBox.contains(PlayerInput.screenX, PlayerInput.screenY)) {
            ctbbController
                    .getCameraController()
                    .moveX(-1);
        } else if (PlayerInput.isPressed && canTouch && upHitBox.contains(PlayerInput.screenX, PlayerInput.screenY)) {
            ctbbController
                    .getCameraController()
                    .moveY(1);
        } else if (PlayerInput.isPressed && canTouch && downHitBox.contains(PlayerInput.screenX, PlayerInput.screenY)) {
            ctbbController
                    .getCameraController()
                    .moveY(-1);
        } else if (PlayerInput.isPressed && centerHitBox.contains(PlayerInput.screenX, PlayerInput.screenY)) {
            changeDecisionSM(CTBBController.PlayerDecision.GENERAL);
        }
    }

    @Override
    public void update(float delta) {
        Tween.warningArrowX(leftArrow, leftArrowInitialX, leftArrowInitialX - 6, arrowSpeed, delta);
        Tween.warningArrowX(rightArrow, rightArrowInitialX,rightArrowInitialX + 6, arrowSpeed, delta);
        Tween.warningArrowY(upArrow, upArrowInitialY, upArrowInitialY + 6, arrowSpeed, delta);
        Tween.warningArrowY(downArrow, downArrowInitialY, downArrowInitialY - 6, arrowSpeed, delta);

    }

    @Override
    public void render() {
        spriteBatch.setProjectionMatrix(ctbbController.getGame().getHUDCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(leftArrow.getImage(), leftArrow.getPositionX(), leftArrow.getPositionY());
        spriteBatch.draw(rightArrow.getImage(), rightArrow.getPositionX(), rightArrow.getPositionY());
        spriteBatch.draw(upArrow.getImage(), upArrow.getPositionX(), upArrow.getPositionY());
        spriteBatch.draw(downArrow.getImage(), downArrow.getPositionX(), downArrow.getPositionY());
        Color c = spriteBatch.getColor();
        bitmapFont.setColor(c.r, c.g, c.b, .6f);
        bitmapFont.draw(spriteBatch, guiMsg,
                (GAME_WIDTH / 2f) - (guiMsg.length() * 2.5f),
                GAME_HEIGHT / 2f - bitmapFont.getLineHeight());
        bitmapFont.draw(spriteBatch, finishMsg,
                (GAME_WIDTH / 2f) - (finishMsg.length() * 2.5f),
                GAME_HEIGHT / 2f - bitmapFont.getLineHeight() - 8);
        bitmapFont.setColor(c.r, c.g, c.b, 1f);
        spriteBatch.end();

    }

    @Override
    public void dispose() {

    }
}
