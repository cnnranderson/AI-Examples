package com.cnnranderson.ai;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.cnnranderson.ai.demo.DemoStateManager;

public class Application extends ApplicationAdapter {

	// Game Info
	public static final String TITLE = "AI Examples";
	public static final double VERSION = 1.0;
	public static final int V_WIDTH = 640;
	public static final int V_HEIGHT = 480;

	private DemoStateManager mStateManager;
	
	@Override
	public void create () {
		initStateManager();
	}

	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mStateManager.render();

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}
	
	@Override
	public void dispose () {
	}

	private void initStateManager() {
		mStateManager = new DemoStateManager(this);
	}
}
