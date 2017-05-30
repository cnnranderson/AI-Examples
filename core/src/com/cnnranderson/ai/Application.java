package com.cnnranderson.ai;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Application extends ApplicationAdapter {

	// Game Info
	public static final String TITLE = "AI Examples";
	public static final double VERSION = 1.0;
	public static final int V_WIDTH = 640;
	public static final int V_HEIGHT = 480;
	
	@Override
	public void create () {

	}

	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	@Override
	public void dispose () {
		GameManager.instance.dispose();
	}
}
