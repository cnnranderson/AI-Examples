package com.cnnranderson.ai.demo.movement.flock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.cnnranderson.ai.Application;
import com.cnnranderson.ai.demo.DemoStateManager;
import com.cnnranderson.ai.demo.common.BaseState;
import com.cnnranderson.ai.util.B2DBodyBuilder;

import static com.cnnranderson.ai.util.B2DConstants.PPM;

public class FlockState extends BaseState {

    OrthographicCamera mCamera;

    Box2DDebugRenderer mWorldRenderer;
    World mWorld;
    Vector2 gravity = new Vector2(0.0f, 0.0f);

    public FlockState(Application app, DemoStateManager stateManager) {
        super(app, stateManager);

        initCamera();
        createWorld();

        new B2DBodyBuilder()
                .dynamicBody()
                .boxBody(1f, 1f)
                .position(320f / PPM, 240f / PPM)
                .fixedRotation(false)
                .build(mWorld);
    }

    @Override
    public void input() {

    }

    @Override
    public void update(float dt) {
        mWorld.step(1f / 60f, 6, 2);

        mCamera.update();
        app.spriteBatch.setProjectionMatrix(mCamera.combined);
        app.shapeBatch.setProjectionMatrix(mCamera.combined);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mWorldRenderer.render(mWorld, mCamera.combined.cpy().scl(32f));
    }

    @Override
    public void resize(int w, int h) {
        mCamera.setToOrtho(false, w, h);
    }

    @Override
    public void dispose() {
        mWorldRenderer.dispose();
        mWorld.dispose();
    }

    private void initCamera() {
        mCamera = new OrthographicCamera(Application.V_WIDTH, Application.V_HEIGHT);

        app.spriteBatch.setProjectionMatrix(mCamera.combined);
        app.shapeBatch.setProjectionMatrix(mCamera.combined);
    }

    private void createWorld() {
        mWorld = new World(gravity, false);
        mWorldRenderer = new Box2DDebugRenderer();
    }
}
