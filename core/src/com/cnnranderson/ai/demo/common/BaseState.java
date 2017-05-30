package com.cnnranderson.ai.demo.common;

import com.cnnranderson.ai.Application;
import com.cnnranderson.ai.demo.DemoStateManager;

public abstract class BaseState {

    // References
    protected Application app;
    protected DemoStateManager stateManager;

    protected BaseState(final Application app, final DemoStateManager stateManager) {
        this.app = app;
        this.stateManager = stateManager;
    }

    public abstract void input();
    public abstract void update(float dt);
    public abstract void render();
    public abstract void resize(int w, int h);
    public abstract void dispose();
}