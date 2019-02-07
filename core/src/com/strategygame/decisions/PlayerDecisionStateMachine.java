package com.strategygame.decisions;

import com.strategygame.handlers.CTBBController;

public abstract class PlayerDecisionStateMachine {

    protected CTBBController ctbbController;

    protected PlayerDecisionStateMachine(CTBBController ctbbController) {
        this.ctbbController = ctbbController;
    }

    public abstract void restartState();
    public abstract void changeDecisionSM(CTBBController.PlayerDecision decision);

    public abstract void handleInput();
    public abstract void update(float delta);
    public abstract void render();
    public abstract void dispose();
}
