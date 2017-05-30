package com.cnnranderson.ai;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cnnranderson.ai.demo.DemoStateManager;

public class Application extends ApplicationAdapter {

	public static final String TITLE = "AI Examples";
	public static final double VERSION = 1.0;
	public static final int V_WIDTH = 640;
	public static final int V_HEIGHT = 480;

	private DemoStateManager mStateManager;

	public SpriteBatch spriteBatch;
	public ShapeRenderer shapeBatch;
	
	@Override
	public void create () {
		initBatches();
		initStateManager();
	}

	@Override
	public void render () {
		mStateManager.render();

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	@Override
	public void resize(int w, int h) {
		mStateManager.resize(w, h);
	}
	
	@Override
	public void dispose () {
		mStateManager.dispose();
	}

	private void initBatches() {
		spriteBatch = new SpriteBatch();
		shapeBatch = new ShapeRenderer();
	}

	private void initStateManager() {
		mStateManager = new DemoStateManager(this);
		mStateManager.setState(DemoStateManager.State.FLOCK);
	}
}
