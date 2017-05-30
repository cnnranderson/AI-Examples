package com.cnnranderson.ai;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

public class GameManager implements Disposable {

    public static final GameManager instance = new GameManager();

    public AssetManager assetManager;
    public SpriteBatch spriteBatch;
    public ShapeRenderer shapeRenderer;

    // Game Vars

    public GameManager() {
        assetManager = new AssetManager();
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        spriteBatch.dispose();
        assetManager.dispose();
    }
}
