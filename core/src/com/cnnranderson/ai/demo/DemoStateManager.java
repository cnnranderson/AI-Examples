package com.cnnranderson.ai.demo;

import com.badlogic.gdx.Gdx;
import com.cnnranderson.ai.Application;
import com.cnnranderson.ai.demo.common.BaseState;
import com.cnnranderson.ai.demo.movement.flock.FlockState;
import com.cnnranderson.ai.demo.movement.formation.FormationState;
import com.cnnranderson.ai.demo.movement.pursue.PursueState;
import com.cnnranderson.ai.demo.movement.wander.WanderState;

import java.util.Stack;

public class DemoStateManager {

    public final Application app;
    public enum State {
        // Decision AI
        FSM,
        PUSHDOWN,
        TREES,

        // Steering/Movement AI
        FLOCK,
        FORMATION,
        PURSUE,
        WANDER,

        // Pathfinding AI
        ASTAR,
        HIERARCHICAL
    }

    private Stack<BaseState> states;

    public DemoStateManager(Application app) {
        this.app = app;
        states = new Stack<BaseState>();
    }

    public void setState(State state) {
        if (states.size() >= 1) {
            states.pop().dispose();
        }
        states.push(getState(state));
    }

    private BaseState getState(State state) {
        switch (state) {
            case FLOCK:
                return new FlockState(app, this);
            case FORMATION:
                return new FormationState(app, this);
            case PURSUE:
                return new PursueState(app, this);
            case WANDER:
                return new WanderState(app, this);
            default:
                return null;
        }
    }

    public void render() {
        states.peek().input();
        states.peek().update(Gdx.graphics.getDeltaTime());
        states.peek().render();
    }

    public void resize(int w, int h) {
        states.peek().resize(w, h);
    }

    public void dispose() {
        for (BaseState gs : states) {
            gs.dispose();
        }
        states.clear();
    }
}
