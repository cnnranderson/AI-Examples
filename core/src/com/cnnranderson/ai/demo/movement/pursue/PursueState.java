package com.cnnranderson.ai.demo.movement.pursue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.cnnranderson.ai.Application;
import com.cnnranderson.ai.behavior.steering.SteeringEntity;
import com.cnnranderson.ai.demo.DemoStateManager;
import com.cnnranderson.ai.demo.common.BaseState;
import com.cnnranderson.ai.util.B2DBodyBuilder;

import static com.cnnranderson.ai.util.B2DConstants.PPM;

public class PursueState extends BaseState {

    OrthographicCamera mCamera;

    Box2DDebugRenderer mWorldRenderer;
    World mWorld;
    Vector2 gravity = new Vector2(0.0f, 0.0f);

    SteeringEntity aiEntity;
    SteeringEntity targetEntity;

    float linearSpeed = 10f;

    public PursueState(Application app, DemoStateManager stateManager) {
        super(app, stateManager);

        initCamera();
        createWorld();

        Body mobileEntity = new B2DBodyBuilder()
                .dynamicBody()
                .boxBody(1f, 1f)
                .position(320f / PPM, 240f / PPM)
                .fixedRotation(false)
                .build(mWorld);

        Body targetBody = new B2DBodyBuilder()
                .dynamicBody()
                .boxBody(1f, 1f)
                .position(150f / PPM, 10f / PPM)
                .fixedRotation(true)
                .density(1f)
                .build(mWorld);

        aiEntity = new SteeringEntity(mobileEntity, false, .5f);
        aiEntity.setMaxLinearSpeed(10);
        aiEntity.setMaxLinearAcceleration(100);
        targetEntity = new SteeringEntity(targetBody, false, .5f);
        markAsSensor(targetEntity);

        final Arrive<Vector2> arriveSB = new Arrive<Vector2>(aiEntity, targetEntity)
                .setTimeToTarget(1f)
                .setArrivalTolerance(0.001f)
                .setDecelerationRadius(3);
        aiEntity.setSteeringBehavior(arriveSB);
    }

    @Override
    public void input() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            targetEntity.getBody().applyForce(0f, linearSpeed, 0, 0, true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            targetEntity.getBody().applyForce(0f, -linearSpeed, 0, 0, true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            targetEntity.getBody().applyForce(-linearSpeed, 0f, 0, 0, true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            targetEntity.getBody().applyForce(linearSpeed, 0f, 0, 0, true);
        }
    }

    @Override
    public void update(float dt) {
        mWorld.step(1f / 60f, 6, 2);

        aiEntity.update(GdxAI.getTimepiece().getDeltaTime());
        targetEntity.update(GdxAI.getTimepiece().getDeltaTime());

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

    public void markAsSensor (SteeringEntity character) {
        Array<Fixture> fixtures = character.getBody().getFixtureList();
        for (int i = 0; i < fixtures.size; i++) {
            fixtures.get(i).setSensor(true);
        }
    }
}
